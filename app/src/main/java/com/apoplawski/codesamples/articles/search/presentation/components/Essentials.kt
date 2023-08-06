package com.apoplawski.codesamples.articles.search.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MyText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        style = style,
        textAlign = textAlign,
    )
}

@Composable
fun MyIcon(
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