package com.dicoding.submission.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submission.R
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.databinding.ItemMovieBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter : PagedListAdapter<MoviesEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onClickListener: OnClickListener

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
            holder.itemView.setOnClickListener { onClickListener.onItemClicked(movie) }
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            with(binding) {
                titleMovie.text = movie.title
                descMovie.text = movie.overview
                dateMovie.text = changeFormat(movie.release_date)
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w1280"+movie.poster_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imageMovie)
                ratingCountDetail.text = binding.root.resources.getString(R.string.rating_count, movie.vote_count)
                ratingBar.rating = rateAverage(movie.vote_average).toFloat()
                ratingAverageDetail.text = rateAverage(movie.vote_average).toString()
            }
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
            val newFormatDate = SimpleDateFormat("MMM dd, yyyy")
            newDate = newFormatDate.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return newDate
    }

    private fun rateAverage(rate: Double): Double {
        return if (rate > 5.0) {
            rate/2
        } else {
            rate
        }
    }

    interface OnClickListener {
        fun onItemClicked(movie: MoviesEntity)
    }
}