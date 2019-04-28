package fr.utbm.kotlinjanus

import io.sarl.core.Logging
import io.sarl.lang.core.*
import java.util.*
import io.sarl.lang.util.ClearableReference
import kotlin.reflect.KClass
import io.sarl.lang.annotation.SarlSpecification


class AgentBuilder {
    var parentId: UUID? = null
    var agentId: UUID? = null

    val capacities: MutableList<KClass<out Capacity>> = arrayListOf()

    fun uses(vararg capacities: KClass<out Capacity>) {
        this.capacities.addAll(capacities)
    }

    fun <T : Event>on(init: OnBuilder<T>.()->Unit) = OnBuilder<T>().apply(init).build()

    fun info(message: String) {

    }

    fun build(): Agent {
        val agentObject =
            @SarlSpecification("0.9") object : Agent(parentId, agentId) {
                private val  `$CAPACITY_USE$IO_SARL_CORE_LOGGING`: ClearableReference<Skill> by lazy { `$getSkill`(Logging::class.java) }

                private fun `$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER`(): Logging {
                    return `$castSkill`(Logging::class.java, this.`$CAPACITY_USE$IO_SARL_CORE_LOGGING`)
                }

                init {
    //                val `_$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER` = `$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER`()
    //                `_$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER`.info("The agent was started.")
                }
            }

        return agentObject
    }
}

class OnBuilder<T : Event> {
    var on: (T)->Unit = {}

    fun build() = On(on)
}

class On<T>(val on: (T)->Unit)

fun agent(init: AgentBuilder.()->Unit) = AgentBuilder().apply(init).build()
