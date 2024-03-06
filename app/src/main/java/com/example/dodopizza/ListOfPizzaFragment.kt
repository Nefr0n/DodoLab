package com.example.dodopizza

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dodopizza.databinding.FragmentListOfPizzaBinding
import com.example.dodopizza.model.Pizza

class ListOfPizzaFragment:BaseFragment<FragmentListOfPizzaBinding>(FragmentListOfPizzaBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        val adapter = PizzaAdapter()
        binding.listPizza.adapter = adapter
        binding.listPizza.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.submitList(getList())
        adapter.itemClick = {it ->
            findNavController().navigate(ListOfPizzaFragmentDirections.actionListOfPizzaFragmentToPizzaDetailsFragment(it.title, it.img, it.desc,it.price, it.size))
        }
        binding.editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                if(binding.editText.text!!.isNotEmpty()){
                    adapter.submitList(searchPizza(binding.editText.text.toString()))
                }
                else{
                    Toast.makeText(requireContext(), "Enter the pizza name", Toast.LENGTH_SHORT).show()
                }
            }

            false
        })
    }

    private fun getList():List<Pizza>{
        return listOf(
            Pizza("Chorizo fresh", R.drawable.p4,
                "Spicy chorizo, sweet pepper, mozzarella cheese, marinara sauce",
                1900,
                "pizza",
                "Small"),
            Pizza("Ham & Cheese",
                R.drawable.p3,
                "Chicken ham, mozzarella cheese, Alfredo sauce",
                3700,
                "pizza",
                "Large" ),
            Pizza("Chorizo fresh",
                R.drawable.p,
                "Spicy chorizo, sweet pepper, mozzarella cheese, marinara sauce",
                3600,
                "combo",
                "Large" ),
            Pizza("Double Chicken ",
                R.drawable.p1,
                "Double chicken, mozzarella cheese, Alfredo sauce",
                3200,
                "pizza",
                "Medium" ),
            Pizza("Bavarian ",
                R.drawable.p2,
                "Spicy chorizo sausages, pickled cucumbers, red onions, tomatoes, mustard sauce, mozzarella, signature tomato sauce",
                2700,
                "pizza",
                "Small" ),
            Pizza("3 pizzas from 4900 ₸",
                R.drawable.p5,
                "A real tasting! Three small special price pizzas. Combo price depends on the selected pizzas and may change",
                4900,
                "combo",
                "size"),
            Pizza("Meat Feast",
                R.drawable.p6,
                "Chicken, chicken ham, chicken pepperoni, chicken chorizo, mozzarella cheese, marinara sauce",
                2700,
                "pizza",
                "size"),
            Pizza("Cheesy",
                R.drawable.p9,
                "Mozzarella cheese, cheddar cheese, parmesan cheese, Alfredo sauce",
                1900,
                "pizza",
                "size"),
            Pizza("Pepperoni with mushrooms",
                R.drawable.p7,
                "Chicken pepperoni, mozzarella cheese, mushrooms, Alfredo sauce",
                2000,
                "combo",
                "size"),
            Pizza("Naruto Pizza",
                R.drawable.p8,
                "Chicken, teriyaki sauce, pineapple, mozzarella cheese, marinara sauce",
                3900,
                "combo",
                "size"),
            )

    }
    private fun searchPizza(input: String):List<Pizza>{
        val pizzas = getList()
        return pizzas.filter{pizza->
            pizza.title.contains(input, ignoreCase = true)
        }
    }
}