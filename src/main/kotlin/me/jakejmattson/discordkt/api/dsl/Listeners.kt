@file:Suppress("unused")

package me.jakejmattson.discordkt.api.dsl

import dev.kord.core.event.Event
import dev.kord.core.on
import me.jakejmattson.discordkt.api.Discord
import me.jakejmattson.discordkt.api.extensions.intentsOf
import me.jakejmattson.discordkt.internal.annotations.BuilderDSL
import me.jakejmattson.discordkt.internal.annotations.InnerDSL
import me.jakejmattson.discordkt.internal.utils.BuilderRegister
import me.jakejmattson.discordkt.internal.utils.InternalLogger
import me.jakejmattson.discordkt.internal.utils.simplerName

/**
 * Create a block for registering listeners.
 *
 * @param construct The builder function.
 */
@BuilderDSL
fun listeners(construct: ListenerBuilder.() -> Unit) = Listeners(construct)

/**
 * @suppress Used in DSL
 *
 * @param discord The discord instance.
 */
data class ListenerBuilder(val discord: Discord) {
    /**
     * Create a new listener.
     */
    @InnerDSL
    inline fun <reified T : Event> on(crossinline listener: suspend T.() -> Unit) {
        val requiredIntents = intentsOf<T>()
        val intentNames = requiredIntents.values.joinToString { it::class.simpleName!! }

        if (requiredIntents !in discord.configuration.intents)
            InternalLogger.error("${T::class.simplerName} missing intent: $intentNames")

        discord.kord.on<T> {
            listener(this)
        }
    }
}

/**
 * This is not for you...
 */
class Listeners(private val collector: ListenerBuilder.() -> Unit) : BuilderRegister {
    /** @suppress */
    override fun register(discord: Discord) {
        collector.invoke(ListenerBuilder(discord))
    }
}