@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.Identifier

interface ValueSpec {
    fun generate(ctx: GenerationContext): String

    companion object {
        fun of(value: String): ValueSpec {
            return object : ValueSpec {
                override fun generate(ctx: GenerationContext): String {
                    return value
                }
            }
        }
    }
}

class StringLiteralSpec(val value: String) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "\"$value\""
    }

    class StringLiteralSpecBuilder
    internal constructor(
        var value: String? = null,
    ) {
        fun value(value: String): StringLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): StringLiteralSpec {
            return StringLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = StringLiteralSpecBuilder()
    }
}

class IntLiteralSpec(val value: Long) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return value.toString()
    }

    class NumberLiteralSpecBuilder
    internal constructor(
        var value: Long? = null,
    ) {

        fun value(value: Long): NumberLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: Int): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        fun value(value: Short): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        fun value(value: Byte): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        fun build(): IntLiteralSpec {
            return IntLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = NumberLiteralSpecBuilder()
    }
}

class FloatLiteralSpec(val value: Double) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return value.toString()
    }

    class FloatLiteralSpecBuilder
    internal constructor(
        var value: Double? = null,
    ) {

        fun value(value: Float): FloatLiteralSpecBuilder {
            this.value = value.toDouble()
            return this
        }

        fun value(value: Double): FloatLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): FloatLiteralSpec {
            return FloatLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = FloatLiteralSpecBuilder()
    }
}

class BooleanLiteralSpec(val value: Boolean) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return value.toString()
    }

    class BooleanLiteralSpecBuilder
    internal constructor(
        var value: Boolean? = null,
    ) {

        fun value(value: Boolean): BooleanLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): BooleanLiteralSpec {
            return BooleanLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = BooleanLiteralSpecBuilder()
    }
}

enum class NullLiteralSpec : ValueSpec {
    INSTANCE,
    ;

    override fun generate(ctx: GenerationContext): String {
        return "null"
    }
}

class VariableReferenceSpec(val name: Identifier) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return name.name
    }

    class VariableReferenceSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        fun name(name: Identifier): VariableReferenceSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableReferenceSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun build(): VariableReferenceSpec {
            return VariableReferenceSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableReferenceSpecBuilder()
    }
}

class AdditionSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} + ${right.generate(ctx)}"
    }

    class AdditionSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): AdditionSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): AdditionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): AdditionSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): AdditionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): AdditionSpec {
            return AdditionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = AdditionSpecBuilder()
    }
}

class SubtractionSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} - ${right.generate(ctx)}"
    }

    class SubtractionSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): SubtractionSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): SubtractionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): SubtractionSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): SubtractionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): SubtractionSpec {
            return SubtractionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = SubtractionSpecBuilder()
    }
}

class MultiplicationSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} * ${right.generate(ctx)}"
    }

    class MultiplicationSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): MultiplicationSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): MultiplicationSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): MultiplicationSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): MultiplicationSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): MultiplicationSpec {
            return MultiplicationSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = MultiplicationSpecBuilder()
    }
}

class DivisionSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} / ${right.generate(ctx)}"
    }

    class DivisionSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): DivisionSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): DivisionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): DivisionSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): DivisionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): DivisionSpec {
            return DivisionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = DivisionSpecBuilder()
    }
}

class ModuloSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} % ${right.generate(ctx)}"
    }

    class ModuloSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): ModuloSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): ModuloSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): ModuloSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): ModuloSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): ModuloSpec {
            return ModuloSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = ModuloSpecBuilder()
    }
}

class PowerSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} ** ${right.generate(ctx)}"
    }

    class PowerSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): PowerSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): PowerSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): PowerSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): PowerSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): PowerSpec {
            return PowerSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = PowerSpecBuilder()
    }
}

class UnaryMinusSpec(val value: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "-${value.generate(ctx)}"
    }

    class UnaryMinusSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): UnaryMinusSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): UnaryMinusSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): UnaryMinusSpec {
            return UnaryMinusSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = UnaryMinusSpecBuilder()
    }
}

typealias NegationSpec = UnaryMinusSpec

class UnaryPlusSpec(val value: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "+${value.generate(ctx)}"
    }

    class UnaryPlusSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): UnaryPlusSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): UnaryPlusSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): UnaryPlusSpec {
            return UnaryPlusSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = UnaryPlusSpecBuilder()
    }
}

class LogicalAndSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} && ${right.generate(ctx)}"
    }

    class LogicalAndSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LogicalAndSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LogicalAndSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LogicalAndSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LogicalAndSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LogicalAndSpec {
            return LogicalAndSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalAndSpecBuilder()
    }
}

class LogicalOrSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} || ${right.generate(ctx)}"
    }

    class LogicalOrSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LogicalOrSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LogicalOrSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LogicalOrSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LogicalOrSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LogicalOrSpec {
            return LogicalOrSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalOrSpecBuilder()
    }
}

class LogicalNotSpec(val value: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "!${value.generate(ctx)}"
    }

    class LogicalNotSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): LogicalNotSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): LogicalNotSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): LogicalNotSpec {
            return LogicalNotSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalNotSpecBuilder()
    }
}

class LogicalXorSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} ^^ ${right.generate(ctx)}"
    }

    class LogicalXorSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LogicalXorSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LogicalXorSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LogicalXorSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LogicalXorSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LogicalXorSpec {
            return LogicalXorSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalXorSpecBuilder()
    }
}

class EqualitySpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} == ${right.generate(ctx)}"
    }

    class EqualitySpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): EqualitySpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): EqualitySpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): EqualitySpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): EqualitySpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): EqualitySpec {
            return EqualitySpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = EqualitySpecBuilder()
    }
}

class InequalitySpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} != ${right.generate(ctx)}"
    }

    class InequalitySpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): InequalitySpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): InequalitySpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): InequalitySpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): InequalitySpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): InequalitySpec {
            return InequalitySpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = InequalitySpecBuilder()
    }
}

class GreaterThanSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} > ${right.generate(ctx)}"
    }

    class GreaterThanSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): GreaterThanSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): GreaterThanSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): GreaterThanSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): GreaterThanSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): GreaterThanSpec {
            return GreaterThanSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = GreaterThanSpecBuilder()
    }
}

class GreaterThanOrEqualSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} >= ${right.generate(ctx)}"
    }

    class GreaterThanOrEqualSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): GreaterThanOrEqualSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): GreaterThanOrEqualSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): GreaterThanOrEqualSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): GreaterThanOrEqualSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): GreaterThanOrEqualSpec {
            return GreaterThanOrEqualSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = GreaterThanOrEqualSpecBuilder()
    }
}

class LessThanSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} < ${right.generate(ctx)}"
    }

    class LessThanSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LessThanSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LessThanSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LessThanSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LessThanSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LessThanSpec {
            return LessThanSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LessThanSpecBuilder()
    }
}

class LessThanOrEqualSpec(val left: ValueSpec, val right: ValueSpec) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} <= ${right.generate(ctx)}"
    }

    class LessThanOrEqualSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LessThanOrEqualSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LessThanOrEqualSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LessThanOrEqualSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LessThanOrEqualSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LessThanOrEqualSpec {
            return LessThanOrEqualSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LessThanOrEqualSpecBuilder()
    }
}
