package com.project.joker.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.joker.R
import com.project.joker.model.Category
import com.project.joker.presentation.CallbackPresenter
import com.project.joker.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

class HomeFragment: Fragment(), CallbackPresenter {

    private lateinit var homePresenter: HomePresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: GroupieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePresenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        adapter = GroupieAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        if(adapter.itemCount == 0) homePresenter.findAllCategories()

        adapter.setOnItemClickListener { item, _ ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(JokeFragment.CATEGORY_KEY,categoryName)
            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun showSuccess(items: Any) {
        val categoryItems = (items as List<*>).map { CategoryItem(it as Category) }
        adapter.addAll(categoryItems)
        adapter.notifyDataSetChanged()
    }
    override fun showFailure(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }
    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }



}