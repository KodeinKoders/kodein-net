package net.kodein.pages.home

import kotlinx.browser.window
import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.home.fragment.Header
import net.kodein.pages.home.fragment.Departments
import net.kodein.pages.home.fragment.Descriptions
import net.kodein.pages.home.fragment.Humans
import net.kodein.utils.recursiveOffset
import org.w3c.dom.HTMLElement
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions
import react.RProps
import react.child
import react.functionalComponent
import react.useRef
import styled.css
import styled.styledDiv

val Home = functionalComponent<RProps>("Home") {
    val div = useRef<HTMLElement?>(null)

    child(Header) {
        attrs.onScrollClick = {
            val (_, offsetTop) = div.current!!.recursiveOffset()
            val clientHeight = div.current!!.clientHeight
            window.scrollTo(ScrollToOptions(top = (offsetTop + clientHeight).toDouble(), behavior = ScrollBehavior.SMOOTH))
        }
    }

    styledDiv {
        ref = div
        css {
            backgroundColor = Color.kodein.dark
            padding(1.rem, 3.rem, 0.rem, 3.rem)
        }
        styledDiv {
            css {
                backgroundColor = Color.kodein.orange
                height = 0.05.rem
            }
        }
    }

    child(MenuTop)

    child(Departments)

    child(Separator)

    child(Descriptions)

    child(Humans)

    layerSeparator(Position.relative, Color.white, Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)

    child(ContactUs)

    child(Footer)

}
