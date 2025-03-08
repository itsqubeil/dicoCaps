package test.dapuk.dicodingcapstone.presentation.main

import android.content.Intent
import android.net.Uri
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
import test.dapuk.dicodingcapstone.R
import test.dapuk.dicodingcapstone.databinding.ActivityMainBinding
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.usecase.FavoriteUseCase
import test.dapuk.core.domain.usecase.GetDevsDetailUseCase
import test.dapuk.core.domain.usecase.GetDevsUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = DevAdapter()
    private val getDevsUseCase: GetDevsUseCase by inject()
    private val mainViewModel: MainViewModel by viewModel ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvDevs.layoutManager = LinearLayoutManager(this)

        binding.rvDevs.adapter = adapter

        binding.btnRetry.setOnClickListener {
            mainViewModel.getDevs()
            mainViewModel.devs.observe(this){
                    devsList ->
                if (devsList != null) {
                    setDevsData(devsList)
                }
            }
        }

        binding.floatingActionButton.setOnClickListener{
            val uri = Uri.parse("dicodingcapsapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        mainViewModel.devs.observe(this){
            devsList ->
            if (devsList != null) {
                setDevsData(devsList)
            }
        }

        mainViewModel.isLoading.observe(this){
            loading(it)
        }

        mainViewModel.isErr.observe(this){
            retry(it)
        }

        mainViewModel.errMsg.observe(this){
            errMsg(it)
        }
    }

    private fun setDevsData(devsLis: List<Devs>){
            adapter.submitList(devsLis)

    }

    private fun errMsg(msg: String){
        binding.tvError.text = msg
    }

    private fun retry(isErr: Boolean) {
        binding.let {
            if (isErr != false) {
                binding.btnRetry.visibility = View.VISIBLE
                binding.tvError.visibility = View.VISIBLE
            } else {
                binding.btnRetry.visibility = View.GONE
                binding.tvError.visibility = View.GONE
            }
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

}