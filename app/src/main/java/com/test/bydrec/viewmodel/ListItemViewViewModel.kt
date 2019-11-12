package com.test.bydrec.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.test.bydrec.R
import com.test.bydrec.model.ListItem

class ListItemViewViewModel(private var item: ListItem) : ViewModel(),
    ListItemViewMvvm.ViewModel {

    private lateinit var context: Context

    override fun setContext(context: Context?) {
        context?.let { this.context = it }
    }

    override fun getHomeTeam(): String? {
        return item.homeTeam
    }

    override fun getAwayTeam(): String? {
        return item.awayTeam
    }

    override fun getDate(): String? {
        return item.date
    }

    override fun getLocalDate(): String? {
        return item.localDate
    }

    override fun getCompetition(): String? {
        return item.competition
    }

    override fun getVenue(): String? {
        return item.venue
    }

    override fun getMatchDay(): String? {
        return item.matchDay
    }

    override fun getState(): String? {
        return item.state
    }

    override fun getHomeTeamScore(): String? {
        return item.homeTeamScore.toString()
    }

    override fun getAwayTeamScore(): String? {
        return item.awayTeamScore.toString()
    }

    override fun isPostponed(): Boolean? {
        return item.state.equals(context.getString(R.string.postponed))
    }

    override fun isResult(): Boolean? {
        return item.fixtureType.equals(context.getString(R.string.fixture_final))
    }

    override fun isWinner(value: String, valueToCompare: String): Boolean? {
        return value.toInt() > valueToCompare.toInt()
    }
}
