package com.example.interview01.util

import java.util.Locale

fun String.enumName() = this.split("_").joinToString(" ") { s ->
    s.lowercase(Locale.ROOT)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}