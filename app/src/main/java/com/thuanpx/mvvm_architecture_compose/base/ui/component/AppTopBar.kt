package com.thuanpx.mvvm_architecture_compose.base.ui.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thuanpx.mvvm_architecture_compose.R

/**
 * Created by ThuanPx on 5/22/22.
 */

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    actionIcon: ImageVector,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(id = titleRes)) }, navigationIcon = {
        IconButton(onClick = onNavigationClick) {
            Icon(
                imageVector = navigationIcon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }, actions = {
        IconButton(onClick = onActionClick) {
            Icon(imageVector = actionIcon, contentDescription = null, tint = MaterialTheme.colorScheme.surface)
        }
    }, colors = colors, modifier = modifier
    )
}

@Preview("Top App Bar")
@Composable
fun TopAppBarPreview() {
    AppTopBar(
        titleRes = R.string.home,
        navigationIcon = Icons.Default.Search,
        actionIcon = Icons.Default.MoreVert,
    )
}