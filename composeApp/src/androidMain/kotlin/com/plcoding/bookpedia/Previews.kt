package com.plcoding.bookpedia

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.books
import com.plcoding.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview(showBackground = true)
@Composable
fun BookSearchBarPreview() {
	MaterialTheme {
		BookSearchBar(
			searchQuery = "Kotlin",
			onSearchQueryChange = {},
			onImeSearch = {},
			modifier = Modifier
				.fillMaxWidth()
		)
	}
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
	BookListScreen(
		state = BookListState(
			searchResults = books
		),
		onAction = {}
	)
}