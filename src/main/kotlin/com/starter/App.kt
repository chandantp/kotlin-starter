package com.starter

fun main() {
    println("Hello!")
    val someList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val pairsList = listOf(Pair(1,1), Pair(2,4), Pair(3,9), Pair(4,16), Pair(5,25))

    val doubles = someList.associateBy( { it -> it }, { it -> it * 2 } )
    println("doubles = $doubles")

    val oddsEvens = someList.groupBy { it -> it % 2 }
    println("oddsEvens = $oddsEvens")

    val oddsEvens2 = someList.groupBy { it -> if (it % 2 == 0) "EVEN" else "ODD" }
    println("oddsEvens2 = $oddsEvens2")

    val numToSquares = pairsList.groupBy({ it.first }, { it.second} )
    println("numToSquares = $numToSquares")

    val oddsEvensParts = someList.partition { it % 2 == 0 }
    println("oddsEvensParts = $oddsEvensParts")

    val listOfLists = someList.map { listOf(it, it*it) }
    println("listOfLists = $listOfLists")

    val flattenListOfLists = listOfLists.flatten()
    println("flattenListOfLists = $flattenListOfLists")

    val flattenListsUsingFlatMap = someList.flatMap { listOf(it, it*it*it) }
    println("flattenListsUsingFlatMap = $flattenListsUsingFlatMap")

    val sumUsingFold = someList.fold(0, { acc, it -> acc + it })
    println("sumUsingFold = $sumUsingFold")

    val concatLeft = someList.fold("START", { acc, it -> "$acc:$it" })
    println("concatLeft = $concatLeft")

    val concatRight = someList.foldRight("START", { it, acc -> "$acc:$it" })
    println("concatRight = $concatRight")
}


