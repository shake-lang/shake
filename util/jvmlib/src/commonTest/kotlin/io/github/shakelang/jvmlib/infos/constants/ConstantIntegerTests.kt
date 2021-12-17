package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantIntegerTests {

    @Test
    fun test() {
        val constant = ConstantIntegerInfo(1)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x00, 0x00, 0x01).stream()
        val constant = ConstantIntegerInfo.contentsFromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x01).stream()
        val constant = ConstantIntegerInfo.fromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x00, 0x00, 0x01)
        val constant = ConstantIntegerInfo.contentsFromBytes(bytes)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x01)
        val constant = ConstantIntegerInfo.fromBytes(bytes)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }
}