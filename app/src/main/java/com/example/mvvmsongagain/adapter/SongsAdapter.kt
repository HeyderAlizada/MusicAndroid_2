package com.example.mvvmsongagain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsongagain.databinding.ItemSongBinding
import com.example.mvvmsongagain.models.Data
import com.example.mvvmsongagain.search.SearchFragmentDirections
import com.squareup.picasso.Picasso

class SongsAdapter(val songs: List<Data>) : RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongsViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SongsViewHolder,
        position: Int
    ) {
        val song = songs[position]
        holder.binding.artistName.text = song.artist.name
        Picasso.get().load(song.album.cover_medium).into(holder.binding.imageView)
        holder.binding.songDuration.text = FormatDuration.format(song.duration)
        holder.binding.songName.text = song.title
        holder.binding.root.setOnClickListener {
            findNavController(it).navigate(SearchFragmentDirections.actionSearchFragmentToPlayerFragment(song))
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }




    class SongsViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

}