package com.books.app.presentation.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.books.app.R
import com.books.app.domain.model.Book
import com.books.app.presentation.commoncomponents.BooksRow
import com.books.app.presentation.commoncomponents.CacheAsyncImage
import com.books.app.presentation.commoncomponents.HorizontalSpacer
import com.books.app.presentation.commoncomponents.ShimmerBrush
import com.books.app.ui.theme.NunitoSansFamily
import com.books.app.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.math.absoluteValue

@Composable
fun DetailsScreen(
    bookId: String,
    genre: String,
    onBackClick: () -> Unit,
) = CompositionLocalProvider(
    androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
) {
    val viewModel: DetailsScreenViewModel =
        koinViewModel(parameters = { parametersOf(bookId, genre) })
    val booksByGenre by viewModel.booksByGenreState.collectAsStateWithLifecycle()
    val selectedBook by viewModel.selectedBookState.collectAsStateWithLifecycle()
    val recommendationBooks by viewModel.recommendationBooksState.collectAsStateWithLifecycle()

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .navigationBarsPadding()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(440.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.details_background),
            contentDescription = null
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(onBackClick = onBackClick) },
            containerColor = Color.Transparent
        ) {
            DetailsScreenContent(
                modifier = Modifier.padding(it),
                booksByGenre = booksByGenre,
                selectedBook = selectedBook,
                recommendationBooks = recommendationBooks,
                setSelectedBook = viewModel::setSelectedBook
            )
        }
    }
}

@Composable
private fun TopBar(onBackClick: () -> Unit) = Box {
    IconButton(
        modifier = Modifier.statusBarsPadding(),
        onClick = { onBackClick() }) {
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null
        )
    }
}

@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    booksByGenre: List<Book>,
    selectedBook: Book,
    recommendationBooks: List<Book>,
    setSelectedBook: (bookId: String) -> Unit,
) = Column(modifier = modifier.fillMaxSize()) {
    CarouselBlock(
        booksByGenre = booksByGenre.ifEmpty { emptyList() },
        selectedBook = selectedBook,
        setSelectedBook = setSelectedBook
    )
    InfoBlock(
        selectedBook = selectedBook,
        recommendationBooks = recommendationBooks
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CarouselBlock(
    modifier: Modifier = Modifier,
    booksByGenre: List<Book>,
    selectedBook: Book,
    setSelectedBook: (bookId: String) -> Unit,
) =
    Box(modifier = modifier.wrapContentSize()) {

        if (booksByGenre.isNotEmpty()) {
            var pagerWidth by remember { mutableStateOf(0.dp) }
            var pagerItemWidth by remember { mutableStateOf(0.dp) }

            val density = LocalDensity.current
            val pagerState = rememberPagerState(initialPage = 0) { booksByGenre.size }

            LaunchedEffect(key1 = selectedBook) {
                pagerState.scrollToPage(booksByGenre.indexOf(selectedBook))
            }
            LaunchedEffect(key1 = pagerState.currentPage) {
                setSelectedBook(booksByGenre[pagerState.currentPage].id.toString())
            }

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    start = (pagerWidth - pagerItemWidth) / 2,
                    end = (pagerWidth - pagerItemWidth) / 2,
                ),
                modifier = Modifier
                    .wrapContentWidth()
                    .onGloballyPositioned {
                        pagerWidth = with(density) {
                            it.size.width.toDp()
                        }
                    },
                pageSpacing = -(5).dp,
            ) { page ->
                CarouselItem(
                    imageUrl = booksByGenre[page].cover_url,
                    title = booksByGenre[page].name,
                    author = booksByGenre[page].author,
                    setPagerItemWidth = { pagerItemWidth = it },
                    pagerState = pagerState,
                    currentPage = page
                )
            }
        }
    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CarouselItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    author: String,
    setPagerItemWidth: (Dp) -> Unit,
    pagerState: PagerState,
    currentPage: Int,
) = Column(modifier = modifier.height(330.dp)) {
    val density = LocalDensity.current

    CacheAsyncImage(
        modifier = Modifier
            .size(200.dp, 250.dp)
            .onGloballyPositioned {
                setPagerItemWidth(with(density) {
                    it.size.width.toDp()
                }
                )
            }
            .graphicsLayer {
                val pageOffset = (
                        (pagerState.currentPage - currentPage) + pagerState.currentPageOffsetFraction
                        ).absoluteValue

                scaleY = lerp(
                    start = 1f,
                    stop = 0.8f,
                    fraction = pageOffset.coerceIn(0.1f, 1f)
                )

                scaleX = lerp(
                    start = 0.95f,
                    stop = 0.78f,
                    fraction = pageOffset.coerceIn(0.1f, 0.7f)
                )
            }
            .clip(RoundedCornerShape(16.dp))
            .background(brush = ShimmerBrush(targetValue = 1300f, showShimmer = true)),
        imageUrl = imageUrl,
        contentScale = ContentScale.FillBounds,
    )

    HorizontalSpacer(height = 12.dp)

    AnimatedVisibility(
        visible = pagerState.currentPage == currentPage,
        enter = fadeIn(animationSpec = tween(1000)) + expandVertically(),
        exit = fadeOut(animationSpec = tween(1000)) + shrinkVertically()
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontFamily = NunitoSansFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
                    color = Theme.colors.basic
                )
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = author,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 15.4.sp,
                    fontFamily = NunitoSansFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
                    color = Theme.colors.white80
                )
            )
        }
    }
}

