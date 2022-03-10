package id.dipoengoro.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var _items: MutableLiveData<List<Item>> = MutableLiveData(listOf())
    val items: LiveData<List<Item>> = _items

    fun addItem(item: String) {
        if (item.isNotEmpty()) {
            val data = Item(item)
            _items.value = _items.value?.plus(data) ?: listOf(data)
        }
    }

    fun removeItem(index: Int) {
        _items.value = _items.value?.toMutableList()?.apply {
            if (!index.equals(null)) removeAt(index)
        }?.toList()
    }
}