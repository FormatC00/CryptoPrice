package com.github.formatc00.data.network.mapper

import com.github.formatc00.TEST_ERROR_CODE
import com.github.formatc00.TEST_ERROR_MSG
import com.github.formatc00.TEST_ID
import com.github.formatc00.TEST_NAME
import com.github.formatc00.data.network.entity.CryptocurrencyMapResponse
import com.github.formatc00.data.network.entity.CryptocurrencyNetwork
import com.github.formatc00.data.network.entity.StatusResponse
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptocurrencyMapperTest() {
    
    private lateinit var mapper: CryptocurrencyMapper
    
    @Before
    fun setUp() {
        mapper = CryptocurrencyMapper()
    }
    
    @Test
    fun testCryptocurrencyMapper() {
        val response = CryptocurrencyMapResponse()
        response.status = StatusResponse(TEST_ERROR_CODE, TEST_ERROR_MSG)
        val cryptocurrencyNetwork =
            CryptocurrencyNetwork(TEST_ID, TEST_NAME, TEST_NAME, TEST_ERROR_CODE)
        response.data = listOf(cryptocurrencyNetwork)
        
        val res = mapper.map(response)
        assertEquals(res.size, 1)
        res[0].apply {
            assertEquals(id, TEST_ID)
            assertEquals(name, TEST_NAME)
            assertEquals(symbol, TEST_NAME)
            assertEquals(rank, TEST_ERROR_CODE)
        }
    }
}