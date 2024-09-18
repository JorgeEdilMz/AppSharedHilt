package uptc.edu.co.sharedpreferences_hilt.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    init {
        // Obtener el valor actual del contador desde SharedPreferences
        val currentCount = sharedPreferences.getInt("counter_value", 0)
        _counter.value = currentCount

        // Aumentar el contador y guardarlo en SharedPreferences
        incrementCounter()
    }

    private fun incrementCounter() {
        val updatedCount = (_counter.value ?: 0) + 1
        _counter.value = updatedCount
        editor.putInt("counter_value", updatedCount).apply()
    }
}