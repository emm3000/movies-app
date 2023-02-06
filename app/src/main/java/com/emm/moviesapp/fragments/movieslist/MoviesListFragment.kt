package com.emm.moviesapp.fragments.movieslist

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emm.moviesapp.adapters.MovieItemAdapter
import com.emm.moviesapp.databinding.FragmentMoviesListBinding
import com.emm.moviesapp.model.MovieUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MoviesListViewModel by viewModels()

    private val movieAdapter: MovieItemAdapter by lazy { MovieItemAdapter(::onItemClick) }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
        val granted = permissions.entries.all { it.value }
        if (granted) {
            Toast.makeText(requireContext(), "Permissions granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Permissions not granted", Toast.LENGTH_SHORT).show()
        }
    }

    // format x d w
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        binding.moviesListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        binding.rvMovieList.adapter = movieAdapter
    }

    private fun onItemClick(movie: MovieUI) {
        findNavController().navigate(MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(movie.id))
    }

    private fun requestPermissions() {
        val permissionToRequest = mutableListOf<String>()
        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        val hasAccessFineLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        val hasAccessCoarseLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        val hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA)
        val hasWriteExternalStorage = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) || minSdk29
        val hasReadExternalStorage = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)

        if (!hasAccessFineLocationPermission) {
            permissionToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!hasAccessCoarseLocationPermission) {
            permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasCameraPermission) {
            permissionToRequest.add(Manifest.permission.CAMERA)
        }
        if (!hasWriteExternalStorage) {
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasReadExternalStorage) {
            permissionToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionToRequest.isNotEmpty()) {
            requestPermission.launch(permissionToRequest.toTypedArray())
        }
    }

    private fun checkSelfPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission,
        ) == PackageManager.PERMISSION_GRANTED
    }
}
