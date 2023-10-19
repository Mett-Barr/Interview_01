package com.example.interview01.topic.kotlin_keyword.class_modifiers

sealed class Expr {
    data class Const(val number: Double) : Expr()
}
data class Sum(val e1: Expr, val e2: Expr) : Expr()
data object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    // 因為 Const 是 Expr 的內部類，所以我們需要使用 "Expr.Const" 這種格式來檢查其類型。
    is Expr.Const -> expr.number

    // Sum 是外部定義的子類，所以我們可以直接使用 "is Sum" 來檢查其類型，不需要加上 "Expr." 前綴。
    is Sum -> eval(expr.e1) + eval(expr.e2)

    NotANumber -> Double.NaN
    // 不再需要 `else` 子句，因為我們已經覆蓋了所有的情況
}