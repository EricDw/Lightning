package com.dewildte.lightning.feature.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import com.dewildte.lightning.design.components.LargePanel
import com.dewildte.lightning.feature.transactions.components.TransactionListItem
import com.dewildte.lightning.models.transactions.Transaction
import com.dewildte.lightning.feature.transactions.sample.sampleTransactonList
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.message_loading_transactions
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionsScreenController(
    viewModel: TransactionsScreenViewModel,
) {

    val windowWidthSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    val twoPane = when (windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            false
        }

        WindowWidthSizeClass.MEDIUM -> {
            true
        }

        else -> {
            // EXPANDED
            true
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    var currentSearchTerm by remember {
        mutableStateOf(state.searchTerm)
    }

    var searchMode by remember {
        mutableStateOf(false)
    }

    with(state) {
        TransactionsScreen(
            twoPane = twoPane,
            isLoading = isLoading,
            error = error,
            searchTerm = currentSearchTerm,
            expandSearchBar = searchMode,
            transactions = transactions,
            selectedTransaction = state.selectedTransaction,
            onTransactionClick = viewModel::selectTransaction,
            searchedTransactions = state.searchedTransactions,
            onSerchTermChange = { newSearchTerm ->
                currentSearchTerm = newSearchTerm
                viewModel.updateSearchTerm(newSearchTerm)
                if (searchMode) {
                    if (searchTerm.isNotBlank()) {
                        viewModel.searchTransactions(searchTerm = newSearchTerm)
                    } else {
                        viewModel.cancelSearch()
                    }
                }
            },
            onSearchClick = {
                searchMode = true
                if (searchTerm.isNotBlank()) {
                    viewModel.searchTransactions(searchTerm)
                } else {
                    viewModel.cancelSearch()
                }
            },
            onCloseSearchClick = {
                searchMode = false
                viewModel.cancelSearch()
            },
            onClearSearchTermClick = {
                currentSearchTerm = ""
                viewModel.updateSearchTerm("")
                viewModel.cancelSearch()
            },
        )
    }

    LaunchedEffect(viewModel) {
        viewModel.loadTransactions()
    }

}

@Composable
fun TransactionsScreen(
    twoPane: Boolean = false,
    isLoading: Boolean = true,
    error: Throwable? = null,
    searchTerm: String = "",
    expandSearchBar: Boolean = false,
    transactions: List<Transaction> = emptyList(),
    searchedTransactions: List<Transaction> = emptyList(),
    selectedTransaction: Transaction? = null,
    onSerchTermChange: (newSearchTerm: String) -> Unit = {},
    onTransactionClick: (transaction: Transaction) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCloseSearchClick: () -> Unit = {},
    onClearSearchTermClick: () -> Unit = {},
) {
    TransactionScreenLayout(
        twoPane = twoPane,
        showDetails = selectedTransaction != null,
        listContent = {
            LargePanel(
                modifier = Modifier
                    .fillMaxHeight(),
                abovePanelContent = {
                    TransactionSearchBar(
                        expanded = expandSearchBar,
                        searchTerm = searchTerm,
                        transactions = searchedTransactions,
                        selectedTransaction = selectedTransaction,
                        onTransactionClick = onTransactionClick,
                        onSearchTermChange = onSerchTermChange,
                        onExpandedChange = { /* no-op */ },
                        onSearchClick = onSearchClick,
                        onBackClick = onCloseSearchClick,
                        onClearClick = onClearSearchTermClick,
                    )
                },
            ) {

                when {

                    error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = error.toString(),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = stringResource(Res.string.message_loading_transactions))
                        }
                    }

                    else -> {
                        TransactionList(
                            transactions = transactions,
                            clipItems = true,
                            selectedTransaction = selectedTransaction,
                            onTransactionClick = onTransactionClick
                        )
                    }
                }
            }

        },
        detailsContent = {
            LargePanel(
                modifier = Modifier
                    .fillMaxHeight(),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    if (selectedTransaction != null) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = selectedTransaction.toString(),
                                modifier = Modifier.padding(16.dp),
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Select a Transaction",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                    }
                }
            }
        },
    )
}

