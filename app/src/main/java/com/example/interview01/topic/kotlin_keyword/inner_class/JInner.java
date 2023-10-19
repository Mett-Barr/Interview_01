package com.example.interview01.topic.kotlin_keyword.inner_class;

public class JInner {

    private int priVar = 0;

    // Static nested class
    // 靜態內部類
    public static class NestedClass {
        private static int priStaVar = 1;
        public static int staVar = 2;

        // int cannotAccessPriVar = priVar;  // Compilation error
    }

    // Inner class (non-static)
    // 非靜態內部類
    public class InnerClass {
        int accessPriVar = priVar;
        int accessPriStaVar = NestedClass.priStaVar;
        int accessStaVar = NestedClass.staVar;
    }
}

class OutJ {
    // int accessPriVar = new JInner().priVar;  // Compilation error
    int accessStaVar = JInner.NestedClass.staVar;
}

