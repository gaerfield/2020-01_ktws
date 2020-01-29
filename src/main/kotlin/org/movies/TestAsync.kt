package org.movies

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel


fun heavyLifting() = GlobalScope.async {
    delay(2000)
    Math.random().also { println("heavyLifting done: $it") }
}

fun worksHard() = GlobalScope.async {
    delay(1000)
    Math.random().also { println("hardWork done: $it") }
}

val channel = Channel<Int>()

suspend fun produceSquares() {
    (1 .. 1000).forEach {
        channel.send(it*it)
        log("calculated $it")
        delay(1000)
    }
    channel.close()
}

suspend fun printNumbers() {
    while(true)
        log(channel.receive().toString())
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

@ExperimentalCoroutinesApi
suspend fun main() {
    val a = heavyLifting()
    val b = worksHard()
    log( "Result: ${a.await() + b.await()}" )

    // (Dispatchers.Unconfined)
    val eins = GlobalScope.launch(Dispatchers.Unconfined) {
        launch(Dispatchers.Default) {
            while(true) {
                log("Blaaa")
                delay(2000)
            }
        }
        produceSquares()
    }
    val zwei = GlobalScope.launch(Dispatchers.Unconfined) { printNumbers() }

    log("dudiduh!") // we are done

    eins.join()
}