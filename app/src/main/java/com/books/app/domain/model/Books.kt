package com.books.app.domain.model

data class Books(
    val books: List<Book>,
    val top_banner_slides: List<TopBannerSlide>,
    val you_will_like_section: List<Int>,
)