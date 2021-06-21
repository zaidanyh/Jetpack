package com.dicoding.submission.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.submission.ui.movie.MovieFragment
import com.dicoding.submission.ui.tv.TvFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvFragment()
        }

        return fragment as Fragment
    }
}