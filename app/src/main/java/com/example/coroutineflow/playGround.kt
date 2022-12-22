package com.example.coroutineflow

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.Random
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

//in viewmodel

//ui
//먼저 완료된 network 리퀘스트부터 ui에 뿌려준다.
fun main() {
    runBlocking {
        var progress = 0
        onViewModel().map {
            "${it} Request Complete!"
        }.collect { job ->
            println(job)
            progress += 25
            println("$progress %")
        }
    }
}

//viewModel
fun onViewModel() = channelFlow {
    runBlocking {
        val totalTime = measureTimeMillis {
            val networkRequest = launch {
                launch { send(userRequest()) }
                launch { send(profileRequest()) }
                launch { send(friendsRequest()) }
                launch { send(chatRequest()) }
            }
            //먼저 완료된 job 부터 출력 가능하게
            networkRequest.join()
        }
        println("Loading Complete!! start from $totalTime ms...")
    }
}.flowOn(Dispatchers.IO)

//repository
suspend fun userRequest(): String {
    val time = measureTimeMillis {
        delay((1..10000).random().toLong())
    }
    println("userRequest: $time ms...")
    return "userinfo"
}

suspend fun profileRequest(): String {
    val time = measureTimeMillis {
        delay((1..10000).random().toLong())
    }
    println("profileRequest: $time ms...")
    return "profileImage"
}

suspend fun friendsRequest(): String {
    val time = measureTimeMillis {
        delay((1..10000).random().toLong())
    }
    println("friendsRequest: $time ms...")
    return "friends"
}

suspend fun chatRequest(): String {
    val time = measureTimeMillis {
        delay((1..10000).random().toLong())
    }
    println("chatRequest: $time ms...")
    return "chatInfo"
}

//1 순차실행

//2 동시실행

// 각각 job이 끝날때마다 완료됬다고 프린트