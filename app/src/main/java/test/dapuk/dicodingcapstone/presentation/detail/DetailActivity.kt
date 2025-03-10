package test.dapuk.dicodingcapstone.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.model.DevsDetail
import test.dapuk.dicodingcapstone.R
import test.dapuk.dicodingcapstone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getStringExtra("id")
        if (id != null) {
            detailViewModel.setDetailId(id)
            Log.d("ingpo id", id)

            binding.btnRetry.setOnClickListener {
                detailViewModel.setDetailId(id)
                detailViewModel.devs.observe(this) { devsList ->
                    if (devsList != null) {
                        setDetail(devsList)
                    }
                }
            }

            detailViewModel.devs.observe(this) { devs ->
                if (devs != null) {
                detailViewModel.checkFavoriteStatus(devs.id)
                setDetail(devs)
                binding.fabToggle.setOnClickListener{
                    val devsFav = Devs(
                        id = devs.id,
                        name = devs.name,
                        image = devs.image,
                        gamesCount = devs.gamesCount,
                        imageBackground = devs.imageBackground,
                        slug = devs.slug,

                    )
                    detailViewModel.toggleFavorite(devsFav)
                }
                } else {
                    Log.e("DetailActivity", "Data devs null")
                }
            }

            detailViewModel.isLoading.observe(this) {
                loading(it)
            }

            detailViewModel.isErr.observe(this) {
                retry(it)
            }

            detailViewModel.errMsg.observe(this) {
                errMsg(it)
            }

            detailViewModel.viewCont.observe(this) {
                contView(it)
            }

            detailViewModel.isFav.observe(this) { isFav ->
                if (isFav) {
                    binding.fabToggle.setImageResource(R.drawable.heartfull)
                } else {
                    binding.fabToggle.setImageResource(R.drawable.heartempty)
                }
            }


        } else {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Not Found")
                .setMessage("invalid")
                .setPositiveButton("OK") { _, _ ->
                    finish()
                }
                .setCancelable(false)
                .create()

            alertDialog.show()
            binding.cont.visibility = View.GONE
        }
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

    private fun contView(isErr: Boolean) {
        binding.let {
            if (isErr != false) {
                binding.cont.visibility = View.VISIBLE
            } else {
                binding.cont.visibility = View.GONE
            }
        }
    }

    private fun setDetail(devs: DevsDetail) {
        Glide.with(this)
            .load(devs.imageBackground)
            .into(binding.ivGamepic)
        Glide.with(this)
            .load(devs.image)
            .into(binding.ivDevpic)
        binding.tvDevname.text = devs.name
        binding.tvGamecountnumber.text = devs.gamesCount.toString()
        binding.tvDesc.text =  HtmlCompat.fromHtml(devs.description,HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun loading(isLoading: Boolean) {
        binding.let {
            if (isLoading != false) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}