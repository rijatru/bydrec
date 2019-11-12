package com.test.bydrec.viewmodel

import android.content.Context

interface ListItemViewMvvm {

    interface ViewModel {

        fun setContext(context: Context?)

        fun getHomeTeam(): String?

        fun getAwayTeam(): String?

        fun getDate(): String?

        fun getLocalDate(): String?

        fun getCompetition(): String?

        fun getVenue(): String?

        fun getMatchDay(): String?

        fun getState(): String?

        fun getHomeTeamScore(): String?

        fun getAwayTeamScore(): String?

        fun isPostponed(): Boolean?

        fun isResult(): Boolean?

        fun isWinner(value: String, valueToCompare: String): Boolean?
    }

    interface View
}
