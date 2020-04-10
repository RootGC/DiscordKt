package me.aberrantfox.kjdautils.internal.arguments

import me.aberrantfox.kjdautils.api.dsl.command.CommandEvent
import me.aberrantfox.kjdautils.internal.command.*
import kotlin.random.Random

open class IntegerArg(override val name: String = "Integer"): ArgumentType<Int>() {
    companion object : IntegerArg()

    override val consumptionType = ConsumptionType.Single
    override val examples = mutableListOf(Random.nextInt(0, 100).toString())

    override fun convert(arg: String, args: List<String>, event: CommandEvent<*>): ArgumentResult<Int> {
        val int = arg.toIntOrNull() ?: return ArgumentResult.Error("Expected an integer number, got $arg")
        return ArgumentResult.Success(int)
    }
}
