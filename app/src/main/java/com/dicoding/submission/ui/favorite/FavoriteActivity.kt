package com.dicoding.submission.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dicoding.submission.R
import com.dicoding.submission.databinding.ActivityFavoriteBinding
import com.dicoding.submission.ui.favorite.ui.movies.MovieFavFragment
import com.dicoding.submission.ui.favorite.ui.tvshow.TvShowFavFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = resources.getString(R.string.menu_favorite)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(ContextCompat.getDrawable(this@FavoriteActivity, R.drawable.ic_arrow_back))
        }

        with(binding) {
            botNav.setOnNavigationItemSelectedListener(this@FavoriteActivity)
        }
        loadFragment(MovieFavFragment())
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            when(fragment) {
                is MovieFavFragment -> binding.botNav.menu.getItem(0).isChecked = true
                is TvShowFavFragment -> binding.botNav.menu.getItem(1).isChecked = true
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentParent, fragment)
                .commit()
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when(item.itemId) {
            R.id.navigation_movie -> fragment = MovieFavFragment()
            R.id.navigation_tvShow -> fragment = TvShowFavFragment()
        }
        return loadFragment(fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}