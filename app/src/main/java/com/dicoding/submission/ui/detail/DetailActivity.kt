package com.dicoding.submission.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submission.R
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.databinding.ActivityDetailBinding
import com.dicoding.submission.ui.viewmodel.DetailViewModel
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.MOVIE
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.TV_SHOW
import com.dicoding.submission.ui.viewmodel.ViewModelFactory
import com.dicoding.submission.vo.Status
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TYPE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_tv"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_arrow_back))
        }

        binding.progressBar.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getInt(EXTRA_TYPE, 0)
            category = extras.getString(EXTRA_CATEGORY)

            if (dataId != 0 && category != null) {
                viewModel.setData(dataId, category.toString())
                setupState()
                if (category == MOVIE) {
                    viewModel.getDetailMovie().observe(this, { movie ->
                        when(movie.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                if (movie.data != null) {
                                    setupDetailMovie(movie.data)
                                }
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, movie.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else if (category == TV_SHOW) {
                    viewModel.getDetailTvShow().observe(this, { tvShow ->
                        when(tvShow.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                if (tvShow.data != null) {
                                    setupDetailTv(tvShow.data)
                                }
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, tvShow.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        }
        binding.fabFav.setOnClickListener(this)
    }

    private fun setupDetailMovie(movie: MoviesEntity) {
        title = resources.getString(R.string.detailMovie)
        with(binding) {
            linearCreate.visibility = View.GONE
            storyDetail.visibility = View.GONE
            textStory.visibility = View.GONE
            titleDetail.text = movie.title
            dateDetail.text = changeFormat(movie.release_date)
            overviewDetail.text = movie.overview
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w1280"+movie.poster_path)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(imgDetail)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w1280"+movie.backdrop_path)
                .into(backgroundImageDetail)
            ratingCountDetail.text = resources.getString(R.string.rating_count, movie.vote_count)
            ratingBar.rating = countRate(movie.vote_average).toFloat()
            ratingAverageDetail.text = countRate(movie.vote_average).toString()
        }
    }

    private fun setupDetailTv(tvShow: TvEntity) {
        title = resources.getString(R.string.detailTV)
        with(binding) {
            tagLineDetail.visibility = View.GONE
            textDirector.text = resources.getString(R.string.creator)
            textScreenplay.visibility = View.GONE
            textStory.visibility = View.GONE
            screenplayDetail.visibility = View.GONE
            titleDetail.text = tvShow.name
            dateDetail.text = changeFormat(tvShow.first_air_date)
            overviewDetail.text = tvShow.overview

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w1280"+tvShow.poster_path)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(imgDetail)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w1280"+tvShow.backdrop_path)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(backgroundImageDetail)
            ratingCountDetail.text = resources.getString(R.string.rating_count, tvShow.vote_count)
            ratingBar.rating = countRate(tvShow.vote_average).toFloat()
            ratingAverageDetail.text = countRate(tvShow.vote_average).toString()
        }
    }

    private fun setupState() {
        if (category == MOVIE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                when(movie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            binding.progressBar.visibility = View.GONE
                            setFavoriteState(movie.data.isFav)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, movie.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (category == TV_SHOW) {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when(tvShow.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            binding.progressBar.visibility = View.GONE
                            setFavoriteState(tvShow.data.isFav)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, tvShow.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun onFabClicked() {
        if (category == MOVIE) {
            viewModel.setFavoriteMovie()
        } else if (category == TV_SHOW) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        val fab = binding.fabFav
        if (!state) {
            fab.setImageResource(R.drawable.ic_baseline_favorite_border)
        } else {
            fab.setImageResource(R.drawable.ic_baseline_favorite)
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    private fun changeFormat(date: String): String {
        lateinit var newDate: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val oldDate: Date
        try {
            oldDate = simpleDateFormat.parse(date)
            val newFormatDate = SimpleDateFormat("dd/MM/yyyy")
            newDate = newFormatDate.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return newDate
    }

    private fun countRate(rate: Double): Double {
        return if (rate > 5.0) {
            rate/2
        } else {
            rate
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.fabFav -> onFabClicked()
        }
    }
}