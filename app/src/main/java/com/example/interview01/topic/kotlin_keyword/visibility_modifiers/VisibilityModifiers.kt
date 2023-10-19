package com.example.interview01.topic.kotlin_keyword.visibility_modifiers

/** 頂層聲明: 函數、屬性、類、對象、接口。
 * private: 只在該文件內可見。
 * internal: 同一模塊內可見。
 * protected: 不適用於頂層聲明。
 * public (默認): 任何地方可見。
 */
private fun priFun() = Unit  // 只在此文件內可見
// protected fun proFun() = Unit // 頂層聲明不可使用protected
internal fun intFun() = Unit
public fun pubFun() = Unit          // 默認為public，可省略

/** 類和接口
 * private: 只在這個類內部可見。
 * protected: 在該類及其子類中可見。
 * internal: 在同一模塊的該類的任何客戶端可見。
 * public (默認): 任何地方可見。
 */
class ExampleClass {
    private val priVal = 0
    protected val proVal = 0
    internal val intVal = 0
    public val pubVal = 0 // 默認為public，可省略

    private fun priFun() = Unit
    protected fun proFun() = Unit
    internal fun intFun() = Unit
    public fun pubFun() = Unit // 默認為public，可省略
}

/** 構造函數
 * private: 只在這個類中可見。
 * protected: 不適用於主構造函數。
 * internal: 在同一模塊的該類的任何客戶端可見。
 * public (默認): 該類可見的任何地方都可見。
 */
class ConstructorExample {
    private constructor()
    // protected constructor(a: Int) : this() // 不可使用protected修飾主構造函數
    internal constructor(a: Int) : this()
    public constructor(a: Int, b: Int) : this()  // 默認為public，可省略
}