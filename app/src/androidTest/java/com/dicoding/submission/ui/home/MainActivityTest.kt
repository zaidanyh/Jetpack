package com.dicoding.submission.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.submission.R
import com.dicoding.submission.utils.DataDummy
import com.dicoding.submission.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy.generateRemoteDummyMovie()
    private val dummyTvShow = DataDummy.generateRemoteDummyTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdIdlingResource())
    }

    @Test
    fun getMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun getDetailMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_count_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_average_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun addDeleteFavMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fabFav)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        openActionBarOverflowOrOptionsMenu(appContext)
        onView(withText("Favorite List")).perform(click())
        onView(withId(R.id.rvMovieFav)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                swipeLeft()
            )
        )
    }

    @Test
    fun detailFavMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.fabFav)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        openActionBarOverflowOrOptionsMenu(appContext)
        onView(withText("Favorite List")).perform(click())
        onView(withId(R.id.rvMovieFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabFav)).perform(click())
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_count_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_average_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun getTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            dummyTvShow.size
        ))
    }
    
    @Test
    fun getDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2, click()
            )
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_count_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_average_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun addDeleteFavTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.fabFav)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        openActionBarOverflowOrOptionsMenu(appContext)
        onView(withText("Favorite List")).perform(click())
        onView(withId(R.id.navigation_tvShow)).perform(click())
        onView(withId(R.id.rvTvShowFav)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                swipeLeft()
            )
        )
    }

    @Test
    fun detailFavTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.fabFav)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        openActionBarOverflowOrOptionsMenu(context)
        onView(withText("Favorite List")).perform(click())
        onView(withId(R.id.navigation_tvShow)).perform(click())
        onView(withId(R.id.rvTvShowFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabFav)).perform(click())
        onView(withId(R.id.rating_count_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_average_detail)).check(matches(isDisplayed()))
    }
}