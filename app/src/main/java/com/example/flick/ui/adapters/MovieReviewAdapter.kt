package com.example.flick.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.reviews.Review
import com.example.flick.databinding.ItemMovieReviewBinding

class MovieReviewAdapter :
    RecyclerView.Adapter<MovieReviewAdapter.MovieReviewsViewHolder>() {
    class MovieReviewsViewHolder(val binding: ItemMovieReviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieReviewBinding =
            ItemMovieReviewBinding.inflate(layoutInflater, parent, false)
        return MovieReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieReviewsViewHolder, position: Int) {
        val review = differ.currentList[position]
        val authorImage = review.author_details.avatar_path?.substringAfter("/")
        holder.binding.apply {
            tvReviewAuthorName.text = review.author
            tvReview.text = review.content.take(100)
            Glide.with(this.root).load(authorImage).into(ivAuthorImage)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
