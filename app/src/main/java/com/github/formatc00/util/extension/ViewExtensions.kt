package com.github.formatc00.util.extension

import android.view.View

var View.visible: Boolean
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
    get() = visibility == View.VISIBLE

fun View.show() {
    visible = true
}

fun View.hide() {
    visible = false
}