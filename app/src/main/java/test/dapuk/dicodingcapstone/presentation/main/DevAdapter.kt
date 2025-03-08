package test.dapuk.dicodingcapstone.presentation.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import test.dapuk.core.databinding.ItemListDevsBinding
import test.dapuk.core.domain.model.Devs
import test.dapuk.dicodingcapstone.R
import test.dapuk.dicodingcapstone.presentation.detail.DetailActivity

class DevAdapter : ListAdapter<Devs, DevAdapter.ListAdapterHolder>(DIFF_CALLBACK) {
    class ListAdapterHolder(val binding: ItemListDevsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(devs: Devs){
            devs.imageBackground.let {
                Glide.with(binding.ivGamepic.context)
                    .load(it)
                    .timeout(5000)
                    .error(R.drawable.failed_load_img)
                    .listener(object : RequestListener<Drawable> {

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(binding.ivGamepic)
            }
            devs.image.let {
                Glide.with(binding.ivDevpic.context)
                    .load(it)
                    .timeout(5000)
                    .error(R.drawable.failed_load_img)
                    .listener(object : RequestListener<Drawable> {

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(binding.ivDevpic)
            }
            binding.tvDevname.text = devs.name
            binding.tvGamecountnumber.text = devs.gamesCount.toString()
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapterHolder {
        val binding = ItemListDevsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapterHolder, position: Int) {
        val developers = getItem(position)
        holder.bind(developers)
        holder.itemView.setOnClickListener {

            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("id", developers.id.toString())
            holder.itemView.context.startActivity(intentDetail)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Devs>() {
            override fun areItemsTheSame(oldItem: Devs, newItem: Devs): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Devs, newItem: Devs): Boolean {
                return oldItem == newItem
            }
        }
    }
}