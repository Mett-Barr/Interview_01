package com.example.interview01.test.java_test

object JavaTest {
    var i = 1
}

fun bubbleSort(arr: IntArray) {
    val n = arr.size

    // 外層迴圈表示需要進行n-1次掃描
    for (i in 0 until n - 1) {

        // 註解：此標記用於標記是否有交換元素，如果沒有交換，則說明數組已經有序
        var swapped = false

        // 註解：內層迴圈表示每次掃描時相鄰元素之間的比較
        for (j in 0 until n - i - 1) {

            // 註解：如果當前元素大於下一個元素，則交換它們
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp

                // 註解：標記已發生交換
                swapped = true
            }
        }

        // 註解：如果沒有交換元素，則表示數組已經有序，可以提前結束排序
        if (!swapped) break
    }
}
