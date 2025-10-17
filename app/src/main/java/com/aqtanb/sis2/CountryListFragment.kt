package com.aqtanb.sis2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CountryListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CountryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        observeViewModel()
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewCountries)
        adapter = CountryAdapter(
            countries = emptyList(),
            onVoteClicked = { position, voteState ->
                viewModel.updateVote(position, voteState)
            },
            onItemClicked = { country ->
                navigateToDetail(country)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            adapter.updateCountries(countries)
        }
    }

    private fun navigateToDetail(country: Country) {
        val action = CountryListFragmentDirections
            .actionCountryListFragmentToCountryDetailFragment(
                countryName = country.name,
                flagUrl = country.flagUrl,
                voteState = country.voteState.name
            )
        findNavController().navigate(action)
    }
}