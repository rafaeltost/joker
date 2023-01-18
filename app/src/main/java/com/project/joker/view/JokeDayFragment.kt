package com.project.joker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.joker.R
import com.project.joker.model.Joke
import com.project.joker.presentation.CallbackPresenter
import com.project.joker.presentation.JokeDayPresenter
import com.project.joker.presentation.JokePresenter
import com.squareup.picasso.Picasso

class JokeDayFragment: Fragment(), CallbackPresenter {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var jokeDayPresenter: JokeDayPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeDayPresenter = JokeDayPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke_day,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = getString(R.string.menu_joke_day)
        textView = view.findViewById(R.id.txt_joke)
        progressBar = view.findViewById(R.id.progress_bar)
        imageView = view.findViewById(R.id.img_joke)


        jokeDayPresenter.findRandom()

    }

    override fun showSuccess(items: Any) {
        val joke = items as Joke
        textView.text = joke.text
        Picasso.get().load(joke.iconUrl).into(imageView)
    }

    override fun showFailure(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}