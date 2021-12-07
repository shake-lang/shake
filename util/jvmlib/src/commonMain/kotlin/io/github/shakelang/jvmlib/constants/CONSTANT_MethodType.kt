package io.github.shakelang.jvmlib.constants

class CONSTANT_MethodType(val index: Int) : CONSTANT() {

    override val tag: Byte = 16
    override fun toJson() = super.toJson().with("index", index)
    override val type: String get() = "CONSTANT_MethodType"

}