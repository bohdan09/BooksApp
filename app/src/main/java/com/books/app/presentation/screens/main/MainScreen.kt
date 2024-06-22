package com.books.app.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.books.app.R
import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.model.TopBannerSlide
import com.books.app.presentation.commoncomponents.BooksRow
import com.books.app.presentation.commoncomponents.CacheAsyncImage
import com.books.app.presentation.commoncomponents.ShimmerBrush
import com.books.app.ui.theme.NunitoSansFamily
import com.books.app.ui.theme.Theme
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
            fontFamily = NunitoSansFamily,
            fontWeight = FontWeight.Bold,
            color = Theme.colors.pink
        )
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier,
    books: List<BookAndGenre>,
    banners: List<TopBannerSlide>,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) =
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            BannerCarousel(banners = banners, openDetailsScreen = openDetailsScreen)
        }

        items(books) { book ->
            BooksGenres(bookAndGenre = book, openDetailsScreen = openDetailsScreen)
        }
    }

@Composable
private fun GenreTitle(genre: String) = Text(
    modifier = Modifier.padding(start = 16.dp),
    text = genre,
    style = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontFamily = NunitoSansFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
)

@Composable
private fun BooksGenres(
    bookAndGenre: BookAndGenre,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) = Column(
    verticalArrangement = Arrangement.spacedBy(14.dp)
) {
    GenreTitle(genre = bookAndGenre.genre)

    BooksRow(
        books = bookAndGenre.books,
        openDetailsScreen = openDetailsScreen,
        itemIsClickable = true,
        booksNameTextColor = Theme.colors.white70
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(
    banners: List<TopBannerSlide>,
    openDetailsScreen: (genre: String, bookId: String) -> Unit,
) {
    var initialPage by remember {
        mutableIntStateOf(0)
    }
    var currentBannerIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState(initialPage = initialPage) { 20_000 }
    val pageCountRange = 9000..11000

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (banners.isNotEmpty()) {
            banners.getOrNull(
                pagerState.currentPage % banners.size
            )?.let { banner ->
                currentBannerIndex = banners.indexOf(banner)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 16.dp
            )
    ) {
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
                    .padding(horizontal = 16.dp)
                    .clickable {
                        openDetailsScreen(
                            "",
                            banners[currentBannerIndex].book_id.toString()
                        )
                    },
            ) { page ->
                banners.getOrNull(
                    page % banners.size
                )?.let { item ->
                    CacheAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(167.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                brush = ShimmerBrush(
                                    targetValue = 1300f,
                                    showShimmer = true
                                )
                            ),
                        imageUrl = item.cover
                    )
                }
            }

            CarouselIndicator(
                banners = banners,
                currentBannerIndex = currentBannerIndex
            )
        }
    }
}

@Composable
fun BoxScope.CarouselIndicator(
    banners: List<TopBannerSlide>,
    currentBannerIndex: Int,
) = Row(
    Modifier
        .align(Alignment.BottomCenter)
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    horizontalArrangement = Arrangement.Center
) {
    repeat(banners.size) { iteration ->
        val color =
            if (currentBannerIndex == iteration) Theme.colors.pink else Theme.colors.frenchGray
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .clip(CircleShape)
                .background(color)
                .size(7.dp)
        )
    }
}