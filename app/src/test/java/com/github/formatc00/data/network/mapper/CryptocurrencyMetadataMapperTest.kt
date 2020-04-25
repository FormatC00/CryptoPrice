package com.github.formatc00.data.network.mapper

import com.github.formatc00.TEST_DESCRIPTION
import com.github.formatc00.TEST_ERROR_CODE
import com.github.formatc00.TEST_ERROR_MSG
import com.github.formatc00.TEST_ID
import com.github.formatc00.TEST_LOGO_URL
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataNetwork
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataResponse
import com.github.formatc00.data.network.entity.CryptocurrencyUrlsNetwork
import com.github.formatc00.data.network.entity.StatusResponse
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CryptocurrencyMetadataMapperTest {
    
    private lateinit var mapper: CryptocurrencyMetadataMapper
    
    @Mock
    lateinit var urlsMapper: CryptocurrencyUrlsMapper
    
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = CryptocurrencyMetadataMapper(urlsMapper)
    }
    
    @Test
    fun testCryptocurrencyMetadataMapper() {
        val urls = mock(CryptocurrencyUrlsNetwork::class.java)
        val response = CryptocurrencyMetadataResponse()
        val testMetadata =
            CryptocurrencyMetadataNetwork(TEST_ID, TEST_DESCRIPTION, TEST_LOGO_URL, urls)
        response.status = StatusResponse(TEST_ERROR_CODE, TEST_ERROR_MSG)
        val data = HashMap<String, CryptocurrencyMetadataNetwork>().apply { put("1", testMetadata) }
        response.data = data
        
        val res = mapper.map(response)
        assertEquals(res.size, 1)
        res[0].apply {
            assertEquals(id, TEST_ID)
            assertEquals(description, TEST_DESCRIPTION)
            assertEquals(logoUrl, TEST_LOGO_URL)
            verify(urlsMapper).map(urls)
        }
    }
}