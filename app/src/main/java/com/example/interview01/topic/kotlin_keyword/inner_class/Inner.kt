package com.example.interview01.topic.kotlin_keyword.inner_class

class KInner {
    private val priVar = 0

    companion object {
        private val priStaVar = 1
        val staVar = 2

        class R {
            val a = priStaVar
        }
    }

    // 內部類
    inner class InnerClass {  // Non-static (Inner)
        val accessPriVar = priVar
        val accessPriStaVar = priStaVar
        val accessStaVar = staVar
    }

    // 嵌套類
    class NestedClass {  // Static nested class
        // val accessPriVar = priVar  // Compilation error
        val accessPriStaVar = priStaVar
        val accessStaVar = staVar
    }
}

class OutK {
    // val accessPriVar = Parent.priVar  // Compilation error
    // val accessPriStaVar = Parent.priStaVar  // Compilation error
    val accessStaVar = KInner.staVar
}
