package com.thuanpx.mvvm_architecture_compose.base.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thuanpx.mvvm_architecture_compose.R
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.AppTheme

/**
 * Created by ThuanPx on 23/06/2022.
 */

@Composable
fun EmptyView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_android), contentDescription = null)
    }
}

@Preview
@Composable
private fun PreviewEmptyView() {
    AppTheme(darkTheme = false) {
        AppGradientBackground {
            EmptyView()
        }
    }
}