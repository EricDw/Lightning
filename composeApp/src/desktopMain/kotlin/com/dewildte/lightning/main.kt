package com.dewildte.lightning

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import androidx.compose.ui.window.application
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.application.viewmodel.LightningApplicationViewModel
import com.dewildte.lightning.design.components.LightningApplicationScaffold
import com.dewildte.lightning.feature.finance.TransactionList
import com.dewildte.lightning.finance.ReadTransaction
import com.dewildte.lightning.navigation.AppDestination
import com.dewildte.lightning.navigation.HomeRoute
import com.dewildte.lightning.network.FinanceApi
import kotlinx.coroutines.launch
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.description_app_icon
import lightning.composeapp.generated.resources.title_app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {

    val model = remember(this) {
        DesktopLightningApplication(
            applicationScope = this
        )
    }

    val state by model.state.collectAsState()

    var isLoading by remember(state.isLoading) { mutableStateOf(state.isLoading) }

    val title = stringResource(Res.string.title_app_name)

    var error by remember { mutableStateOf<Throwable?>(null) }

    val scope = rememberCoroutineScope()

    if (isLoading) {

        val loadingWindowState = rememberDialogState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 200.dp, height = 200.dp)
        )

        DialogWindow(
            onCloseRequest = { /* no-op */ },
            undecorated = true,
            focusable = false,
            transparent = true,
            state = loadingWindowState,
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = stringResource(Res.string.description_app_icon),
                    modifier = Modifier.size(100.dp)
                )
            }

            LaunchedEffect(model) {
                model.recieve(LightningApplication.Message.GetPlatform({}))
            }
        }

    } else {

        val mainWindowState = rememberWindowState(
            position = WindowPosition(Alignment.Center)
        )


        Window(
            onCloseRequest = ::exitApplication,
            title = title,
            state = mainWindowState,
            icon = rememberVectorPainter(Icons.Default.Bolt)
        ) {

            val viewModel = viewModel {
                LightningApplicationViewModel(
                    application = model
                )
            }

            val homeState by viewModel.state.collectAsStateWithLifecycle()

            val navController = rememberNavController()

            LightningApplicationScaffold(
                state = homeState,
                selectedDestination = AppDestination.HOME,
            ) {

                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    modifier = Modifier.fillMaxSize(),
                ) {

                    composable<HomeRoute> { backstackEntry ->
                        val transactions = remember(backstackEntry) {
                            mutableStateListOf<ReadTransaction>()
                        }

                        if (error != null) {
                            Card(
                                modifier = Modifier.wrapContentSize(),
                                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.error)
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = error.toString(),
                                )
                            }
                        } else {

                            TransactionList(
                                transactions = transactions,
                                onLoadClick = {
                                    scope.launch {
                                        val message = LightningApplication.Message.RetrieveTransactions()
                                        model.recieve(message)
                                        try {
                                            transactions.addAll(message.response.await())
                                        } catch (e: Throwable) {
                                            error = e
                                        }
                                    }
                                }
                            )
                        }

                    }

                }
            }
        }
    }
}