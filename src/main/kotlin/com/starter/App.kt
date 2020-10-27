package com.starter

fun main() {
    println("Hello!")
}

fun foo(): Long = System.currentTimeMillis()

fun bar(): Long = foo() * 2
