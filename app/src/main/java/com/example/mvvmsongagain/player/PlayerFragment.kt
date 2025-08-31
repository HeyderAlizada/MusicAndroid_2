package com.example.mvvmsongagain.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mvvmsongagain.PicassoHelper
import com.example.mvvmsongagain.R
import com.example.mvvmsongagain.adapter.FormatDuration
import com.example.mvvmsongagain.databinding.FragmentPlayerBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPlayerBinding
    private val args: PlayerFragmentArgs by navArgs()
    private val viewModel: PlayingConditionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        (dialog as? BottomSheetDialog)?.let { bottomSheetDialog ->
            val bottomSheet = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            bottomSheet?.let {
                binding.root.layoutParams.height =
                    (resources.displayMetrics.heightPixels * 1).toInt()
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Load album art
        PicassoHelper.addImage(args.song.album.cover_big, binding.albumCover)

        // Static info
        binding.songName.text = args.song.title
        binding.artsitID.text = args.song.artist.name
        binding.songDuration.text = FormatDuration.format(30)

        viewModel.isPlaying.observe(viewLifecycleOwner) { playing ->
            binding.playPauseBtn.setImageResource(
                if (playing) R.drawable.ic_pause else R.drawable.ic_play
            )
        }

        viewModel.duration.observe(viewLifecycleOwner) { duration ->
            binding.songSeekBar.max = duration
        }

        viewModel.progress.observe(viewLifecycleOwner) { progress ->
            binding.songSeekBar.progress = progress
            val seconds = ((progress + 999) / 1000) // round up
            binding.currentTime.text = FormatDuration.format(seconds)
        }

        binding.playPauseBtn.setOnClickListener {
            viewModel.playPause(args.song.preview)
        }

        binding.songSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) viewModel.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.releasePlayer()
    }
}
