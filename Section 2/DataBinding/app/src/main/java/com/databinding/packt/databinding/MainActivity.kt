package com.databinding.packt.databinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.databinding.packt.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//		binding.tvName.text = "Dan Brown"
//		binding.tvEmail.text = "danbrown@gmail.com"

		binding.contact = Contact("Captain Marvel", "caption@gmail.com")

		binding.handler = EventHandler(this)

		binding.imageUrl = "https://i.redd.it/lhw4vp5yoy121.jpg"
	}
}
