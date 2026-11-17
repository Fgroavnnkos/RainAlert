package com.example.test.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.Triodion
import com.example.test.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val AppColorScheme = lightColorScheme(

    primary = AppColors.primary,
    onPrimary = AppColors.onPrimary,

    secondary = AppColors.secondary,
    onSecondary = AppColors.onSecondary,

    background = AppColors.background,
    onBackground = AppColors.onBackground,

    surface = AppColors.surface,
    onSurface = AppColors.onSurface,

    surfaceVariant = AppColors.surfaceVariant,
    onSurfaceVariant = AppColors.onSurfaceVariant,

    outline = AppColors.outline,

    error = AppColors.error,
    onError = AppColors.onError
)


@Composable
fun TestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val appTypography = typography.copy(

        displayLarge = typography.displayLarge.copy(
            fontFamily = Triodion,
            fontWeight = FontWeight.Bold
        ),

        displayMedium = typography.displayMedium.copy(
            fontFamily = Triodion,
            fontWeight = FontWeight.Bold
        ),

        displaySmall = typography.displaySmall.copy(
            fontFamily = Triodion,
            fontWeight = FontWeight.Bold
        ),

        headlineLarge = typography.headlineLarge.copy(
            fontFamily = Triodion
        ),

        headlineMedium = typography.headlineMedium.copy(
            fontFamily = Triodion
        ),

        headlineSmall = typography.headlineSmall.copy(
            fontFamily = Triodion
        ),

        titleLarge = typography.titleLarge.copy(
            fontFamily = Triodion
        ),

        titleMedium = typography.titleMedium.copy(
            fontFamily = Triodion
        ),

        titleSmall = typography.titleSmall.copy(
            fontFamily = Triodion
        ),

        bodyLarge = typography.bodyLarge.copy(
            fontFamily = Triodion
        ),

        bodyMedium = typography.bodyMedium.copy(
            fontFamily = Triodion
        ),

        bodySmall = typography.bodySmall.copy(
            fontFamily = Triodion
        ),

        labelLarge = typography.labelLarge.copy(
            fontFamily = Triodion,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),

        labelMedium = typography.labelMedium.copy(
            fontFamily = Triodion,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        ),

        labelSmall = typography.labelSmall.copy(
            fontFamily = Triodion,
            fontWeight = FontWeight.Bold
        )
    )

    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = appTypography,
        shapes = shapes.copy(
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}