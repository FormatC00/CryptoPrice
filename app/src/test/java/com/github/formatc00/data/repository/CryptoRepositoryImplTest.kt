package com.github.formatc00.data.repository

import com.github.formatc00.TEST_IDS
import com.github.formatc00.core.repository.CryptoRepository
import com.github.formatc00.data.network.CryptoApi
import com.github.formatc00.data.network.entity.CryptocurrencyMapResponse
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataResponse
import com.github.formatc00.data.network.entity.CryptocurrencyQuoteResponse
import com.github.formatc00.data.network.mapper.CryptocurrencyMapper
import com.github.formatc00.data.network.mapper.CryptocurrencyMetadataMapper
import com.github.formatc00.data.network.mapper.CryptocurrencyQuoteMapper
import com.github.formatc00.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CryptoRepositoryImplTest {
    
    lateinit var repository: CryptoRepository
    
    @Mock
    lateinit var api: CryptoApi
    
    @Mock
    lateinit var cryptocurrencyMapper: CryptocurrencyMapper
    
    @Mock
    lateinit var cryptocurrencyMetadataMapper: CryptocurrencyMetadataMapper
    
    @Mock
    lateinit var cryptocurrencyQuoteMapper: CryptocurrencyQuoteMapper
    
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CryptoRepositoryImpl(
            api,
            cryptocurrencyMapper,
            cryptocurrencyMetadataMapper,
            cryptocurrencyQuoteMapper
        )
    }
    
    @Test
    fun testCryptocurrencyMap() {
        val mockResponse = mock(CryptocurrencyMapResponse::class.java)
        whenever(api.getMap()).thenReturn(Single.just(mockResponse))
        repository.getMap().test()?.apply {
            assertNoErrors()
            val values = values()
            assertEquals(values.size, 1)
            verify(cryptocurrencyMapper).map(mockResponse)
        }
    }
    
    @Test
    fun testCryptocurrenciesMetadata() {
        val mockResponse = mock(CryptocurrencyMetadataResponse::class.java)
        whenever(api.getMetadata(TEST_IDS)).thenReturn(Single.just(mockResponse))
        repository.getMetadata(TEST_IDS).test()?.apply {
            assertNoErrors()
            val values = values()
            assertEquals(values.size, 1)
            verify(cryptocurrencyMetadataMapper).map(mockResponse)
        }
    }
    
    @Test
    fun testCryptocurrenciesQuote() {
        val mockResponse = mock(CryptocurrencyQuoteResponse::class.java)
        whenever(api.getQuote(TEST_IDS)).thenReturn(Single.just(mockResponse))
        repository.getQuote(TEST_IDS).test()?.apply {
            assertNoErrors()
            val values = values()
            assertEquals(values.size, 1)
            verify(cryptocurrencyQuoteMapper).map(mockResponse)
        }
    }
}