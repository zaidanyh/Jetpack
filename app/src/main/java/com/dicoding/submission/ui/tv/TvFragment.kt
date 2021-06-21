package com.dicoding.submission.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.R
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.databinding.FragmentTvBinding
import com.dicoding.submission.ui.detail.DetailActivity
import com.dicoding.submission.ui.favorite.FavoriteActivity
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.TV_SHOW
import com.dicoding.submission.ui.viewmodel.MyViewModel
import com.dicoding.submission.ui.viewmodel.ViewModelFactory
import com.dicoding.submission.utils.SortUtils.BEST
import com.dicoding.submission.utils.SortUtils.RANDOM
import com.dicoding.submission.utils.SortUtils.WORST
import com.dicoding.submission.vo.Resource
import com.dicoding.submission.vo.Status

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModel
    private lateinit var tvAdapter: TvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            tvAdapter = TvAdapter()
            binding.progressBar.visibility = View.VISIBLE
            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }

            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
            viewModel.getTvShows(BEST).observe(viewLifecycleOwner, setupTvShow)

            tvAdapter.setOnClickListener(object : TvAdapter.OnClickListener {
                override fun onItemClicked(tv: TvEntity) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, tv.id)
                        putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOW)
                    }
                    startActivity(intent)
                }
            })
        }
    }

    private val setupTvShow = Observer<Resource<PagedList<TvEntity>>> { tvShow ->
        if (tvShow != null) {
            when(tvShow.status) {
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    tvAdapter.submitList(tvShow.data)
                    tvAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
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
                viewModel.getTvShows(BEST).observe(viewLifecycleOwner, setupTvShow)
                item.isChecked = true
            }

            R.id.action_worst -> {
                viewModel.getTvShows(WORST).observe(viewLifecycleOwner, setupTvShow)
                item.isChecked = true
            }
            R.id.action_random -> {
                viewModel.getTvShows(RANDOM).observe(viewLifecycleOwner, setupTvShow)
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