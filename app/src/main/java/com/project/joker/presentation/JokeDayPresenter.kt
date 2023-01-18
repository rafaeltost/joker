package com.project.joker.presentation

import com.project.joker.data.JokeCallback
import com.project.joker.data.JokeDayRemoteDataSource
import com.project.joker.data.JokeRemoteDataSource
import com.project.joker.model.Joke

class JokeDayPresenter(
    val callback: CallbackPresenter,
    val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
): JokeCallback {

    fun findRandom(){
        callback.showProgress()
        dataSource.findRandom(this)
    }

    override fun onSuccess(response: Joke) {
        callback.showSuccess(response)
    }

    override fun onError(response: String) {
        callback.showFailure(response)
    }

    override fun onComplete() {
        callback.hideProgress()
    }


}