package com.project.joker.presentation


interface CallbackPresenter {
    fun showSuccess(items: Any)
    fun showFailure(message: String)
    fun showProgress()
    fun hideProgress()
}