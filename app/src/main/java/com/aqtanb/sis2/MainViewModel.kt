package com.aqtanb.sis2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        loadCountries()
    }

    private fun loadCountries() {
        val countryList = listOf(
            Country("United States", "https://flagcdn.com/w320/us.png"),
            Country("United Kingdom", "https://flagcdn.com/w320/gb.png"),
            Country("France", "https://flagcdn.com/w320/fr.png"),
            Country("Germany", "https://flagcdn.com/w320/de.png"),
            Country("Japan", "https://flagcdn.com/w320/jp.png"),
            Country("South Korea", "https://flagcdn.com/w320/kr.png"),
            Country("Canada", "https://flagcdn.com/w320/ca.png"),
            Country("Australia", "https://flagcdn.com/w320/au.png"),
            Country("Brazil", "https://flagcdn.com/w320/br.png"),
            Country("India", "https://flagcdn.com/w320/in.png"),
            Country("China", "https://flagcdn.com/w320/cn.png"),
            Country("Russia", "https://flagcdn.com/w320/ru.png"),
            Country("Italy", "https://flagcdn.com/w320/it.png"),
            Country("Spain", "https://flagcdn.com/w320/es.png"),
            Country("Mexico", "https://flagcdn.com/w320/mx.png"),
            Country("Argentina", "https://flagcdn.com/w320/ar.png"),
            Country("South Africa", "https://flagcdn.com/w320/za.png"),
            Country("Egypt", "https://flagcdn.com/w320/eg.png"),
            Country("Nigeria", "https://flagcdn.com/w320/ng.png"),
            Country("Kazakhstan", "https://flagcdn.com/w320/kz.png"),
            Country("Turkey", "https://flagcdn.com/w320/tr.png"),
            Country("Saudi Arabia", "https://flagcdn.com/w320/sa.png"),
            Country("Thailand", "https://flagcdn.com/w320/th.png"),
            Country("Vietnam", "https://flagcdn.com/w320/vn.png"),
            Country("Indonesia", "https://flagcdn.com/w320/id.png")
        )
        _countries.value = countryList
    }

    fun updateVote(position: Int, voteState: VoteState) {
        val currentList = _countries.value ?: return
        val updatedList = currentList.toMutableList()

        if (position >= 0 && position < updatedList.size) {
            updatedList[position] = updatedList[position].copy(voteState = voteState)
            _countries.value = updatedList
        }
    }
}