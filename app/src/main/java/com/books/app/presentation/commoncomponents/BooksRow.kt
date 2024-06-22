package com.books.app.presentation.commoncomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.books.app.R
import com.books.app.domain.model.Book

@Composable
fun BooksRow(
    books: List<Book>,
    booksNameTextColor: Color,
    itemIsClickable: Boolean,
    openDetailsScreen: (genre: String, bookId: String) -> Unit = { _, _ -> },
) =
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        books.forEachIndexed { index, itemBook ->
            BookCoverItem(
                modifier = Modifier.let {
                    when (index) {
                        0 -> it.padding(start = 16.dp)
                        books.lastIndex -> it.padding(end = 16.dp)
                        else -> it
                    }
                },
                book = itemBook,
                booksNameTextColor = booksNameTextColor,
                itemIsClickable = itemIsClickable,
                openDetailsScreen = openDetailsScreen
            )
        }
    }


@Composable
private fun BookCoverItem(
    modifier: Modifier,
    book: Book,
    booksNameTextColor: Color,
    itemIsClickable: Boolean,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) = Column(
    modifier = modifier
        .width(120.dp)
        .clickable(itemIsClickable) {
            openDetailsScreen(book.genre, book.id.toString())
        },
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    CacheAsyncImage(
        modifier = Modifier
            .size(width = 120.dp, height = 150.dp),
        imageUrl = book.cover_url
    )

    Text(
        text = book.name,
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
            fontWeight = FontWeight.Bold,
            color = booksNameTextColor
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}