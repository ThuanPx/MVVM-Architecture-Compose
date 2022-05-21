package com.thuanpx.mvvm_architecture_compose.base.ui.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Created by ThuanPx on 5/25/22.
 */

@Composable
fun AppErrorAlertDialog(text: String) {
    val isOpenDialog = remember { mutableStateOf(true) }
    if (isOpenDialog.value) {
        AlertDialog(
            title = { Text(text = "Error!") },
            text = { Text(text = text) },
            confirmButton = {
                TextButton(
                    modifier = Modifier
                        .wrapContentSize(),
                    onClick = { isOpenDialog.value = false }
                ) {
                    Text("OK")
                }
            },
            onDismissRequest = { isOpenDialog.value = false }
        )
    }
}