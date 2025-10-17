package com.aqtanb.sis2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// Bridge for RecyclerView and data
class CountryAdapter(
    private var countries: List<Country>,
    private val onVoteClicked: (Int, VoteState) -> Unit,
    private val onItemClicked: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageFlag: ImageView = itemView.findViewById(R.id.imageFlag)
        val textCountryName: TextView = itemView.findViewById(R.id.textCountryName)
        val buttonLike: Button = itemView.findViewById(R.id.buttonLike)
        val buttonDislike: Button = itemView.findViewById(R.id.buttonDislike)
    }

    // How to create
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    // How to fill with data
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]

        holder.textCountryName.text = country.name

        Glide.with(holder.itemView.context)
            .load(country.flagUrl)
            .centerCrop()
            .into(holder.imageFlag)

        updateButtonStates(holder, country.voteState)

        holder.itemView.setOnClickListener {
            onItemClicked(country)
        }

        holder.buttonLike.setOnClickListener {
            val newVoteState = if (country.voteState == VoteState.LIKE) {
                VoteState.NONE
            } else {
                VoteState.LIKE
            }
            onVoteClicked(position, newVoteState)
        }

        holder.buttonDislike.setOnClickListener {
            val newVoteState = if (country.voteState == VoteState.DISLIKE) {
                VoteState.NONE
            } else {
                VoteState.DISLIKE
            }
            onVoteClicked(position, newVoteState)
        }
    }

    private fun updateButtonStates(holder: CountryViewHolder, voteState: VoteState) {
        val context = holder.itemView.context

        when (voteState) {
            VoteState.LIKE -> {
                holder.buttonLike.backgroundTintList = ContextCompat.getColorStateList(context, R.color.like_button_selected)
                holder.buttonDislike.backgroundTintList = ContextCompat.getColorStateList(context, R.color.button_neutral)
                holder.buttonLike.text = "Liked"
                holder.buttonDislike.text = "Dislike"
            }
            VoteState.DISLIKE -> {
                holder.buttonLike.backgroundTintList = ContextCompat.getColorStateList(context, R.color.button_neutral)
                holder.buttonDislike.backgroundTintList = ContextCompat.getColorStateList(context, R.color.dislike_button_selected)
                holder.buttonLike.text = "Like"
                holder.buttonDislike.text = "Disliked"
            }
            VoteState.NONE -> {
                holder.buttonLike.backgroundTintList = ContextCompat.getColorStateList(context, R.color.like_button_color)
                holder.buttonDislike.backgroundTintList = ContextCompat.getColorStateList(context, R.color.dislike_button_color)
                holder.buttonLike.text = "Like"
                holder.buttonDislike.text = "Dislike"
            }
        }
    }
    // How many
    override fun getItemCount(): Int = countries.size

    fun updateCountries(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }
}