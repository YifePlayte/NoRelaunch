package com.yifeplayte.norelaunch.activity.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.yifeplayte.norelaunch.R
import com.yifeplayte.norelaunch.activity.dialogs.NotActivatedDialog
import com.yifeplayte.norelaunch.activity.dialogs.RebootDialog
import com.yifeplayte.norelaunch.activity.ui.SPSwitch
import com.yifeplayte.norelaunch.utils.SharedPreferences.mSP
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getWindowSize
import top.yukonga.miuix.kmp.utils.overScrollVertical

@Composable
fun HomePage(
    navController: NavController? = null,
    currentRoute: String? = null
) {
    val scrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    val hazeState = remember { HazeState() }
    val hazeStyle = HazeStyle(
        backgroundColor = MiuixTheme.colorScheme.background,
        tint = HazeTint(
            MiuixTheme.colorScheme.background.copy(
                if (scrollBehavior.state.collapsedFraction <= 0f) 1f
                else lerp(1f, 0.67f, (scrollBehavior.state.collapsedFraction))
            )
        )
    )

    val showRebootDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                color = Color.Transparent,
                modifier = Modifier
                    .hazeEffect(hazeState) {
                        style = hazeStyle
                        blurRadius = 25.dp
                        noiseFactor = 0f
                    }
                    .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Left))
                    .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Left))
                    .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
                    .windowInsetsPadding(WindowInsets.captionBar.only(WindowInsetsSides.Top)),
                title = stringResource(R.string.app_name),
                scrollBehavior = scrollBehavior,
                defaultWindowInsetsPadding = false
            )
        },
        popupHost = { null }
    ) {
        LazyColumn(
            modifier = Modifier
                .hazeSource(state = hazeState)
                .height(getWindowSize().height.dp)
                .overScrollVertical()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Left))
                .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Left)),
            contentPadding = it,
            overscrollEffect = null
        ) {
            item {
                Spacer(Modifier.height(6.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp)
                ) {
                    SPSwitch("CONFIG_MCC", "CONFIG_MCC")
                    SPSwitch("CONFIG_MNC", "CONFIG_MNC")
                    SPSwitch("CONFIG_LOCALE", "CONFIG_LOCALE")
                    SPSwitch("CONFIG_TOUCHSCREEN", "CONFIG_TOUCHSCREEN")
                    SPSwitch("CONFIG_KEYBOARD", "CONFIG_KEYBOARD")
                    SPSwitch("CONFIG_KEYBOARD_HIDDEN", "CONFIG_KEYBOARD_HIDDEN")
                    SPSwitch("CONFIG_NAVIGATION", "CONFIG_NAVIGATION")
                    SPSwitch("CONFIG_ORIENTATION", "CONFIG_ORIENTATION")
                    SPSwitch("CONFIG_SCREEN_LAYOUT", "CONFIG_SCREEN_LAYOUT")
                    SPSwitch("CONFIG_UI_MODE", "CONFIG_UI_MODE")
                    SPSwitch("CONFIG_SCREEN_SIZE", "CONFIG_SCREEN_SIZE")
                    SPSwitch("CONFIG_SMALLEST_SCREEN_SIZE", "CONFIG_SMALLEST_SCREEN_SIZE")
                    SPSwitch("CONFIG_DENSITY", "CONFIG_DENSITY")
                    SPSwitch("CONFIG_LAYOUT_DIRECTION", "CONFIG_LAYOUT_DIRECTION")
                    SPSwitch("CONFIG_COLOR_MODE", "CONFIG_COLOR_MODE")
                    SPSwitch("CONFIG_FONT_SCALE", "CONFIG_FONT_SCALE")
                    SPSwitch("CONFIG_GRAMMATICAL_GENDER", "CONFIG_GRAMMATICAL_GENDER")
                    SPSwitch("CONFIG_FONT_WEIGHT_ADJUSTMENT", "CONFIG_FONT_WEIGHT_ADJUSTMENT")
                }
            }
            item {
                SmallTitle(
                    text = stringResource(R.string.reboot),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp)
                ) {
                    BasicComponent(
                        title = stringResource(R.string.reboot_system),
                        titleColor = BasicComponentDefaults.titleColor(
                            color = Color.Red
                        ),
                        onClick = {
                            showRebootDialog.value = true
                        }
                    )
                }
            }
            item {
                Spacer(
                    Modifier.height(
                        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                                WindowInsets.captionBar.asPaddingValues().calculateBottomPadding()
                    )
                )
            }
        }
    }

    RebootDialog(showRebootDialog)

    if (mSP == null) {
        NotActivatedDialog()
    }
}