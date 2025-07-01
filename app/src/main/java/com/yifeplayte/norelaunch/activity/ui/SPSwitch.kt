package com.yifeplayte.norelaunch.activity.ui

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yifeplayte.norelaunch.utils.SharedPreferences.get
import com.yifeplayte.norelaunch.utils.SharedPreferences.mSP
import com.yifeplayte.norelaunch.utils.SharedPreferences.put
import top.yukonga.miuix.kmp.extra.SuperSwitch

@Composable
fun SPSwitch(
    key: String,
    title: String,
    summary: String? = null,
    defaultValue: Boolean = false,
    sharedPreferences: SharedPreferences? = mSP,
) {
    val switchState = remember { mutableStateOf(sharedPreferences.get(key, defaultValue)) }
    SuperSwitch(
        title = title,
        summary = summary,
        checked = switchState.value,
        onCheckedChange = {
            switchState.value = it
            sharedPreferences.put(key, it)
        }
    )
}