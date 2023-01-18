package com.project.joker.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.project.joker.data.CategoryRemoteDataSource
import com.project.joker.data.ListCategoryCallback
import com.project.joker.model.Category

class HomePresenter(
    private val callback: CallbackPresenter,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
    ): ListCategoryCallback {

    fun findAllCategories(){
        callback.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>){
        val start = 40 //matiz
        val end = 190 //matiz
        val diff = (end - start) / response.size

        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f,
            )
            Category(s, Color.HSVToColor(hsv).toLong()) }
        callback.showSuccess(categories)
    }

    override fun onError(response: String){
       callback.showFailure(response)
    }

    override fun onComplete(){
       callback.hideProgress()
    }

}