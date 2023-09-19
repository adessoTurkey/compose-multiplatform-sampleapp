package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.MR
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun TextInputItem(
    modifier: Modifier = Modifier,
    query: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null

) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        value = query,
        onValueChange = { onValueChange.invoke(it) },
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(5.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        label = label,
        visualTransformation = visualTransformation,
        isError = isError,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    query: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        value = query,
        onValueChange = { onValueChange.invoke(it) },
        leadingIcon = {
            Icon(painter = painterResource(MR.images.ic_tabbar_search), contentDescription = null)
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onValueChange.invoke("") },
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        },
        placeholder = {
            TextItem(
                text = "Movies or Series",
                textColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                fontSize = 17.sp
            )
        },
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        isError = isError,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            errorContainerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    )
}