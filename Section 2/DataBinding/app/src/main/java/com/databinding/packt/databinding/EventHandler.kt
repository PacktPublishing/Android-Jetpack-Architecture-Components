package com.databinding.packt.databinding

import android.content.Context
import android.util.Log
import android.widget.Toast

open class EventHandler(context: Context)  {

	val myContext = context

	fun onButtonClick(name: String) {
		Toast.makeText(myContext, "Hello $name" , Toast.LENGTH_SHORT).show()
	}
}