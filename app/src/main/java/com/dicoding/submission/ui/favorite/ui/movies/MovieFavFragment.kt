package com.dicoding.submission.ui.favorite.ui.movies

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
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.databinding.FragmentMovieFavBinding
import com.dicoding.submission.ui.detail.DetailActivity
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.MOVIE
import com.dicoding.submission.ui.viewmodel.FavoriteViewModel
import com.dicoding.submission.ui.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MovieFavFragment : Fragment() {

    private var _binding: FragmentMovieFavBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var movieFavAdapter: MovieFavAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieFavBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvMovieFav)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            movieFavAdapter = MovieFavAdapter()

            with(binding.rvMovieFav) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieFavAdapter
            }

            viewModel.getFavMovies().observe(viewLifecycleOwner, { favMov ->
                if (favMov.isNotEmpty()) {
                    movieFavAdapter.submitList(favMov)
                    movieFavAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Movie Favorite List is Empty", Toast.LENGTH_SHORT).show()
                }
            })

            movieFavAdapter.setOnClickListener(object: MovieFavAdapter.OnClickListener {
                override fun onItemClicked(movie: MoviesEntity) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, movie.id)
                        putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)
                    }
                    context?.startActivity(intent)
                }
            })
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val movieEntity = movieFavAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavMovie(it) }

                val snackBar = Snackbar.make(binding.root, R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    movieEntity?.let { viewModel.setFavMovie(it) }
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