@Composable
private fun InfoBlock(
    modifier: Modifier = Modifier,
    selectedBook: Book,
    recommendationBooks: List<Book>,
) {
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = selectedBook) {
        scrollState.animateScrollTo(0)
    }
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            StatisticItem(
                title = stringResource(id = R.string.readers),
                statisticValue = selectedBook.views
            )
            StatisticItem(
                title = stringResource(id = R.string.likes),
                statisticValue = selectedBook.likes
            )
            StatisticItem(
                title = stringResource(id = R.string.quotes),
                statisticValue = selectedBook.quotes
            )
            StatisticItem(
                title = stringResource(id = R.string.genre),
                statisticValue = selectedBook.genre
            )
        }

        SummeryItem(summery = selectedBook.summary)

        YouWillAlsoLikeItem(recommendationBooks = recommendationBooks)

        ReadNowButton()
    }
}


@Composable
private fun YouWillAlsoLikeItem(recommendationBooks: List<Book>) = Column {
    HeaderText(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        text = stringResource(id = R.string.also_like)
    )

    HorizontalSpacer(height = 16.dp)

    BooksRow(
        books = recommendationBooks,
        itemIsClickable = false,
        booksNameTextColor = Theme.colors.mineShaft
    )
}

@Composable
private fun SummeryItem(summery: String) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
        .padding(
            horizontal = 16.dp,
        ),
) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Theme.colors.alto
    )

    HorizontalSpacer(height = 16.dp)

    HeaderText(text = stringResource(id = R.string.summary))

    HorizontalSpacer(height = 8.dp)

    Text(
        text = summery,
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.8.sp,
            fontFamily = NunitoSansFamily,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = TextUnit((0.150F), TextUnitType.Unspecified),
            color = Color.Black
        )
    )

    HorizontalSpacer(height = 16.dp)

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Theme.colors.alto
    )

    HorizontalSpacer(height = 16.dp)
}

@Composable
private fun StatisticItem(title: String, statisticValue: String) = Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = statisticValue,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontFamily = NunitoSansFamily,
            fontWeight = FontWeight.Bold,
            letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
            color = Color.Black
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

    Text(
        text = title,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 12.sp,
            lineHeight = 13.2.sp,
            fontFamily = NunitoSansFamily,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
            color = Theme.colors.alto
        )
    )
}

@Composable
private fun HeaderText(modifier: Modifier = Modifier, text: String) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontFamily = NunitoSansFamily,
        fontWeight = FontWeight.Bold,
        letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
        color = Color.Black
    )
)

@Composable
fun ReadNowButton(modifier: Modifier = Modifier) =
    Button(
        modifier = modifier
            .padding(horizontal = 49.dp, vertical = 24.dp)
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(30.dp),
        onClick = {
            // no-op
        },
        colors = ButtonDefaults.buttonColors(Theme.colors.pink)
    ) {
        Text(
            text = stringResource(id = R.string.read_now),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = NunitoSansFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        )
    }
