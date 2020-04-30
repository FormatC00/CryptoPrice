package com.github.formatc00.util.extension

import kotlin.math.floor

fun Double.floor2Digits(): Double = floor(this * 100) / 100