package com.github.formatc00

import org.mockito.Mockito

fun <T> Any.whenever(methodCall: T) = Mockito.`when`(methodCall)