package com.example.interview01.topic.kotlin_keyword.class_modifiers

enum class EnumColor(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun enumTest() {
    val color = EnumColor.entries.random()
    when (color) {
        EnumColor.RED -> {}
        EnumColor.GREEN -> {}
        EnumColor.BLUE -> {}
    }
}