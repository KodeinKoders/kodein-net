package net.kodein.utils

import kotlinx.browser.window
import org.w3c.dom.events.Event
import react.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.typeOf

//// https://github.com/JetBrains/kotlin-wrappers/issues/125
//internal operator fun <P> FunctionalComponent<P>.getValue(thisRef: Any?, property: KProperty<*>): FunctionalComponent<P> {
//    this.asDynamic().displayName = property.name.capitalize()
//    return this
//}

internal operator fun <R> RMutableRef<R?>.invoke(): R {
    return this.current!!
}


internal class StateDelegate<T>(state: Pair<T, RSetState<T>>) : ReadWriteProperty<Any?, T> {

    private var value = state.first
    private val set = state.second

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (value == this.value) return
        this.value = value
        set(value)
    }

}


private val mobileQuery = "(orientation: portrait) and (max-height: 980px), (orientation: landscape) and (max-width: 980px)"

fun useIsMobile(): Boolean {
    var isMobile: Boolean by useState {
        if (jsTypeOf(window) == "undefined") false
        else window.matchMedia(mobileQuery).matches
    }

    useEffectWithCleanup(emptyList()) {
        val onResize: (Event?) -> Unit = {
            isMobile = window.matchMedia(mobileQuery).matches
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    return isMobile
}