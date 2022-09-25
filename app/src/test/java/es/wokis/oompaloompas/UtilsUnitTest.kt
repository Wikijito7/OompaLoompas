package es.wokis.oompaloompas

import es.wokis.oompaloompas.utils.capitalize
import es.wokis.oompaloompas.utils.orZero
import junit.framework.Assert.assertEquals
import org.junit.Test

class UtilsUnitTest {
    @Test
    fun `assert orZero returns zero`() {
        val value: Int? = null
        assertEquals(0, value.orZero())
    }

    @Test
    fun `assert capitalize returns string capitalized`() {
        val value = "hola mundo"
        assertEquals("Hola mundo", value.capitalize())
    }
}