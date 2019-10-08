package me.aberrantfox.kjdautils.internal.arguments

import me.aberrantfox.kjdautils.api.dsl.command.CommandEvent
import me.aberrantfox.kjdautils.internal.command.*

open class TimeStringArg(override val name : String = "Time"): ArgumentType<Double>() {
    companion object : TimeStringArg()

    override val examples = arrayListOf("1h 2m 10 seconds", "5 seconds", "5h", "1d", "1 day", "10 minutes 8 seconds")
    override val consumptionType = ConsumptionType.Multiple
    override fun convert(arg: String, args: List<String>, event: CommandEvent<*>) = convertTimeString(args)
}