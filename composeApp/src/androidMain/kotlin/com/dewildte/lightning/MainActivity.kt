package com.dewildte.lightning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dewildte.lightning.design.components.LightningApplicationScaffold
import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.application.viewmodel.LightningApplicationViewModel
import com.dewildte.lightning.design.components.LightningApplicationScaffoldState
import com.dewildte.lightning.feature.finance.TransactionList
import com.dewildte.lightning.finance.ReadTransaction
import com.dewildte.lightning.navigation.AppDestination
import com.dewildte.lightning.navigation.HomeRoute
import com.dewildte.lightning.network.FinanceApi
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val model = application as LightningApplication

            val financeApi = remember { FinanceApi() }

            val transactions = remember { mutableStateListOf<ReadTransaction>() }

            var error by remember { mutableStateOf<Throwable?>(null) }

            val state by model.state.collectAsStateWithLifecycle()

            val scaffoldState = LightningApplicationScaffoldState()

            val navController = rememberNavController()

            LightningApplicationScaffold(
                state = scaffoldState,
                selectedDestination = AppDestination.HOME,
            ) {

                val viewModel = viewModel {
                    LightningApplicationViewModel(
                        application = model
                    )
                }

                val homeState by viewModel.state.collectAsStateWithLifecycle()

                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    modifier = Modifier.fillMaxSize(),
                ) {

                    composable<HomeRoute> { backstackEntry ->

                        val scope = rememberCoroutineScope()

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