package com.example.test

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class HomeViewModel(private val context: Context) : ViewModel() {

    private val selectedItemList = mutableListOf<Data.Data.Taxonomy>()
    private var categoryItemList = mutableListOf<Data.Data>()
    private val selectedItem = MutableLiveData<List<Data.Data.Taxonomy>>()
    private val categoryItem = MutableLiveData<List<Data.Data>>()
    val _selectedItem = selectedItem
    val _categoryItem = categoryItem
    private val gson = Gson()


    init {
       val jsonContent =  readJsonFile(context,)
        val data = gson.fromJson(jsonContent,Data::class.java)
        categoryItemList = data.data as MutableList<Data.Data>
        categoryItem.postValue(categoryItemList)
    }

    fun selectItem(item: Data.Data.Taxonomy,isClicked: Boolean){
        if (isClicked)
            selectedItemList.add(item)
        else
            selectedItemList.remove(item)

        selectedItem.postValue(selectedItemList)

    }

    fun unSelectItem(item: Data.Data.Taxonomy) {
        categoryItemList.forEach {
            it.taxonomies.forEach { taxonomies ->
                if (taxonomies.id == item.id)
                    taxonomies.isChecked = false
            }
        }

        categoryItem.postValue(categoryItemList)

    }


    private fun readJsonFile(context: Context): String {
        return try {
            val inputStream: InputStream = context.assets.open("filers.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
}