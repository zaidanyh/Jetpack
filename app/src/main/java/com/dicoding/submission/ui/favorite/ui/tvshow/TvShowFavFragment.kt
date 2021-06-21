package com.dicoding.submission.ui.favorite.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.R
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.databinding.FragmentTvShowFavBinding
import com.dicoding.submission.ui.detail.DetailActivity
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.TV_SHOW
import com.dicoding.submission.ui.viewmodel.FavoriteViewModel
import com.dicoding.submission.ui.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvShowFavFragment : Fragment() {

    private var _binding: FragmentTvShowFavBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var tvShowFavAdapter: TvShowFavAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvShowFavBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvTvShowFav)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            tvShowFavAdapter = TvShowFavAdapter()

            with(binding.rvTvShowFav) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowFavAdapter
            }

            viewModel.getFavTvShows().observe(viewLifecycleOwner, { tvShowFav ->
                if (tvShowFav.isNotEmpty()) {
                    tvShowFavAdapter.submitList(tvShowFav)
                    tvShowFavAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Tv Show Favorite List is Empty", Toast.LENGTH_SHORT).show()
                }
            })

            tvShowFavAdapter.setOnClickListener(object: TvShowFavAdapter.OnClickListener {
                override fun onItemClicked(tv: TvEntity) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, tv.id)
                        putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOW)
                    }
                    context?.startActivity(intent)
                }
            })
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val tvShow = tvShowFavAdapter.getSwipedData(swipedPosition)
                tvShow?.let { viewModel.setFavTvShow(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    tvShow?.let { viewModel.setFavTvShow(it) }
                }
                snackBar.show()
            }
        }

    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}