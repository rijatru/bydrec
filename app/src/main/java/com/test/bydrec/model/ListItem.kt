package com.test.bydrec.model

import com.test.bydrec.R

class ListItem(
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
    val competition: String,
    val venue: String,
    val matchDay: String,
    val localDate: String,
    val state: String?,
    val fixtureType: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?
) :
    Item {

    override fun getType(): Int {
        return R.integer.list_item
    }
}
