package com.test.bydrec.viewmodel.providers

import com.test.business.results.api.ResultsController
import com.test.business.results.api.TeamMatchResult
import com.test.bydrec.R
import com.test.bydrec.model.Item
import com.test.bydrec.model.ListHeaderItem
import com.test.bydrec.model.ListItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ResultsItemsProviderImpl(private val resultsController: ResultsController) :
    ItemsProvider {

    override val items: List<Item>
        get() {
            val items = ArrayList<Item>()
            val results = resultsController.results
            val datesList = getDates(results)

            if (datesList.isNotEmpty()) {
                addHeaderItem(items, datesList)

                for (result in results) {
                    if (canAddNewHeaderItem(datesList, result)) {
                        addNewHeaderItem(items, datesList)
                    }
                    addListItem(items, result)
                }
            }

            return items
        }

    override fun filterItemsBy(
        items: List<Item>,
        itemsFilter: String,
        defaultValue: String
    ): ArrayList<Item> {
        val filteredListItems = ArrayList<Item>()
        items.forEach {
            if (it.getType() == R.integer.list_header_item && (it as ListHeaderItem).date == itemsFilter) {
                filteredListItems.add(it)
            } else if (it.getType() == R.integer.list_item && (it as ListItem).date == itemsFilter) {
                filteredListItems.add(it)
            } else if (itemsFilter == defaultValue) {
                filteredListItems.add(it)
            }
        }
        return filteredListItems
    }

    private fun addListItem(items: MutableList<Item>, result: TeamMatchResult) {
        val listItem =
            ListItem(
                result.homeTeam.name,
                result.awayTeam.name,
                formatDate(result.date),
                result.competitionStage.competition.name,
                result.venue.name,
                formatMatchDay(result.date),
                formatLocalDate(result.date),
                result.state,
                result.type,
                result.aggregateScore?.home,
                result.aggregateScore?.away
            )
        items.add(listItem)
    }

    private fun addHeaderItem(items: MutableList<Item>, datesList: List<String>) {
        val item = ListHeaderItem(datesList[0])
        items.add(item)
    }

    private fun getDates(results: List<TeamMatchResult>): MutableList<String> {
        val dates = LinkedHashSet<String>()

        for (result in results) {
            val formattedDate = formatDate(result.date)
            dates.add(formattedDate)
        }

        return ArrayList(dates)
    }

    private fun addNewHeaderItem(items: MutableList<Item>, datesList: MutableList<String>) {
        datesList.removeAt(0)
        if (datesList.isNotEmpty()) {
            addHeaderItem(items, datesList)
        }
    }

    private fun canAddNewHeaderItem(datesList: List<String>, result: TeamMatchResult): Boolean {
        return datesList.isNotEmpty() && resultHasDifferentMonth(datesList[0], result)
    }

    private fun resultHasDifferentMonth(date: String, result: TeamMatchResult): Boolean {
        return formatDate(result.date) != date
    }

    private fun formatDate(rawDate: String?): String {
        return try {
            if (rawDate == null) {
                return "N/A"
            }
            val monthDate = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.parse(rawDate)
            if (date != null) monthDate.format(date) else "N/A"
        } catch (e: ParseException) {
            "N/A"
        }
    }

    private fun formatLocalDate(rawDate: String?): String {
        return try {
            if (rawDate == null) {
                return "N/A"
            }
            val monthDate = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault())
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
            val date = sdf.parse(rawDate)
            if (date != null) monthDate.format(date) else "N/A"
        } catch (e: ParseException) {
            "N/A"
        }
    }

    private fun formatMatchDay(rawDate: String?): String {
        return try {
            if (rawDate == null) {
                return "N/A"
            }
            val monthDate = SimpleDateFormat("dd EEE", Locale.getDefault())
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.parse(rawDate)
            if (date != null) monthDate.format(date) else "N/A"
        } catch (e: ParseException) {
            "N/A"
        }
    }
}
