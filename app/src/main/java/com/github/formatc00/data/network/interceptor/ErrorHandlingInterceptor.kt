package com.github.formatc00.data.network.interceptor

import com.github.formatc00.core.entity.HTTP_OK
import com.github.formatc00.core.exception.InternetUnavailableException
import com.github.formatc00.core.exception.InvalidResponseException
import com.github.formatc00.data.network.entity.ServerResponse
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandlingInterceptor @Inject constructor(
    private val gson: Gson
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            processOkHttpResponse(chain)
        } catch (e: ConnectException) {
            throw InternetUnavailableException(e)
        } catch (e: UnknownHostException) {
            throw InternetUnavailableException(e)
        }
    }
    
    private fun processOkHttpResponse(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        
        try {
            if (response.code() != HTTP_OK) {
                throw InvalidResponseException()
            }
            
            proceedServerResponse(response)
        } catch (e: JsonParseException) {
            throw InvalidResponseException(e)
        }
        
        return response
    }
    
    private fun proceedServerResponse(response: Response) {
        val responseBody = response.body() ?: throw InvalidResponseException("Body is null")
        responseBody.use {
            val stringBody = it.string()
            val serverResponse = gson.fromJson(stringBody, ServerResponse::class.java)
            val status = serverResponse.status ?: throw InvalidResponseException("Status is null")
            val errorCode = status.errorCode ?: throw InvalidResponseException("Error code is null")
            
            if (errorCode != HTTP_OK || errorCode != 0) {
                throw InvalidResponseException()
            }
        }
    }
}