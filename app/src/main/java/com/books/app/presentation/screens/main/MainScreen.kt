package com.books.app.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.books.app.R
import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import com.books.app.presentation.commoncomponents.ShimmerBrush
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) = CompositionLocalProvider(
    androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        containerColor = Color.Black
    ) {
        val viewModel: MainScreenViewModel = koinViewModel()

        val book by viewModel.booksState.collectAsStateWithLifecycle()
        val banners by viewModel.bannersState.collectAsStateWithLifecycle()
        MainScreenContent(
            modifier = Modifier.padding(it),
            books = book,
            banners = banners,
            openDetailsScreen = openDetailsScreen
        )
    }
}

@Composable
private fun TopBar() = Box {
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            top = 50.dp,
            bottom = 8.dp
        ),
        text = stringResource(id = R.string.library),
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
            fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
            fontWeight = FontWeight.Bold,
            color = Color(0xffD0006E)
        )
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier,
    books: List<BookAndGenre>,
    banners: List<Book>,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) =
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            BannerCarousel(banners = banners)
        }

        items(books) { book ->
            MoviesGenres(book = book, openDetailsScreen = openDetailsScreen)
        }
    }

@Composable
private fun GenreTitle(genre: String) = Text(
    modifier = Modifier.padding(start = 16.dp),
    text = genre,
    style = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
)


@Composable
private fun MovieItem(
    modifier: Modifier,
    book: Book,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) = Column(
    modifier = modifier
        .width(120.dp)
        .clickable {
            openDetailsScreen(book.genre, book.id.toString())
        },
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    val showShimmer by remember { mutableStateOf(true) }
    AsyncImage(
        model = book.cover_url,
        modifier = Modifier
            .size(width = 120.dp, height = 150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(brush = ShimmerBrush(targetValue = 1300f, showShimmer = showShimmer)),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )

    Text(
        text = book.name,
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
            fontWeight = FontWeight.Bold,
            color = Color(0xB3FFFFFF)
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
private fun MoviesGenres(
    book: BookAndGenre,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) = Column(
    verticalArrangement = Arrangement.spacedBy(14.dp)
) {
    GenreTitle(genre = book.genre)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        book.books.forEachIndexed { index, itemBook ->
            MovieItem(
                modifier = Modifier.let {
                    when (index) {
                        0 -> it.padding(start = 16.dp)
                        book.books.lastIndex -> it.padding(end = 16.dp)
                        else -> it
                    }
                },
                book = itemBook,
                openDetailsScreen = openDetailsScreen
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(banners: List<Book>) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            bottom = 16.dp
        )
) {
    var initialPage by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState(initialPage = initialPage) { 20_000 }
    val pageCountRange = 9000..11000

    if (banners.isNotEmpty()) {
        LaunchedEffect(Unit) {
            pageCountRange.forEach {
                if (it % banners.size == 0) {
                    initialPage = it
                    pagerState.scrollToPage(it)
                    return@LaunchedEffect
                }
            }
        }

        LaunchedEffect(pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                while (true) {
                    delay(3000)
                    pagerState.scrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp),
        ) { page ->
            banners.getOrNull(
                page % banners.size
            )?.let { item ->
                AsyncImage(
                    model = item.cover_url,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(167.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(brush = ShimmerBrush(targetValue = 1300f, showShimmer = true)),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }

        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            banners.getOrNull(
                pagerState.currentPage % banners.size
            )?.let { item ->
                repeat(banners.size) { iteration ->
                    val color =
                        if (banners.indexOf(item) == iteration) Color(0xffD0006E) else Color(
                            0xffC1C2CA
                        )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(7.dp)
                    )
                }
            }
        }
    }
}
