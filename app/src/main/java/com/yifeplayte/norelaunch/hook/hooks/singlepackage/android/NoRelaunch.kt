package com.yifeplayte.norelaunch.hook.hooks.singlepackage.android

import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.CONFIG_MCC
import android.content.pm.ActivityInfo.CONFIG_MNC
import android.content.pm.ActivityInfo.CONFIG_LOCALE
import android.content.pm.ActivityInfo.CONFIG_TOUCHSCREEN
import android.content.pm.ActivityInfo.CONFIG_KEYBOARD
import android.content.pm.ActivityInfo.CONFIG_KEYBOARD_HIDDEN
import android.content.pm.ActivityInfo.CONFIG_NAVIGATION
import android.content.pm.ActivityInfo.CONFIG_ORIENTATION
import android.content.pm.ActivityInfo.CONFIG_SCREEN_LAYOUT
import android.content.pm.ActivityInfo.CONFIG_UI_MODE
import android.content.pm.ActivityInfo.CONFIG_SCREEN_SIZE
import android.content.pm.ActivityInfo.CONFIG_SMALLEST_SCREEN_SIZE
import android.content.pm.ActivityInfo.CONFIG_DENSITY
import android.content.pm.ActivityInfo.CONFIG_LAYOUT_DIRECTION
import android.content.pm.ActivityInfo.CONFIG_COLOR_MODE
import android.content.pm.ActivityInfo.CONFIG_GRAMMATICAL_GENDER
import android.content.pm.ActivityInfo.CONFIG_FONT_SCALE
import android.content.pm.ActivityInfo.CONFIG_FONT_WEIGHT_ADJUSTMENT
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.Log
import com.github.kyuubiran.ezxhelper.ObjectUtils.getObjectOrNullAs
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.yifeplayte.norelaunch.hook.hooks.BaseHook
import com.yifeplayte.norelaunch.hook.utils.XSharedPreferences.getBoolean

@Suppress("unused")
object NoRelaunch : BaseHook() {
    override val key = "no_relaunch"
    override val isEnabled = true
    override fun hook() {
        loadClass("com.android.server.wm.ActivityRecord").methodFinder()
            .filterByName("shouldRelaunchLocked").filterNonAbstract().single().createHook {
                before { param ->
                    val ignoredConfigs: Int = listOf(
                        "CONFIG_MCC" to CONFIG_MCC,
                        "CONFIG_MNC" to CONFIG_MNC,
                        "CONFIG_LOCALE" to CONFIG_LOCALE,
                        "CONFIG_TOUCHSCREEN" to CONFIG_TOUCHSCREEN,
                        "CONFIG_KEYBOARD" to CONFIG_KEYBOARD,
                        "CONFIG_KEYBOARD_HIDDEN" to CONFIG_KEYBOARD_HIDDEN,
                        "CONFIG_NAVIGATION" to CONFIG_NAVIGATION,
                        "CONFIG_ORIENTATION" to CONFIG_ORIENTATION,
                        "CONFIG_SCREEN_LAYOUT" to CONFIG_SCREEN_LAYOUT,
                        "CONFIG_UI_MODE" to CONFIG_UI_MODE,
                        "CONFIG_SCREEN_SIZE" to CONFIG_SCREEN_SIZE,
                        "CONFIG_SMALLEST_SCREEN_SIZE" to CONFIG_SMALLEST_SCREEN_SIZE,
                        "CONFIG_DENSITY" to CONFIG_DENSITY,
                        "CONFIG_LAYOUT_DIRECTION" to CONFIG_LAYOUT_DIRECTION,
                        "CONFIG_COLOR_MODE" to CONFIG_COLOR_MODE,
                        "CONFIG_GRAMMATICAL_GENDER" to CONFIG_GRAMMATICAL_GENDER,
                        "CONFIG_FONT_SCALE" to CONFIG_FONT_SCALE,
                        "CONFIG_FONT_WEIGHT_ADJUSTMENT" to CONFIG_FONT_WEIGHT_ADJUSTMENT
                    ).fold(0) { acc, (config, value) ->
                        acc or if (getBoolean(config, false)) value else 0
                    }

                    param.args[0] = (param.args[0] as Int) and ignoredConfigs.inv()
                }
            }
    }

    private fun configChangesToString(configChanges: Int): String = buildString {
        if (configChanges and CONFIG_MCC > 0) append("|mcc")
        if (configChanges and CONFIG_MNC > 0) append("|mnc")
        if (configChanges and CONFIG_LOCALE > 0) append("|locale")
        if (configChanges and CONFIG_TOUCHSCREEN > 0) append("|touchscreen")
        if (configChanges and CONFIG_KEYBOARD > 0) append("|keyboard")
        if (configChanges and CONFIG_KEYBOARD_HIDDEN > 0) append("|keyboardHidden")
        if (configChanges and CONFIG_NAVIGATION > 0) append("|navigation")
        if (configChanges and CONFIG_ORIENTATION > 0) append("|orientation")
        if (configChanges and CONFIG_SCREEN_LAYOUT > 0) append("|screenLayout")
        if (configChanges and CONFIG_UI_MODE > 0) append("|uiMode")
        if (configChanges and CONFIG_SCREEN_SIZE > 0) append("|screenSize")
        if (configChanges and CONFIG_SMALLEST_SCREEN_SIZE > 0) append("|smallestScreenSize")
        if (configChanges and CONFIG_DENSITY > 0) append("|density")
        if (configChanges and CONFIG_LAYOUT_DIRECTION > 0) append("|layoutDirection")
        if (configChanges and CONFIG_COLOR_MODE > 0) append("|colorMode")
        if (configChanges and CONFIG_GRAMMATICAL_GENDER > 0) append("|grammaticalGender")
        if (configChanges and CONFIG_FONT_SCALE > 0) append("|fontScale")
        if (configChanges and CONFIG_FONT_WEIGHT_ADJUSTMENT > 0) append("|fontWeightAdjustment")
    }.trim('|')
}