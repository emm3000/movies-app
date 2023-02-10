package com.emm.moviesapp.fragments.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emm.moviesapp.fragments.moviedetail.screen.MovieDetailScreen
import com.emm.moviesapp.fragments.moviedetail.theme.MovieDetailTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MovieDetailTheme {
                    MovieDetailScreen(
                        viewModel = viewModel,
                        onBackButtonAction = { findNavController().popBackStack() },
                    )
                }
            }
        }
    }
}
