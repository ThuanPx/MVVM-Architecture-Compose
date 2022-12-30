package com.thuanpx.mvvm_compose.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thuanpx.mvvm_compose.R
import com.thuanpx.mvvm_compose.core.DevicePreviews
import com.thuanpx.mvvm_compose.core.LandscapeDevicePreviews
import com.thuanpx.mvvm_compose.core.PhoneDevicePreviews
import com.thuanpx.mvvm_compose.core.TabletDevicePreviews
import com.thuanpx.mvvm_compose.core.navigation.AppComposeNavigator
import com.thuanpx.mvvm_compose.core.navigation.AppScreens
import com.thuanpx.mvvm_compose.core.navigation.ComposeNavigatorDefault
import com.thuanpx.mvvm_compose.designsystem.component.AppBackground
import com.thuanpx.mvvm_compose.designsystem.theme.AppTheme
import kotlinx.coroutines.delay

/**
 * Created by ThuanPx on 02/01/2023.
 */

@Composable
fun LoginScreen(
  composeNavigator: AppComposeNavigator,
) {
  var isShow by remember { mutableStateOf(true) }
  LaunchedEffect(key1 = Unit, block = {
    delay(1_000)
    isShow = false
    delay(500)
    composeNavigator.navigateAndClearBackStack(AppScreens.Home.name)
  })
  Scaffold { padding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      AnimatedVisibility(
        visible = isShow,
        exit = fadeOut(),
        modifier = Modifier
          .align(Alignment.Center)
      ) {
        Image(
          painter = painterResource(id = R.mipmap.ic_launcher_foreground),
          contentDescription = null,
          modifier = Modifier
            .size(200.dp)
        )
      }
    }
  }
}

@PhoneDevicePreviews
@Composable
fun PreviewLoginScreen() {
  AppTheme {
    LoginScreen(
      composeNavigator = ComposeNavigatorDefault(),
    )
  }
}

