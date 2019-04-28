package fr.utbm.kotlinjanus

import io.sarl.core.Initialize
import io.sarl.core.Logging
import io.sarl.lang.annotation.ImportedCapacityFeature
import io.sarl.lang.annotation.PerceptGuardEvaluator
import io.sarl.lang.annotation.SarlSpecification
import io.sarl.lang.annotation.SyntheticMember
import io.sarl.lang.core.Agent
import io.sarl.lang.core.Capacity
import io.sarl.lang.core.Event
import io.sarl.lang.core.Skill
import io.sarl.lang.util.ClearableReference
import org.eclipse.xtext.xbase.lib.Extension
import java.util.*
import kotlin.reflect.KClass

@SarlSpecification("0.9")
open class KotlinAgent(parentId: UUID, agentId: UUID) : Agent(parentId, agentId) {

    @Extension
    @ImportedCapacityFeature(Logging::class)
    @SyntheticMember
    private var ioSarlCoreLogging: ClearableReference<Skill>? = null

    val eventsMap = hashMapOf<Class<out Event>, MutableList<(Event)->Unit>>()

    fun uses(vararg capacities: KClass<out Capacity>) {

    }

    fun <T: Event>on(clazz: Class<T>, init: (T)->Unit) {
        if(!this.eventsMap.containsKey(clazz))
            this.eventsMap[clazz] = arrayListOf()

        this.eventsMap[clazz]?.add(init as (Event) -> Unit)
    }

    inline fun <reified T: Event> on(noinline init: (T)->Unit) {
        if(!this.eventsMap.containsKey(T::class.java))
            this.eventsMap[T::class.java] = arrayListOf()

        this.eventsMap[T::class.java]?.add(init as (Event) -> Unit)
    }

    fun <T : Capacity>skill(clazz: Class<T>): T {
        val caller = this.`$castSkill`(clazz, this.`$getSkill`(clazz))

        return caller
    }

    inline fun <reified T: Capacity>skill(): T {
        val caller = this.`$castSkill`(T::class.java, this.`$getSkill`(T::class.java))

        return caller
    }

    private fun `$behaviorUnit$Initialize$0`(occurrence: Initialize?) {
        val ioSarlCoreLogging = this.ioSarlCoreLogging
        val `_$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER` = this.`$castSkill`(
            Logging::class.java,
            if (ioSarlCoreLogging?.get() == null) {
                this.ioSarlCoreLogging = this.`$getSkill`(Logging::class.java)
                this.ioSarlCoreLogging
            } else {
                this.ioSarlCoreLogging
            }
        )
        `_$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER`.info("The agent was started.")
    }

    @SyntheticMember
    @PerceptGuardEvaluator
    private fun `$guardEvaluator$Initialize`(
        occurrence: Initialize?,
        ___SARLlocal_runnableCollection: MutableCollection<Runnable>
    ) {
        assert(occurrence != null)
        assert(___SARLlocal_runnableCollection != null)
        ___SARLlocal_runnableCollection.add(Runnable { `$behaviorUnit$Initialize$0`(occurrence) })
    }

    @SyntheticMember
    @PerceptGuardEvaluator
    private fun `$guardEvaluator$Event`(
        occurrence: Event?,
        ___SARLlocal_runnableCollection: MutableCollection<Runnable>
    ) {
        if(occurrence != null && ___SARLlocal_runnableCollection != null) {
            eventsMap[occurrence::class.java]?.forEach {
                ___SARLlocal_runnableCollection.add(Runnable { it.invoke(occurrence) })
            }
        }
    }
}