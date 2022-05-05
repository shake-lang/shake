package io.github.shakelang.shake.processor

import io.github.shakelang.shake.processor.program.creation.CreationShakeConstructor
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeInvokable

object ShakeSelect {

    fun selectFunction(
        functions: List<CreationShakeInvokable>,
        parameters: List<CreationShakeType>,
    ): CreationShakeInvokable? = functions.filter {
            it.parameters.size == parameters.size && // TODO default parameters
                    it.parameters.zip(parameters).all { (parameter, argument) ->
                        parameter.type.compatibleTo(argument)
                    }
        }.minByOrNull {
            it.parameters.zip(parameters).sumOf { (parameter, argument) ->
                parameter.type.compatibilityDistance(argument)
            }
        }

    fun selectConstructor(
        constructors: List<CreationShakeConstructor>,
        parameters: List<CreationShakeType>,
    ): CreationShakeConstructor? = constructors.filter {
        it.parameters.size == parameters.size && // TODO default parameters
                it.parameters.zip(parameters).all { (parameter, argument) ->
                    parameter.type.compatibleTo(argument)
                }
    }.minByOrNull {
        it.parameters.zip(parameters).sumOf { (parameter, argument) ->
            parameter.type.compatibilityDistance(argument)
        }
    }

}