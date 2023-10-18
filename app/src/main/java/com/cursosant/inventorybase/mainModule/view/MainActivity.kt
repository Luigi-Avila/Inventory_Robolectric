package com.cursosant.inventorybase.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cursosant.inventorybase.R
import com.cursosant.inventorybase.addModule.view.AddProductFragment
import com.cursosant.inventorybase.databinding.ActivityMainBinding
import com.cursosant.inventorybase.entities.Product
import com.cursosant.inventorybase.mainModule.view.adapters.OnClickListener
import com.cursosant.inventorybase.mainModule.view.adapters.ProductAdapter
import com.cursosant.inventorybase.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupFab()

        setupViewModel()
    }

    private fun setupAdapter(){
        adapter = ProductAdapter(this)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity,
                resources.getInteger(R.integer.main_columns))
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupFab(){
        binding.fab.setOnClickListener { //Open add fragment
            AddProductFragment().show(supportFragmentManager, getString(R.string.add_title))
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getProducts().observe(this, { products ->
            setListToAdapter(products)
        })
        viewModel.isWelcome().observe(this, { isWelcome ->
            if (isWelcome){
                Snackbar.make(binding.root, getString(R.string.main_msg_welcome), Snackbar.LENGTH_SHORT).show()
                viewModel.setWelcome(false)
            }
        })
    }

    private fun setListToAdapter(products: List<Product>) {
        adapter.submitList(products)
    }

    /**
     * OnClickListener
     * */
    override fun onClick(product: Product) {
        viewModel.setWelcome(true)
    }

    override fun onLongClick(product: Product) {
        viewModel.deleteProduct(product)
    }
}