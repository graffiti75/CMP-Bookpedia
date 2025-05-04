package com.plcoding.bookpedia.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.book.presentation.sharedKoinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = Route.BookGraph
	) {
		navigation<Route.BookGraph>(
			startDestination = Route.BookList
		) {
			composable<Route.BookList> {
				val viewModel = koinViewModel<BookListViewModel>()
				val selectedBookViewModel = it
					.sharedKoinViewModel<SelectedBookViewModel>(navController)

				LaunchedEffect(true) {
					selectedBookViewModel.onSelectedBook(null)
				}

				BookListScreenRoot(
					viewModel = viewModel,
					onBookClick = { book ->
						selectedBookViewModel.onSelectedBook(book)
						navController.navigate(
							Route.BookDetail(book.id)
						)
					}
				)
			}
			composable<Route.BookDetail> {
				val selectedBookViewModel = it
					.sharedKoinViewModel<SelectedBookViewModel>(navController)
//				val args = it.toRoute<Route.BookDetail>()
				val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					Text("BookDetail screen! $selectedBook")
				}
			}
		}
	}
}