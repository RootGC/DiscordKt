package me.aberrantfox.kjdautils.internal.arguments

import me.aberrantfox.kjdautils.api.dsl.command.CommandEvent
import me.aberrantfox.kjdautils.extensions.stdlib.trimToID
import me.aberrantfox.kjdautils.internal.command.*
import net.dv8tion.jda.api.entities.Message

open class MessageArg(override val name: String = "MessageID"): ArgumentType<Message>() {
    companion object : MessageArg()

    override val consumptionType = ConsumptionType.Single
    override var exampleFactory = createExampleFactory {
        mutableListOf(it.message.id)
    }

    override fun convert(arg: String, args: List<String>, event: CommandEvent<*>): ArgumentResult<Message> {
        val retrieved = event.channel.retrieveMessageById(arg.trimToID()).complete()
            ?: return ArgumentResult.Error("Couldn't retrieve a message with the id given from this channel.")

        return ArgumentResult.Success(retrieved)
    }
}