package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantIntegerInfo(val value: Int) : ConstantInfo() {

    override val tag: Byte get() = ConstantIntegerInfo.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeInt(value)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantIntegerInfo {
            val value = stream.readInt()
            return ConstantIntegerInfo(value)
        }

        fun fromStream(stream: DataInputStream): ConstantIntegerInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantIntegerInfo")
            return contentsFromStream(stream)
        }

        const val name: String = "constant_integer_info"
        const val tag: Byte = ConstantTags.CONSTANT_INTEGER
    }

}