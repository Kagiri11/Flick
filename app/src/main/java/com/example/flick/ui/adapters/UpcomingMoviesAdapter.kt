package com.example.flick.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.Movie
import com.example.flick.databinding.ItemUpcomingMoviesBinding

class UpcomingMoviesAdapter :
    RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>() {
    class UpcomingMoviesViewHolder(val binding: ItemUpcomingMoviesBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemUpcomingMoviesBinding =
            ItemUpcomingMoviesBinding.inflate(layoutInflater, parent, false)
        return UpcomingMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration + movie.posterPath
        holder.binding.apply {
            tvUpcomingMovieName.text = movie.title
            Glide.with(this.root).load(movieImage).into(ivUpcomingMovie)
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
