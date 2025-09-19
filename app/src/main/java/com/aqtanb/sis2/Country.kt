package com.aqtanb.sis2

data class Country(
    val name: String,
    val flagUrl: String,
    var voteState: VoteState = VoteState.NONE
)