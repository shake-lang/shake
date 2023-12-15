package com.shakelang.shake.util.shason.elements

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonIntegerElementTests : FreeSpec({

    "value should return the value" {
        JsonIntegerElement(123).value shouldBe 123
    }

    "toString() should return the value as string" {
        JsonIntegerElement(123).toString() shouldBe "123"
    }

    "isNull() should return false" {
        JsonIntegerElement(123).isNull() shouldBe false
    }

    "isBoolean() should return false" {
        JsonIntegerElement(123).isBoolean() shouldBe false
    }

    "isString() should return false" {
        JsonIntegerElement(123).isString() shouldBe false
    }

    "isDouble() should return true" {
        JsonIntegerElement(123).isDouble() shouldBe true
    }

    "isInt() should return true" {
        JsonIntegerElement(123).isInt() shouldBe true
    }

    "isJsonPrimitive() should return true" {
        JsonIntegerElement(123).isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonIntegerElement(123).isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonIntegerElement(123).isJsonObject() shouldBe false
    }

    "isJsonDouble() should return true" {
        JsonIntegerElement(123).isJsonDouble() shouldBe true
    }

    "isJsonString() should return false" {
        JsonIntegerElement(123).isJsonString() shouldBe false
    }

    "isJsonInteger() should return true" {
        JsonIntegerElement(123).isJsonInteger() shouldBe true
    }

    "isJsonNull() should return false" {
        JsonIntegerElement(123).isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return false" {
        JsonIntegerElement(123).isJsonBoolean() shouldBe false
    }
})

class JsonDoubleElementTests : FreeSpec({

    "value should return the value" {
        JsonDoubleElement(123.0).value shouldBe 123.0
    }

    "toString() should return the value as string" {
        JsonDoubleElement(123.0).toString() shouldBe "123.0"
    }

    "isNull() should return false" {
        JsonDoubleElement(123.0).isNull() shouldBe false
    }

    "isBoolean() should return false" {
        JsonDoubleElement(123.0).isBoolean() shouldBe false
    }

    "isString() should return false" {
        JsonDoubleElement(123.0).isString() shouldBe false
    }

    "isDouble() should return true" {
        JsonDoubleElement(123.0).isDouble() shouldBe true
    }

    "isInt() should return false" {
        JsonDoubleElement(123.0).isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonDoubleElement(123.0).isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonDoubleElement(123.0).isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonDoubleElement(123.0).isJsonObject() shouldBe false
    }

    "isJsonDouble() should return true" {
        JsonDoubleElement(123.0).isJsonDouble() shouldBe true
    }

    "isJsonString() should return false" {
        JsonDoubleElement(123.0).isJsonString() shouldBe false
    }

    "isJsonInteger() should return false" {
        JsonDoubleElement(123.0).isJsonInteger() shouldBe false
    }

    "isJsonNull() should return false" {
        JsonDoubleElement(123.0).isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return false" {
        JsonDoubleElement(123.0).isJsonBoolean() shouldBe false
    }
})

class JsonStringElementTests : FreeSpec({

    "value should return the value" {
        JsonStringElement("123").value shouldBe "123"
    }

    "toString() should return the value as string" {
        JsonStringElement("123").toString() shouldBe "\"123\""
    }

    "isNull() should return false" {
        JsonStringElement("123").isNull() shouldBe false
    }

    "isBoolean() should return false" {
        JsonStringElement("123").isBoolean() shouldBe false
    }

    "isString() should return true" {
        JsonStringElement("123").isString() shouldBe true
    }

    "isDouble() should return false" {
        JsonStringElement("123").isDouble() shouldBe false
    }

    "isInt() should return false" {
        JsonStringElement("123").isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonStringElement("123").isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonStringElement("123").isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonStringElement("123").isJsonObject() shouldBe false
    }

    "isJsonDouble() should return false" {
        JsonStringElement("123").isJsonDouble() shouldBe false
    }

    "isJsonString() should return true" {
        JsonStringElement("123").isJsonString() shouldBe true
    }

    "isJsonInteger() should return false" {
        JsonStringElement("123").isJsonInteger() shouldBe false
    }

    "isJsonNull() should return false" {
        JsonStringElement("123").isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return false" {
        JsonStringElement("123").isJsonBoolean() shouldBe false
    }
})

