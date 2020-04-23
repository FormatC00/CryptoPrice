package com.github.formatc00.core.exception

abstract class BaseException : RuntimeException {
    
    constructor()
    
    constructor(message: String) : super(message)
    
    constructor(message: String, cause: Throwable?) : super(message, cause)
    
    constructor(cause: Throwable) : super(cause)
}