package com.paging.packt.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GitRepoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemViewModel = ViewModelProviders.of(this).get(GitRepoViewModel::class.java)

        itemViewModel.gitRepoPagedList.observe(this, Observer {
            adapter.submitList(it)
        })

        recyclerView.adapter = adapter

//        val service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
//        val call = service.getRepositories(1, 10, "android")
//        call.enqueue(object: Callback<GitRepoResponse> {
//
//            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
//
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()!!
//                    val responseItems = apiResponse.items
//
//                    val size = responseItems?.let {
//                        responseItems.size.toString()
//                    }
//
//                    Toast.makeText(this@MainActivity, size, Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
//            }
//        })
    }
}
