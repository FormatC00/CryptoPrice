package com.github.formatc00.data.network.mapper

import com.github.formatc00.ANNOUNCEMENT
import com.github.formatc00.CHAT
import com.github.formatc00.EXPLORER
import com.github.formatc00.MESSAGE_BOARD
import com.github.formatc00.REDDIT
import com.github.formatc00.SOURCE_CODE
import com.github.formatc00.TEST_TECHNICAL_DOC
import com.github.formatc00.TEST_WEBSITE
import com.github.formatc00.TWITTER
import com.github.formatc00.data.network.entity.CryptocurrencyUrlsNetwork
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptocurrencyUrlsMapperTest {
    
    private lateinit var urlsMapper: CryptocurrencyUrlsMapper
    
    @Before
    fun setUp() {
        urlsMapper = CryptocurrencyUrlsMapper()
    }
    
    @Test
    fun testUrlsMapper() {
        val urls = CryptocurrencyUrlsNetwork(
            listOf(TEST_WEBSITE),
            listOf(TEST_TECHNICAL_DOC),
            listOf(TWITTER),
            listOf(REDDIT),
            listOf(MESSAGE_BOARD),
            listOf(ANNOUNCEMENT),
            listOf(CHAT),
            listOf(EXPLORER),
            listOf(SOURCE_CODE)
        )
        
        urlsMapper.map(urls)?.apply {
            assertEquals(website[0], TEST_WEBSITE)
            assertEquals(technicalDoc[0], TEST_TECHNICAL_DOC)
            assertEquals(twitter[0], TWITTER)
            assertEquals(reddit[0], REDDIT)
            assertEquals(messageBoard[0], MESSAGE_BOARD)
            assertEquals(announcement[0], ANNOUNCEMENT)
            assertEquals(chat[0], CHAT)
            assertEquals(explorer[0], EXPLORER)
            assertEquals(sourceCode[0], SOURCE_CODE)
        }
    }
}