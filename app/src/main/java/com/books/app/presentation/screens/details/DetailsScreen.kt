package com.books.app.presentation.screens.details

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
import coil.compose.AsyncImage
import com.books.app.R
import com.books.app.domain.model.Book
import com.books.app.presentation.commoncomponents.HorizontalSpacer
import com.books.app.presentation.commoncomponents.ShimmerBrush
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
    val books by viewModel.booksState.collectAsStateWithLifecycle()

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
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
            DetailsScreenContent(Modifier.padding(it), books = books)
        }
    }
}

@Composable
private fun TopBar(onBackClick: () -> Unit) = Box {
    IconButton(
        modifier = Modifier.padding(
            start = 16.dp,
            top = 50.dp,
            bottom = 8.dp
        ),
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
    books: List<Book>,
) =
    Column(modifier = modifier.fillMaxSize()) {
        CarouselBlock(books = if (books.isNotEmpty()) books else emptyList())
        InfoBlock(book = if (books.isNotEmpty()) books[0] else Book.default())
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselBlock(modifier: Modifier = Modifier, books: List<Book>) =
    Box(modifier = modifier.wrapContentSize()) {

        if (books.isNotEmpty()) {
            var pagerWidth by remember { mutableStateOf(0.dp) }
            var pagerItemWidth by remember { mutableStateOf(0.dp) }

            val density = LocalDensity.current
            val pagerState = rememberPagerState(initialPage = 0) { books.size }

            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fixed(200.dp),
                contentPadding = PaddingValues(
                    start = (pagerWidth - pagerItemWidth) / 2,
                    end = (pagerWidth - pagerItemWidth) / 2,
                    top = 10.dp
                ),
                modifier = Modifier
                    .wrapContentWidth()
                    .onGloballyPositioned {
                        pagerWidth = with(density) {
                            it.size.width.toDp()
                        }
                    },
                pageSpacing = 16.dp,
            ) { page ->
                CarouselItem(
                    imageUrl = books[page].cover_url,
                    title = books[page].name,
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
    setPagerItemWidth: (Dp) -> Unit,
    pagerState: PagerState,
    currentPage: Int,
) = Column(modifier = modifier.height(313.dp)) {
    val density = LocalDensity.current

    AsyncImage(
        model = imageUrl,
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
            }
            .clip(RoundedCornerShape(16.dp))
            .background(brush = ShimmerBrush(targetValue = 1300f, showShimmer = true)),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )

    HorizontalSpacer(height = 16.dp)

    AnimatedVisibility(visible = pagerState.currentPage == currentPage) {

        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
                    color = Color(0xB3FFFFFF)
                )
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 15.4.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
                    color = Color(0xB3FFFFFF)
                )
            )
        }
    }
}

@Composable
fun InfoBlock(modifier: Modifier = Modifier, book: Book) = Column(
    modifier = modifier
        .offset(y = (20).dp)
        .clip(
            RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        )
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState())
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 38.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        StatisticItem(
            title = stringResource(id = R.string.readers),
            statisticValue = book.views
        )
        StatisticItem(
            title = stringResource(id = R.string.likes),
            statisticValue = book.likes
        )
        StatisticItem(
            title = stringResource(id = R.string.quotes),
            statisticValue = book.quotes
        )
        StatisticItem(
            title = stringResource(id = R.string.genre),
            statisticValue = book.genre
        )
    }

    SummeryItem(summery = book.summary)


}

@Composable
private fun YouWillAlsoLikeItem(summery: String) = Column {

}

@Composable
private fun SummeryItem(summery: String) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 16.dp,
        ),
) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color(0xffD9D5D6)
    )

    HorizontalSpacer(height = 16.dp)

    Text(
        text = stringResource(id = R.string.summary),
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
            fontWeight = FontWeight.Bold,
            letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
            color = Color.Black
        )
    )

    HorizontalSpacer(height = 8.dp)

    Text(
        text = summery,
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.8.sp,
            fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.SemiBold)),
            fontWeight = FontWeight.SemiBold,
            letterSpacing = TextUnit((0.150F), TextUnitType.Unspecified),
            color = Color.Black
        )
    )

    HorizontalSpacer(height = 16.dp)

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color(0xffD9D5D6)
    )

    HorizontalSpacer(height = 16.dp)
}

@Composable
private fun StatisticItem(title: String, statisticValue: String) =
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statisticValue,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Bold)),
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
                fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.SemiBold)),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = TextUnit(-(0.408F), TextUnitType.Unspecified),
                color = Color(0xffD9D5D6)
            )
        )

    }