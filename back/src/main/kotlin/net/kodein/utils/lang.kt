package net.kodein.utils

import java.util.*


val <T> Optional<T>.value: T? get() = orElse(null)
