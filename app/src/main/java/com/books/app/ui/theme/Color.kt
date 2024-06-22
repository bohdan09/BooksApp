package com.books.app.ui.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    val black = Color(0xFF000000)
    val white = Color(0xffffffff)
    val white80 = Color(0xCCFFFFFF)
    val white70 = Color(0xB3FFFFFF)
    val white20 = Color(0x33FFFFFF)
    val frenchGray = Color(0xffC1C2CA)
    val cinder = Color(0xff0B080F)
    val alto = Color(0xffD9D5D6)
    val mineShaft = Color(0xff393637)
    val pink = Color(0xffDD48A1)
}

interface ColorPalette {
    val primary: Color
    val basic: Color
    val white80: Color
    val white70: Color
    val white20: Color
    val frenchGray: Color
    val cinder: Color
    val alto: Color
    val mineShaft: Color
    val pink: Color

}

fun colorPalette(): ColorPalette = object : ColorPalette {
    override val primary: Color = AppColors.black
    override val basic: Color = AppColors.white
    override val white80: Color = AppColors.white80
    override val white20: Color = AppColors.white20
    override val white70: Color = AppColors.white70
    override val frenchGray: Color = AppColors.frenchGray
    override val cinder: Color = AppColors.cinder
    override val alto: Color = AppColors.alto
    override val mineShaft: Color = AppColors.mineShaft
    override val pink: Color = AppColors.pink

}