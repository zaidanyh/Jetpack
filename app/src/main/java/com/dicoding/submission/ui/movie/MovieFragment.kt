package com.dicoding.submission.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.R
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.databinding.FragmentMovieBinding
import com.dicoding.submission.ui.detail.DetailActivity
import com.dicoding.submission.ui.favorite.FavoriteActivity
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.MOVIE
import com.dicoding.submission.ui.viewmodel.MyViewModel
import com.dicoding.submission.ui.viewmodel.ViewModelFactory
import com.dicoding.submission.utils.SortUtils.BEST
import com.dicoding.submission.utils.SortUtils.RANDOM
import com.dicoding.submission.utils.SortUtils.WORST
import com.dicoding.submission.vo.Resource
import com.dicoding.submission.vo.Status

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        _binding = FragmentMovieBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieAdapter = MovieAdapter()
            binding.progressBar.visibility = View.VISIBLE
            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
            viewModel.getMovies(BEST).observe(viewLifecycleOwner, setupMovie)

            movieAdapter.setOnClickListener(object : MovieAdapter.OnClickListener {
                override fun onItemClicked(movie: MoviesEntity) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, movie.id)
                        putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)
                    }
                    startActivity(intent)
                }
            })
        }
    }

    private val setupMovie = Observer<Resource<PagedList<MoviesEntity>>> { movies ->
        if (movies != null) {
            when(movies.status) {
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    movieAdapter.submitList(movies.data)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, movies.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_best -> {
                viewModel.getMovies(BEST).observe(viewLifecycleOwner, setupMovie)
                item.isChecked = true
            }

            R.id.action_worst -> {
                viewModel.getMovies(WORST).observe(viewLifecycleOwner, setupMovie)
                item.isChecked = true
            }
            R.id.action_random -> {
                viewModel.getMovies(RANDOM).observe(viewLifecycleOwner, setupMovie)
                item.isChecked = true
            }
            R.id.action_favorite -> startActivity(Intent(context, FavoriteActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}