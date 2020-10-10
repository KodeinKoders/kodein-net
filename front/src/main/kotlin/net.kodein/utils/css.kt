package net.kodein.utils

import kotlinx.css.LinearDimension
import kotlinx.css.StyledElement


var StyledElement.clipPath: String
    get() = declarations["clip-path"] as String
    set(value) { declarations["clip-path"] = value }