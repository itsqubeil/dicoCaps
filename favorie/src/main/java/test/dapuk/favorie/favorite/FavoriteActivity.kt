package test.dapuk.favorie.favorite

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import test.dapuk.dicodingcapstone.R
import test.dapuk.dicodingcapstone.databinding.ActivityFavoriteBinding
import test.dapuk.core.domain.usecase.FavoriteUseCase
import test.dapuk.core.domain.usecase.GetDevsDetailUseCase
import test.dapuk.core.domain.usecase.GetDevsUseCase

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val adapter = FavoriteAdapter()
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        loadKoinModules(FavoriteModule)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvDevs.layoutManager = LinearLayoutManager(this)

        binding.rvDevs.adapter = adapter

        favoriteViewModel.favoriteDevs.observe(this) { favoriteList ->
            adapter.submitList(favoriteList)
        }

        favoriteViewModel.isLoading.observe(this){
            loading(it)
        }

        favoriteViewModel.isErr.observe(this){
            err(it)
        }
    }

    private fun loading(isLoading: Boolean) {
        binding.let {
            if (isLoading != false) {
                binding.progressBar2.visibility = View.VISIBLE
            } else {
                binding.progressBar2.visibility = View.GONE
            }
        }
    }

    private fun err(isErr: Boolean) {
        binding.let {
            if (isErr != false) {
                binding.tvError.visibility = View.VISIBLE
            } else {
                binding.tvError.visibility = View.GONE
            }
        }
    }
    override fun onResume() {
        super.onResume()
        favoriteViewModel.fetchFavorite()
    }
}