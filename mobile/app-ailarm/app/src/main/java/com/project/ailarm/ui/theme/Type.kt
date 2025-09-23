package com.project.ailarm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.project.ailarm.R

// Proveedor de Google Fonts (Play Services)
private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// Familia Roboto con pesos comunes
private val roboto = GoogleFont("Roboto")

val RobotoFamily = FontFamily(
    Font(roboto, provider, FontWeight.Normal),
    Font(roboto, provider, FontWeight.Medium),
    Font(roboto, provider, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(fontFamily = RobotoFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp, color = TextColor),
    bodyMedium = TextStyle(fontFamily = RobotoFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, color = TextColor),
    titleLarge = TextStyle(fontFamily = RobotoFamily, fontWeight = FontWeight.Medium, fontSize = 22.sp, color = TextColor),
    titleMedium = TextStyle(fontFamily = RobotoFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = TextColor),
    headlineSmall = TextStyle(fontFamily = RobotoFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = TextColor),
    labelSmall = TextStyle(fontFamily = RobotoFamily, fontWeight = FontWeight.Medium, fontSize = 12.sp, color = TextColor)
)

val AppBarTitle = TextStyle(
    fontFamily = RobotoFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = 0.sp,
    color = TextColor,
)
