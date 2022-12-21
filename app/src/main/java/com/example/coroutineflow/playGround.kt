package com.example.coroutineflow

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

val stateFlow = MutableStateFlow("")
//ui
//먼저 완료된 network 리퀘스트부터 ui에 뿌려준다.
fun main() {
    runBlocking {
        onViewModel()
        //state -> userComplete, profileComplete, friendComplete, chatComplete
        stateFlow.collect{ job ->
            println(job)
        }
    }
}

fun onViewModel() = runBlocking {
    val time = measureTimeMillis {
        val userJob = async {
            userRequest()
        }
        val profileJob = async {
            profileRequest()
        }
        val friendsJob = async {
            friendsRequest()
        }
        val chatJob = async {
            chatRequest()
        }
        //먼저 완료된 job 부터 출력 가능하게

    }
    println(time)
}

suspend fun userRequest(): String {
    delay(500)
    return "userinfo"
}

suspend fun profileRequest(): String {
    delay(1000)
    return "profileImage"
}

suspend fun friendsRequest(): String {
    delay(1500)
    return "friends"
}

suspend fun chatRequest(): String {
    delay(1800)
    return "chatInfo"
}

//1 순차실행

//2 동시실행

// 각각 job이 끝날때마다 완료됬다고 프린트