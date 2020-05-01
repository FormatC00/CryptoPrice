package com.github.formatc00.data.facade

import com.github.formatc00.TEST_DESCRIPTION
import com.github.formatc00.TEST_ID
import com.github.formatc00.TEST_IDS
import com.github.formatc00.TEST_NAME
import com.github.formatc00.TEST_OFFSET
import com.github.formatc00.TEST_SUPPLY
import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.entity.CryptocurrencyMetadata
import com.github.formatc00.core.entity.CryptocurrencyQuote
import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.core.repository.CryptoRepository
import com.github.formatc00.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CryptoFacadeImplTest {
    
    private lateinit var facade: CryptoFacade
    
    @Mock
    lateinit var cryptoRepository: CryptoRepository
    
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        facade = CryptoFacadeImpl(cryptoRepository)
    }
    
    @Test
    fun testGetCryptocurrenciesMap() {
        val testMetadata = CryptocurrencyMetadata(TEST_ID, TEST_DESCRIPTION)
        val testQuote = CryptocurrencyQuote(TEST_ID, TEST_SUPPLY)
        val testCryptocurrency = Cryptocurrency(TEST_ID, TEST_NAME)
        whenever(cryptoRepository.getMetadata(TEST_IDS))
            .thenReturn(Single.just(listOf(testMetadata)))
        whenever(cryptoRepository.getQuote(TEST_IDS))
            .thenReturn(Single.just(listOf(testQuote)))
        whenever(cryptoRepository.getMap(TEST_OFFSET))
            .thenReturn(Single.just(listOf(testCryptocurrency)))
    
        val testObserver = facade.getCryptocurrenciesMap(TEST_OFFSET).test()
        testObserver?.apply {
            assertNoErrors()
            assertEquals(valueCount(), 1)
            val resList = values()[0]
            assertEquals(resList.size, 1)
            resList[0].apply {
                assertEquals(id, TEST_ID)
                assertEquals(name, TEST_NAME)
                assertNotNull(metadata)
                assertEquals(metadata!!.id, TEST_ID)
                assertEquals(metadata!!.description, TEST_DESCRIPTION)
                assertNotNull(quote)
                assertEquals(quote!!.id, TEST_ID)
                assertEquals(quote!!.maxSupply, TEST_SUPPLY)
            }
        }
    }
}