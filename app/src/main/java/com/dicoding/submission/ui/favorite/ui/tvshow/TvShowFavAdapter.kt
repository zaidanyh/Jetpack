package com.dicoding.submission.ui.favorite.ui.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submission.R
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.databinding.ItemTvBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TvShowFavAdapter: PagedListAdapter<TvEntity, TvShowFavAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onClickListener: OnClickListener

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)

    inner class TvShowViewHolder(private val binding: ItemTvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvEntity) {
            with(binding) {
                titleTv.text = tvShow.name
                dateTv.text = changeFormat(tvShow.first_air_date)
                descTv.text = tvShow.overview
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w1280"+tvShow.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imageTv)
                ratingBar.rating = rateAverage(tvShow.vote_average).toFloat()
                ratingAverageDetail.text = rateAverage(tvShow.vote_average).toString()
                ratingCountDetail.text = binding.root.resources.getString(R.string.rating_count, tvShow.vote_count)
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
        fun onItemClicked(tv: TvEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
            holder.itemView.setOnClickListener { onClickListener.onItemClicked(tvShow) }
        }
    }
}