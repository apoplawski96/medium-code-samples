package com.apoplawski.codesamples.screens.search.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun MIcon(
    @DrawableRes resource: Int,
    tint: Color,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
) {
    Icon(
        painter = painterResource(id = resource),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier then Modifier.semantics {
            this.contentDescription = contentDescription
        },
    )
}