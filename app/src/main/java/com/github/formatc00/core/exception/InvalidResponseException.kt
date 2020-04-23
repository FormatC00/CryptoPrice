package com.github.formatc00.core.exception

class InvalidResponseException : BaseException {
    
    constructor(throwable: Throwable) : super(throwable)
    
    constructor(message: String) : super(message)
    
    constructor()
}