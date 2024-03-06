package com.example.dodopizza

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dodopizza.databinding.FragmentPizzaDetailsBinding

class PizzaDetailsFragment:BaseFragment<FragmentPizzaDetailsBinding>(FragmentPizzaDetailsBinding::inflate) {
    private val args:PizzaDetailsFragmentArgs by navArgs()
    @SuppressLint("SetTextI18n")
    override fun onBindView() {
        super.onBindView()
        with(binding){
            title.text = args.title
            mainImg.setImageResource(args.pic)
            price.text = "Add to cart for "+args.price+" KZT"
            desc.text = args.desc
            size.text = args.size
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            price.setOnClickListener {
                Toast.makeText(requireContext(), "Bon Appetit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
