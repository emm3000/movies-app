package com.emm.moviesapp.fragments.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emm.moviesapp.adapters.MovieItemAdapter
import com.emm.moviesapp.databinding.FragmentMoviesListBinding
import com.emm.moviesapp.model.MovieModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MoviesListViewModel by viewModels()

    private val movieAdapter: MovieItemAdapter by lazy { MovieItemAdapter(::onItemClick) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        binding.moviesListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovieList.adapter = movieAdapter
    }

    private fun onItemClick(movie: MovieModel) {

    }


}