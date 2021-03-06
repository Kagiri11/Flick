package com.example.flick.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.domain.models.Movie
import com.example.flick.databinding.ItemMovieBinding
import com.example.flick.ui.utils.DominantColorExtractor

class NowPlayingMoviesAdapter :
    RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingViewHolder>() {
    class NowPlayingViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(layoutInflater, parent, false)
        return NowPlayingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration + movie.posterPath
        holder.binding.apply {
            tvMovieName.text = movie.title
            tvMovieRating.text = movie.voteAverage.toString()
            Glide.with(this.root)
                .load(movieImage)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .listener(
                    DominantColorExtractor(fadingEdgeLayout, tvMovieName, tvMovieRating)
                )
                .centerCrop()
                .into(ivMovieImage)
            this.root.setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = differ.currentList.size
}
