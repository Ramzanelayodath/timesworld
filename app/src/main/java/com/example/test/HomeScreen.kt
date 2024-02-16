package com.example.test

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), ViewModelFactory(requireContext())).get(HomeViewModel::class.java)
    }
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var selectedItemAdapter: SelectedItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        binding.apply {
            rd1.isChecked = true
            recycler.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            recyclerItems.layoutManager =
                LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        }

        setupCategoryRecyclerView()
        setupSelectedItemRecyclerView()

        return binding.root
    }

    private fun setupCategoryRecyclerView() {
        adapter = RecyclerViewAdapter(requireActivity(), mutableListOf())
        binding.recycler.adapter = adapter
        adapter.onItemClick = { item, isClicked ->
            homeViewModel.selectItem(item, isClicked)
        }
        homeViewModel._categoryItem.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }
    }

    private fun setupSelectedItemRecyclerView() {
        selectedItemAdapter = SelectedItemAdapter(mutableListOf())
        binding.recyclerItems.adapter = selectedItemAdapter
        selectedItemAdapter.onItemClick = { item ->
            homeViewModel.unSelectItem(item)
            homeViewModel.selectItem(item,false)
        }
        homeViewModel._selectedItem.observe(viewLifecycleOwner) { list ->
            selectedItemAdapter.setData(list)
        }
    }
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
