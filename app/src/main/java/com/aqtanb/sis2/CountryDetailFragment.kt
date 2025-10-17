package com.aqtanb.sis2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

class CountryDetailFragment : Fragment() {

    private val args: CountryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageFlag: ImageView = view.findViewById(R.id.imageFlag)
        val textCountryName: TextView = view.findViewById(R.id.textCountryName)
        val textVoteState: TextView = view.findViewById(R.id.textVoteState)

        textCountryName.text = args.countryName

        Glide.with(this)
            .load(args.flagUrl)
            .centerCrop()
            .into(imageFlag)

        val voteStateEnum = VoteState.valueOf(args.voteState)
        val voteStatusText = when (voteStateEnum) {
            VoteState.LIKE -> "Vote Status: Liked"
            VoteState.DISLIKE -> "Vote Status: Disliked"
            VoteState.NONE -> "Vote Status: Not voted"
        }
        textVoteState.text = voteStatusText
    }
}