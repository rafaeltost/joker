package com.project.joker.presentation

import com.project.joker.data.JokeCallback
import com.project.joker.data.JokeRemoteDataSource
import com.project.joker.model.Joke

class JokePresenter(
    val callback: CallbackPresenter,
    val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
): JokeCallback {

    fun findBy(categoryName: String?){
        callback.showProgress()
        dataSource.findBy(categoryName, this)
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