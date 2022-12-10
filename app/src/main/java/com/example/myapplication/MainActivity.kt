package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.classListSingUpAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.mdel.MovieResponse
import com.example.myapplication.mdel.Search
import com.example.myapplication.viewmodel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    var movieResponse: MovieResponse = MovieResponse()
    lateinit var adapterMy: classListSingUpAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.setMoviewRequest()
        binding.plswait.visibility = View.VISIBLE



        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

        initObsever()

    }
    private fun filter(text: String) {
        val filteredList: ArrayList<Search> = ArrayList()
        if (movieResponse.Search.size!=-1){
            for (item in movieResponse.Search) {
                if (item.Title.toString()!!.toLowerCase().contains(text.lowercase(Locale.getDefault()))){
                    filteredList.add(item)
                }
            }
            adapterMy.filterList(filteredList)
        }

    }

    private fun initObsever() {
        viewModel.getMoviewResponse().observe(this) {
            if (it != null) {
                movieResponse = it
                binding.plswait.visibility = View.GONE

                adapterMy = classListSingUpAdapter(movieResponse.Search)
                binding.rvClassList.adapter = adapterMy
                binding.rvClassList.layoutManager = GridLayoutManager(this,2)
                binding.rvClassList.setHasFixedSize(true)

                adapterMy.notifyDataSetChanged()
                utils.showToast(this, movieResponse.Search.size.toString())
            }
        }
    }

    fun clickListnerAdapter(title: String?) {
        utils.showToast(this, title.toString())
    }
}