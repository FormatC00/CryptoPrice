package com.github.formatc00.util

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(value = RobolectricTestRunner::class)
class NumbersManagerTest {
    
    private lateinit var manager: NumbersManager
    
    @Before
    fun setUp() {
        manager = NumbersManager(RuntimeEnvironment.application.resources)
    }
    
    @Test
    fun testFormatWithThreeDigits() {
        assertEquals(manager.formatBigNumber(1_234_567.945345348), "1.23 M")
        assertEquals(manager.formatBigNumber(12_345_678.94354354358), "12.34 M")
        assertEquals(manager.formatBigNumber(125_645_678.9534534538), "125.64 M")
        assertEquals(manager.formatBigNumber(125_645.435345387), "125.64 K")
        assertEquals(manager.formatBigNumber(12_445.543534587), "12.44 K")
        assertEquals(manager.formatBigNumber(1_145.34534587), "1.14 K")
        assertEquals(manager.formatBigNumber(1_678_897_245.23535), "1.67 Bn")
        assertEquals(manager.formatBigNumber(56_856_356_543.34554), "56.85 Bn")
        assertEquals(manager.formatBigNumber(678_777_434_545.74356), "678.77 Bn")
        assertEquals(manager.formatBigNumber(1_897_546_785_546.34345345), "1.89 Tn")
        assertEquals(manager.formatBigNumber(10_675_867_343_434.34345345), "10.67 Tn")
        assertEquals(manager.formatBigNumber(0.0), "0.0")
        assertEquals(manager.formatBigNumber(0.34345345), "0.34")
        assertEquals(manager.formatBigNumber(1.4365), "1.43")
        assertEquals(manager.formatBigNumber(10.5656), "10.56")
    }
}