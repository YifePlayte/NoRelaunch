package com.yifeplayte.norelaunch.hook.hooks

import com.github.kyuubiran.ezxhelper.Log
import com.github.kyuubiran.ezxhelper.LogExtensions.logexIfThrow
import com.yifeplayte.norelaunch.hook.utils.XSharedPreferences.getBoolean

abstract class BaseSubHook {
    private var isInit: Boolean = false
    abstract val key: String
    abstract fun hook(subClassLoader: ClassLoader)
    open val isEnabled get() = getBoolean(key, false)
    fun init(subClassLoader: ClassLoader) {
        if (isInit) return
        if (isEnabled) runCatching {
            hook(subClassLoader)
            isInit = true
            Log.ix("Inited hook: ${this.javaClass.simpleName}")
        }.logexIfThrow("Failed init hook: ${this.javaClass.simpleName}")
    }
}