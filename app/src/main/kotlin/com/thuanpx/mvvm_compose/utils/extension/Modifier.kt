package com.thuanpx.mvvm_compose.utils.extension

import androidx.compose.ui.Modifier

/**
 * Created by ThuanPx on 31/03/2023.
 */

/**
 * https://medium.com/@aris.kotsomitopoulos/multiple-conditional-modifier-in-jetpack-compose-fe6c18ad359
 */
fun Modifier.thenIf(condition: Boolean, modifier: Modifier.() -> Modifier) =
  if (condition) {
    then(modifier(Modifier))
  } else {
    this
  }

fun Modifier.thenIf(
  condition: Boolean,
  vararg modifier: Modifier.() -> Modifier
): Modifier =
  if (condition) {
    var all: Modifier = Modifier
    modifier.forEach {
      all = all.then(it(Modifier))
    }
    then(all)
  } else {
    this
  }