@Composable
private fun TransactionScreenLayout(
    twoPane: Boolean,
    showDetails: Boolean,
    listContent: @Composable () -> Unit,
    detailsContent: @Composable () -> Unit,
) {
    if (twoPane) {
        Row(
            Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.weight(0.5F)) {
                listContent()
            }
            Spacer(modifier = Modifier.width(width = 16.dp))
            Box(
                modifier = Modifier.weight(0.5F)
            ) {
                detailsContent()
            }
        }
    } else {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            if (showDetails) {
                detailsContent()
            } else {
                listContent()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransactionSearchBar(
    searchTerm: String,
    expanded: Boolean,
    transactions: List<Transaction>,
    selectedTransaction: Transaction?,
    onTransactionClick: (transaction: Transaction) -> Unit,
    onSearchTermChange: (newSearchTerm: String) -> Unit,
    onExpandedChange: (expanded: Boolean) -> Unit,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
    onClearClick: () -> Unit,
) {
    SearchBar(
        inputField = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.defaultMinSize(minHeight = 56.dp).padding(horizontal = 4.dp)
            ) {
                if (expanded) {
                    IconButton(

                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(
                        onClick = onSearchClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
                BasicTextField(
                    value = searchTerm,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                    onValueChange = onSearchTermChange,
                    modifier = Modifier.padding(16.dp).weight(1F),
                    visualTransformation = { annotatedString: AnnotatedString ->
                        if (annotatedString.isBlank()) {
                            val hint = AnnotatedString("Search Transactions")
                            val offsetMapping = object : OffsetMapping {
                                override fun originalToTransformed(offset: Int): Int {
                                    return 0
                                }

                                override fun transformedToOriginal(offset: Int): Int {
                                    return annotatedString.length
                                }

                            }
                            TransformedText(hint, offsetMapping = offsetMapping)
                        } else {
                            val offsetMapping = object : OffsetMapping {
                                override fun originalToTransformed(offset: Int): Int {
                                    return annotatedString.length
                                }

                                override fun transformedToOriginal(offset: Int): Int {
                                    return annotatedString.length
                                }

                            }
                            TransformedText(annotatedString, OffsetMapping.Identity)
                        }
                    },
                )
                if (searchTerm.isNotBlank()) {
                    IconButton(
                        onClick = onClearClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp
            )
    ) {
        TransactionList(
            transactions = transactions,
            clipItems = false,
            selectedTransaction = selectedTransaction,
            onTransactionClick = onTransactionClick,
        )
    }

}

@Composable
private fun TransactionList(
    transactions: List<Transaction>,
    clipItems: Boolean,
    selectedTransaction: Transaction?,
    onTransactionClick: (transaction: Transaction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = transactions,
            key = { it.id.value },
        ) { transaction ->
            val itemDrawModifier = Modifier
                .let {
                    if (clipItems) {
                        it.clip(MaterialTheme.shapes.medium)
                    } else it
                }
                .clickable(
                    onClick = { onTransactionClick(transaction) },
                    onClickLabel = null,
                    role = Role.Button,
                )
            TransactionListItem(
                transaction = transaction,
                selected = transaction == selectedTransaction,
                modifier = itemDrawModifier,
            )
        }
    }
}

@Composable
private fun TransactionDetails(
    selectedTransaction: Transaction,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = selectedTransaction.toString(),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Immutable
data class TransactionsScreenState(
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val searchTerm: String = "",
    val selectedTransaction: Transaction? = null,
    val transactions: List<Transaction> = emptyList(),
    val searchedTransactions: List<Transaction> = emptyList(),
)

@Preview
@Composable
private fun TransactionsScreenPreview() {
    val transactions = remember {
        sampleTransactonList()
    }
    TransactionsScreen(
        transactions = transactions,
        isLoading = false,
    )
}