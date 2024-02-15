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
    private var list: List<Data.Data> =  mutableListOf()
    var selectedItemList = mutableListOf<Data.Data.Taxonomy>()
    lateinit var selectedItemAdapter : SelectedItemAdapter
    private lateinit var  homeViewModel : HomeViewModel
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false)
        homeViewModel = ViewModelProvider(requireActivity(),ViewModelFactory(requireContext()))[HomeViewModel::class.java]
        binding.apply {
           rd1.isChecked = true
            recycler.layoutManager = LinearLayoutManager(requireActivity(),RecyclerView.VERTICAL,false)
            recyclerItems.layoutManager = LinearLayoutManager(requireActivity(),RecyclerView.HORIZONTAL,false)
        }
        adapter = RecyclerViewAdapter(requireActivity(),list.toMutableList())
        homeViewModel._selectedItem.observe(requireActivity()) {
            selectedItemList = it as MutableList<Data.Data.Taxonomy>
            selectedItemAdapter = SelectedItemAdapter(selectedItemList.toMutableList())
            selectedItemAdapter.onItemClick = {it ->
                //homeViewModel.unSelectItem(it)
            }
            binding.recyclerItems.adapter = selectedItemAdapter

        }
        homeViewModel._categoryItem.observe(requireActivity()){
            list = it as MutableList<Data.Data>
            adapter = RecyclerViewAdapter(requireActivity(),list.toMutableList())
            binding.recycler.adapter = adapter
            adapter.onItemClick = { it,isClicked ->
                homeViewModel.selectItem(it,isClicked)
            }
        }
        return binding.root
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