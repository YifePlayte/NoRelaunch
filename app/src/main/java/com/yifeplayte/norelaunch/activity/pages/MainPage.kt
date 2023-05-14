package com.yifeplayte.norelaunch.activity.pages

import android.annotation.SuppressLint
import android.widget.Toast
import cn.fkj233.ui.activity.annotation.BMMainPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.dialog.MIUIDialog
import com.yifeplayte.norelaunch.R
import com.yifeplayte.norelaunch.hook.PACKAGE_NAME_HOOKED
import com.yifeplayte.norelaunch.utils.Terminal

@SuppressLint("NonConstantResourceId")
@BMMainPage(titleId = R.string.app_name)
class MainPage : BasePage() {
    override fun onCreate() {
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_MCC"), SwitchV("CONFIG_MCC", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_MNC"), SwitchV("CONFIG_MNC", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_LOCALE"), SwitchV("CONFIG_LOCALE", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_TOUCHSCREEN"), SwitchV("CONFIG_TOUCHSCREEN", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_KEYBOARD"), SwitchV("CONFIG_KEYBOARD", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_KEYBOARD_HIDDEN"), SwitchV("CONFIG_KEYBOARD_HIDDEN", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_NAVIGATION"), SwitchV("CONFIG_NAVIGATION", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_ORIENTATION"), SwitchV("CONFIG_ORIENTATION", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_SCREEN_LAYOUT"), SwitchV("CONFIG_SCREEN_LAYOUT", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_UI_MODE"), SwitchV("CONFIG_UI_MODE", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_SCREEN_SIZE"), SwitchV("CONFIG_SCREEN_SIZE", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_SMALLEST_SCREEN_SIZE"), SwitchV("CONFIG_SMALLEST_SCREEN_SIZE", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_DENSITY"), SwitchV("CONFIG_DENSITY", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_LAYOUT_DIRECTION"), SwitchV("CONFIG_LAYOUT_DIRECTION", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_COLOR_MODE"), SwitchV("CONFIG_COLOR_MODE", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_GRAMMATICAL_GENDER"), SwitchV("CONFIG_GRAMMATICAL_GENDER", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_FONT_SCALE"), SwitchV("CONFIG_FONT_SCALE", false))
        TextSummaryWithSwitch(TextSummaryV(text = "CONFIG_FONT_WEIGHT_ADJUSTMENT"), SwitchV("CONFIG_FONT_WEIGHT_ADJUSTMENT", false))

        TitleText(textId = R.string.reboot)
        TextSummaryWithArrow(TextSummaryV(
            textId = R.string.reboot_system
        ) {
            MIUIDialog(activity) {
                setTitle(R.string.warning)
                setMessage(R.string.reboot_tips)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.done) {
                    Terminal.exec("/system/bin/sync;/system/bin/svc power reboot || reboot")
                }
            }.show()
        })
    }
}