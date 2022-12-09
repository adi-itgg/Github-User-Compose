@file:OptIn(ExperimentalMaterial3Api::class)

package me.syahdilla.putra.sholeh.githubusercompose.ui.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import me.syahdilla.putra.sholeh.githubusercompose.R

enum class SearchViewState {
    OPEN,
    CLOSE
}

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = Unit) { focusRequester.requestFocus() }

    TextField(text,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .focusRequester(focusRequester),
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(
                modifier = Modifier.alpha(0.4f),
                text = stringResource(id = R.string.search_query_hint),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        textStyle = MaterialTheme.typography.titleLarge,
        singleLine = true,
        leadingIcon = { AddSearchIcon() },
        trailingIcon = {
            Row {
                AddCloseIcon {
                    if (text.isNotEmpty()) {
                        onTextChanged("")
                    } else {
                        onCloseClicked()
                    }
                }
                AddFavoriteIcon()
                AddAboutIcon()
                Spacer(modifier = Modifier.width(4.dp))
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked(text)
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}