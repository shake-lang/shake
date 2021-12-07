package io.github.shakelang.jvmlib.constants

class ConstantMethodrefInfo(val classRef: Int, val nameTypeRef: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val type: String get() = name
    
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)

    companion object {
        const val name = "ConstantMethodrefInfo"
        const val tag = ConstantTags.CONSTANT_METHOD_REF
    }

}