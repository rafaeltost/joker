package com.project.joker.data

import com.project.joker.model.Joke

interface JokeCallback {

    fun onSuccess(response: Joke)

    fun onError(response:String)

    fun onComplete()
}