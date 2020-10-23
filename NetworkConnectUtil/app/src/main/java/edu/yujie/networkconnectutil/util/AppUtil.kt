package edu.yujie.myapplication.util


fun getMethodName(): String {
    val stackTrace = Thread.currentThread().stackTrace
    return stackTrace[3].methodName
}

fun getClassName(): String {
    val stackTrace = Thread.currentThread().stackTrace
    return stackTrace[3].className
}

