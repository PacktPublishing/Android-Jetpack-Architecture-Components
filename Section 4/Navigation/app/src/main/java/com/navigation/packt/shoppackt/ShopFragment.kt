package com.navigation.packt.shoppackt

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_shop.*
import java.util.*


class ShopFragment : Fragment() {

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_shop, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b_about.setOnClickListener {
//			Navigation.findNavController(it).navigate(R.id.about_destination)

			val nextAction = ShopFragmentDirections.nextAction()
			nextAction.setProductCount(Random().nextInt(200))
			Navigation.findNavController(it).navigate(nextAction)

//			Navigation.findNavController(it).navigate(R.id.next_action)
		}
	}
}
