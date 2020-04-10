package me.aberrantfox.kjdautils.internal.arguments

import me.aberrantfox.kjdautils.api.dsl.command.CommandEvent
import me.aberrantfox.kjdautils.internal.command.*
import kotlin.random.Random

open class DoubleArg(override val name: String = "Decimal"): ArgumentType<Double>() {
    companion object : DoubleArg()

    override val consumptionType = ConsumptionType.Single
    override val examples = mutableListOf(Random.nextDouble(0.0, 100.0).toString())

    override fun convert(arg: String, args: List<String>, event: CommandEvent<*>): ArgumentResult<Double> {
        val double = arg.toDoubleOrNull()
            ?: return ArgumentResult.Error("Expected a decimal number, got $arg")

        return ArgumentResult.Success(double)
    }
}