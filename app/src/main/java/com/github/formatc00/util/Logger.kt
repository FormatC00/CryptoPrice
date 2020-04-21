package com.github.formatc00.util

import android.util.Log
import com.github.formatc00.BuildConfig

class Logger private constructor(aClass: Class<*>) {
    
    private val tag = aClass.simpleName
    
    fun d(msg: String) {
        if (MIN_LEVEL <= Log.DEBUG) {
            Log.d(tag, convertToMessage(msg))
        }
    }
    
    fun e(msg: String) {
        e(null, msg)
    }
    
    @JvmOverloads
    fun e(e: Throwable?, msg: String? = null) {
        if (MIN_LEVEL <= Log.ERROR) {
            Log.e(tag, convertToMessage(msg))
        }
    }
    
    private fun convertToMessage(msg: String?): String {
        return msg ?: ""
    }
    
    companion object {
        
        private val MIN_LEVEL = if (BuildConfig.DEBUG) Log.VERBOSE else Log.ERROR
        
        fun create(aClass: Class<*>): Logger {
            return Logger(aClass)
        }
        
        fun create(classForSimpleName: Any): Logger {
            return Logger(classForSimpleName.javaClass)
        }
    }
}
