package net.kodein.pages.services

import kotlinx.css.Color
import net.kodein.charter.kodein
import net.kodein.components.ContactUs
import net.kodein.components.Footer
import net.kodein.components.MenuTop
import net.kodein.pages.services.fragment.Cover
import net.kodein.utils.flexColumn
import react.RProps
import react.child
import react.functionalComponent


val Services = functionalComponent<RProps>("Services") {
    flexColumn {
        child(MenuTop) {
            attrs.animated = true
            attrs.backgroundColor = Color.kodein.dark
        }
        child(Cover)
        /*child(Descriptions)*/
        child(ContactUs)
        child(Footer)
    }
}
