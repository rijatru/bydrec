package com.test.bydrec.viewmodel.providers

import com.test.business.fixtures.api.Fixture
import com.test.business.fixtures.api.FixturesController
import com.test.bydrec.R
import com.test.bydrec.model.Item
import com.test.bydrec.model.ListHeaderItem
import com.test.bydrec.model.ListItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class FixturesItemsProviderImpl(private val fixturesController: FixturesController) :
    ItemsProvider {

    override val items: List<Item>
        get() {
            val items = ArrayList<Item>()
            val fixtures = fixturesController.fixtures
            val datesList = getDates(fixtures)

            if (datesList.isNotEmpty()) {
                addHeaderItem(items, datesList)

                for (fixture in fixtures) {
                    if (canAddNewHeaderItem(datesList, fixture)) {
                        addNewHeaderItem(items, datesList)
                    }
                    addListItem(items, fixture)
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

    private fun addListItem(items: MutableList<Item>, fixture: Fixture) {
        val listItem =
            ListItem(
                fixture.homeTeam.name,
                fixture.awayTeam.name,
                formatDate(fixture.date),
                fixture.competitionStage.competition.name,
                fixture.venue.name,
                formatMatchDay(fixture.date),
                formatLocalDate(fixture.date),
                fixture.state,
                fixture.type,
                0,
                0
            )
        items.add(listItem)
    }

    private fun addHeaderItem(items: MutableList<Item>, datesList: List<String>) {
        val item = ListHeaderItem(datesList[0])
        items.add(item)
    }

    private fun getDates(fixtures: List<Fixture>): MutableList<String> {
        val dates = LinkedHashSet<String>()

        for (fixture in fixtures) {
            val formattedDate = formatDate(fixture.date)
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

    private fun canAddNewHeaderItem(datesList: List<String>, fixture: Fixture): Boolean {
        return datesList.isNotEmpty() && fixtureHasDifferentMonth(datesList[0], fixture)
    }

    private fun fixtureHasDifferentMonth(date: String, fixture: Fixture): Boolean {
        return formatDate(fixture.date) != date
    }

    fun formatDate(rawDate: String?): String {
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