class JsonBooleanElementTests : FreeSpec({

    "JsonBooleanElement.TRUE should return true" {
        JsonBooleanElement.TRUE.value shouldBe true
    }
    
    "JsonBooleanElement.FALSE should return false" {
        JsonBooleanElement.FALSE.value shouldBe false
    }
    
    "JsonBooleanElement.from(true) should return true" {
        JsonBooleanElement.from(true).value shouldBe true
    }
    
    "JsonBooleanElement.from(false) should return false" {
        JsonBooleanElement.from(false).value shouldBe false
    }
    
    "value should return the value" {
        JsonBooleanElement.TRUE.value shouldBe true
    }

    "toString() should return the value as string" {
        JsonBooleanElement.TRUE.toString() shouldBe "true"
    }

    "isNull() should return false" {
        JsonBooleanElement.TRUE.isNull() shouldBe false
    }

    "isBoolean() should return true" {
        JsonBooleanElement.TRUE.isBoolean() shouldBe true
    }

    "isString() should return false" {
        JsonBooleanElement.TRUE.isString() shouldBe false
    }

    "isDouble() should return false" {
        JsonBooleanElement.TRUE.isDouble() shouldBe false
    }

    "isInt() should return false" {
        JsonBooleanElement.TRUE.isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonBooleanElement.TRUE.isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonBooleanElement.TRUE.isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonBooleanElement.TRUE.isJsonObject() shouldBe false
    }

    "isJsonDouble() should return false" {
        JsonBooleanElement.TRUE.isJsonDouble() shouldBe false
    }

    "isJsonString() should return false" {
        JsonBooleanElement.TRUE.isJsonString() shouldBe false
    }

    "isJsonInteger() should return false" {
        JsonBooleanElement.TRUE.isJsonInteger() shouldBe false
    }

    "isJsonNull() should return false" {
        JsonBooleanElement.TRUE.isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return true" {
        JsonBooleanElement.TRUE.isJsonBoolean() shouldBe true
    }
})

class JsonNullElementTests : FreeSpec({

    "value should return null" {
        JsonNullElement.INSTANCE.value shouldBe null
    }

    "toString() should return null" {
        JsonNullElement.INSTANCE.toString() shouldBe "null"
    }

    "isNull() should return true" {
        JsonNullElement.INSTANCE.isNull() shouldBe true
    }

    "isBoolean() should return false" {
        JsonNullElement.INSTANCE.isBoolean() shouldBe false
    }

    "isString() should return false" {
        JsonNullElement.INSTANCE.isString() shouldBe false
    }

    "isDouble() should return false" {
        JsonNullElement.INSTANCE.isDouble() shouldBe false
    }

    "isInt() should return false" {
        JsonNullElement.INSTANCE.isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonNullElement.INSTANCE.isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonNullElement.INSTANCE.isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonNullElement.INSTANCE.isJsonObject() shouldBe false
    }

    "isJsonDouble() should return false" {
        JsonNullElement.INSTANCE.isJsonDouble() shouldBe false
    }

    "isJsonString() should return false" {
        JsonNullElement.INSTANCE.isJsonString() shouldBe false
    }

    "isJsonInteger() should return false" {
        JsonNullElement.INSTANCE.isJsonInteger() shouldBe false
    }

    "isJsonNull() should return true" {
        JsonNullElement.INSTANCE.isJsonNull() shouldBe true
    }

    "isJsonBoolean() should return false" {
        JsonNullElement.INSTANCE.isJsonBoolean() shouldBe false
    }
})


