package fr.utbm.kotlinjanus

import io.janusproject.Boot
import io.sarl.core.Initialize
import io.sarl.core.Logging
import io.sarl.lang.annotation.SarlSpecification
import io.sarl.lang.core.Event
import java.util.*

val agentA =
    agent {
        val randomAttribute = ""

        uses(Logging::class)

        on<Initialize> {
            info("Test")
        }

        on<MyEvent> {

        }

        on<MyEvent2> {

        }
    }

class MyEvent : Event()
class MyEvent2 : Event()

@SarlSpecification("0.9") class ChildKotlinAgent(parentId: UUID, agentId: UUID) : KotlinAgent(parentId, agentId) {
    init {
        on(Initialize::class.java) {
            println("Hello world")

            skill(Logging::class.java).info("I'm alive")
        }
    }
}

class Example {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Starting...")

            //Boot.startJanus(TestAgent::class.java)
            //Boot.startJanus(agentA.javaClass)
            //Boot.startJanus(KotlinAgent::class.java)
            Boot.startJanus(ChildKotlinAgent::class.java)
        }
    }
}