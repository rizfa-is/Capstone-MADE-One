package com.issog.capstonemadeone.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.issog.capstonemadeone.core.data.Resources
import com.issog.capstonemadeone.databinding.FragmentTvShowBinding
import com.issog.capstonemadeone.ui.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tbShowAdapter: MovieAdapter
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(LayoutInflater.from(container?.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        getMovieData()
    }

    private fun getMovieData() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            when(it) {
                is Resources.Loading -> showLoading()

                is Resources.Success -> {
                    hideLoading()
                    tbShowAdapter.setMovieData(it.data)
                }

                is Resources.Error -> {
                    Toast.makeText(this.context, "${it.code}-${it.message}", Toast.LENGTH_SHORT).show()
                }

                is Resources.NetworkError -> {
                    Toast.makeText(this.context, "Network Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initAdapter() {
        tbShowAdapter = MovieAdapter()
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            adapter = tbShowAdapter
        }
    }

    private fun showLoading() {
        binding.apply {
            loading.isVisible = true
            rvMovie.isVisible = false
        }
    }

    private fun hideLoading() {
        binding.apply {
            loading.isVisible = false
            rvMovie.isVisible = true
        }
    }
}