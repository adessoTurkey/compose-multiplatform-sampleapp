package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

const val DEFAULT_MINIMUM_TEXT_LINE = 5

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "Show More >>",
    showMoreStyle: SpanStyle = SpanStyle(
        fontWeight = FontWeight.W500,
        color = MaterialTheme.colorScheme.primary
    ),
    showLessText: String = "Show Less",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableStateOf(0) }
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth().animateContentSize(),
            text =
            buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        append(text)
                    } else {
                        val adjustText =
                            text
                                .substring(startIndex = 0, endIndex = lastCharIndex)
                                .dropLast(showMoreText.length)
                                .dropLastWhile { it == '.' }
                        append("$adjustText...")
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = textAlign
        )

        if (clickable) {
            Text(
                text = if (isExpanded) showLessText else showMoreText,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .padding(top = 8.dp)
            )
        }
    }
}
