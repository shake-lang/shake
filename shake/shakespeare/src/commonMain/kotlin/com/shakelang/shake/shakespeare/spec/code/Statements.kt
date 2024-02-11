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
import com.shakelang.shake.shakespeare.spec.Type

interface StatementSpec {
    fun generate(ctx: GenerationContext): String

    companion object {
        fun of(value: String): StatementSpec {
            return object : StatementSpec {
                override fun generate(ctx: GenerationContext): String {
                    return value
                }
            }
        }
    }
}

class VariableDeclarationSpec(
    val name: Identifier,
    val type: Type,
    val value: ValueSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "val $name: $type = $value"
    }

    open class VariableDeclarationSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var type: Type? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableDeclarationSpecBuilder {
            this.name = name
            return this
        }

        fun type(type: Type): VariableDeclarationSpecBuilder {
            this.type = type
            return this
        }

        fun type(type: String): VariableDeclarationSpecBuilder {
            this.type = Type.of(type)
            return this
        }

        fun value(value: ValueSpec): VariableDeclarationSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableDeclarationSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableDeclarationSpec {
            return VariableDeclarationSpec(
                name ?: throw IllegalStateException("Name not set"),
                type ?: throw IllegalStateException("Type not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableDeclarationSpecBuilder()
    }
}

class WhileSpec(
    val condition: ValueSpec,
    val body: CodeSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "while(${condition.generate(ctx)}) ${body.generate(ctx.indent())}"
    }

    open class WhileSpecBuilder
    internal constructor(
        var condition: ValueSpec? = null,
        var body: CodeSpec? = null,
    ) {

        fun condition(condition: ValueSpec): WhileSpecBuilder {
            this.condition = condition
            return this
        }

        fun condition(condition: String): WhileSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        fun body(body: CodeSpec): WhileSpecBuilder {
            this.body = body
            return this
        }

        fun build(): WhileSpec {
            return WhileSpec(
                condition ?: throw IllegalStateException("Condition not set"),
                body ?: throw IllegalStateException("Body not set"),
            )
        }
    }

    companion object {
        fun builder() = WhileSpecBuilder()
    }
}

class DoWhileSpec(
    val body: CodeSpec,
    val condition: ValueSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "do ${body.generate(ctx.indent())} while(${condition.generate(ctx)})"
    }

    open class DoWhileSpecBuilder
    internal constructor(
        var body: CodeSpec? = null,
        var condition: ValueSpec? = null,
    ) {

        fun body(body: CodeSpec): DoWhileSpecBuilder {
            this.body = body
            return this
        }

        fun condition(condition: ValueSpec): DoWhileSpecBuilder {
            this.condition = condition
            return this
        }

        fun condition(condition: String): DoWhileSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        fun build(): DoWhileSpec {
            return DoWhileSpec(
                body ?: throw IllegalStateException("Body not set"),
                condition ?: throw IllegalStateException("Condition not set"),
            )
        }
    }

    companion object {
        fun builder() = DoWhileSpecBuilder()
    }
}

class ForSpec(
    val init: StatementSpec,
    val condition: ValueSpec,
    val update: StatementSpec,
    val body: CodeSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "for(${init.generate(ctx)}; ${condition.generate(ctx)}; ${update.generate(ctx)}) ${body.generate(ctx.indent())}"
    }

    open class ForSpecBuilder
    internal constructor(
        var init: StatementSpec? = null,
        var condition: ValueSpec? = null,
        var update: StatementSpec? = null,
        var body: CodeSpec? = null,
    ) {

        fun init(init: StatementSpec): ForSpecBuilder {
            this.init = init
            return this
        }

        fun init(init: String): ForSpecBuilder {
            this.init = StatementSpec.of(init)
            return this
        }

        fun condition(condition: ValueSpec): ForSpecBuilder {
            this.condition = condition
            return this
        }

        fun condition(condition: String): ForSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        fun update(update: StatementSpec): ForSpecBuilder {
            this.update = update
            return this
        }

        fun update(update: String): ForSpecBuilder {
            this.update = StatementSpec.of(update)
            return this
        }

        fun body(body: CodeSpec): ForSpecBuilder {
            this.body = body
            return this
        }

        fun build(): ForSpec {
            return ForSpec(
                init ?: throw IllegalStateException("Init not set"),
                condition ?: throw IllegalStateException("Condition not set"),
                update ?: throw IllegalStateException("Update not set"),
                body ?: throw IllegalStateException("Body not set"),
            )
        }
    }

    companion object {
        fun builder() = ForSpecBuilder()
    }
}

class IfSpec(
    val condition: ValueSpec,
    val body: CodeSpec,
    val elseBody: CodeSpec?,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        val elsePart = if (elseBody != null) " else ${elseBody.generate(ctx.indent())}" else ""
        return "if(${condition.generate(ctx)}) ${body.generate(ctx.indent())}$elsePart"
    }

    open class IfSpecBuilder
    internal constructor(
        var condition: ValueSpec? = null,
        var body: CodeSpec? = null,
        var elseBody: CodeSpec? = null,
    ) {

        fun condition(condition: ValueSpec): IfSpecBuilder {
            this.condition = condition
            return this
        }

        fun condition(condition: String): IfSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        fun body(body: CodeSpec): IfSpecBuilder {
            this.body = body
            return this
        }

        fun elseBody(elseBody: CodeSpec): IfSpecBuilder {
            this.elseBody = elseBody
            return this
        }

        fun build(): IfSpec {
            return IfSpec(
                condition ?: throw IllegalStateException("Condition not set"),
                body ?: throw IllegalStateException("Body not set"),
                elseBody,
            )
        }
    }

    companion object {
        fun builder() = IfSpecBuilder()
    }
}

class ReturnSpec(
    val value: ValueSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "return ${value.generate(ctx)}"
    }

    open class ReturnSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): ReturnSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): ReturnSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): ReturnSpec {
            return ReturnSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }
}
