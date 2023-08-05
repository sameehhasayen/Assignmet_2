package com.example.assignment2


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private var viewRecipesFragment: ViewRecipesFragment? = null
    private var addRecipeDialogFragment: AddRecipeDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewRecipesFragment = ViewRecipesFragment()
        addRecipeDialogFragment = AddRecipeDialogFragment()


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, viewRecipesFragment!!)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.action_view_recipes -> {
                // Show View Recipes Fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, viewRecipesFragment!!)
                    .commit()
                true
            }

            R.id.action_add_recipe -> {
                // Show Add Recipe DialogFragment
                addRecipeDialogFragment!!.show(supportFragmentManager, "add_recipe_dialog")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    class ViewRecipesFragment : Fragment() {
        private lateinit var recipeListAdapter: ArrayAdapter<String>
        private lateinit var recipesList: ListView
        private val dummyRecipeList = listOf("Pancakes", "Spaghetti", "Brownies", "Salad")

        @SuppressLint("MissingInflatedId")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_view_recipes, container, false)
            recipesList = view.findViewById(R.id.list_recipes)
            recipeListAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                dummyRecipeList
            )
            recipesList.adapter = recipeListAdapter
            return view
        }
        class AddRecipeDialogFragment : DialogFragment() {
            // ... AddRecipeDialogFragment code ...
        }
    }
}

