package com.example.flick.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.domain.models.Cast
import com.example.flick.databinding.ItemMovieCastBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    class CastViewHolder(val binding: ItemMovieCastBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieCastBinding =
            ItemMovieCastBinding.inflate(layoutInflater, parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val castImage = configuration + cast.profile_path
        holder.binding.apply {
            tvCastName.text = cast.name
            tvCastCharacter.text = cast.character
            Glide.with(this.root)
                .load(castImage)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .circleCrop().into(ivCastImage)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
