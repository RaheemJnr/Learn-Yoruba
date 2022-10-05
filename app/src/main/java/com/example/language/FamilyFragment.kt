package com.example.language

import android.annotation.TargetApi
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.annotation.RequiresApi


class FamilyFragment : Fragment() {
    //init MediaPlayer
    private var mMediaPlayer : MediaPlayer? = null

    /** Handles audio focus when playing a sound file */
    private var mAudioManager : AudioManager? = null
    private var focusRequest : AudioFocusRequest? = null

    //function to release mediaPlayer after playing
    private val completedAudio = MediaPlayer.OnCompletionListener {
        // Now that the sound file has finished playing, release the media player resources.
        releaseMediaPlayer()
    }

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private var audioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
            // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
            // our app is allowed to continue playing sound but at a lower volume. We'll treat
            // both cases the same way because our app is playing short sound files.

            // Pause playback and reset player to the start of the file. That way, we can
            // play the word from the beginning when we resume playback.
            mMediaPlayer?.pause()
            mMediaPlayer?.seekTo(0)
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
            mMediaPlayer?.start()
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            // The AUDIOFOCUS_LOSS case means we've lost audio focus and
            // Stop playback and clean up resources
            releaseMediaPlayer()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.word_list, container, false)

        val mAudioManager = activity?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).run {
            setAudioAttributes(AudioAttributes.Builder().run {
                setUsage(AudioAttributes.USAGE_GAME)
                setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                build()
            })
            setAcceptsDelayedFocusGain(true)
            setOnAudioFocusChangeListener(audioFocusChangeListener, Handler())
            build()
        }

        val numberArray  :ArrayList<Word> = arrayListOf()

        itemsToDisplay(numberArray)


        // adapter view create a layout for the arrays using simple_list_item_1
        val itemAdapter = WordAdapter(activity!!, numberArray, R.color.category_family)

        //it then find the list to use by id in the layout.xml
        // and populate it
        val listView: ListView = rootView.findViewById(R.id.list)
        listView.adapter = itemAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _ : AdapterView<*>, _ : View, i: Int, _ : Long ->

            // Get the {@link Word} object at the given position the user clicked on
            val word: Word = numberArray[i]
            //release current audio before playing another one
            releaseMediaPlayer()
            val result : Int
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                @TargetApi(Build.VERSION_CODES.O)
                result = focusRequest?.let { mAudioManager.requestAudioFocus(it) }!!
            } else {
                @Suppress("DEPRECATION")
                result = mAudioManager.requestAudioFocus(
                    audioFocusChangeListener,
                    //use music stream
                    AudioManager.STREAM_MUSIC,
                    // use audio for short period of time
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                )
            }
            // if the request is granted do this
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // Start playback
                //play the media file that correspond to the position
                mMediaPlayer = MediaPlayer.create(activity, word.getAudioResource())
                //play audio
                mMediaPlayer?.start()
                //callBack function which call the release function when audio finish playing
                mMediaPlayer?.setOnCompletionListener(completedAudio)
            }
        }

        return rootView
    }

    private fun itemsToDisplay(numberArray : ArrayList<Word>) {
        numberArray.add(Word("Father", "baba", R.drawable.family_father, R.raw.father))
        numberArray.add(Word("Mother", "iya", R.drawable.family_mother, R.raw.mother))
        numberArray.add(
            Word(
                "Sister",
                "egbon obinrin",
                R.drawable.family_older_sister,
                R.raw.sister
            )
        )
        numberArray.add(
            Word(
                "brother",
                "egbon okunrin",
                R.drawable.family_older_brother,
                R.raw.brother
            )
        )
        numberArray.add(
            Word(
                "niece",
                "omo egbon obinrin",
                R.drawable.family_younger_sister,
                R.raw.niece
            )
        )
        numberArray.add(
            Word(
                "cousin",
                "omo egbon okunrin",
                R.drawable.family_younger_brother,
                R.raw.cousin
            )
        )
        numberArray.add(
            Word(
                "grandfather",
                "baba baba",
                R.drawable.family_grandfather,
                R.raw.grandfather
            )
        )
        numberArray.add(
            Word(
                "grandmother",
                "iya iya",
                R.drawable.family_grandmother,
                R.raw.grandmother
            )
        )
        numberArray.add(
            Word(
                "uncle",
                "egbon/aburo iya/baba nii okunrin",
                R.drawable.family_older_brother,
                R.raw.uncle
            )
        )
        numberArray.add(
            Word(
                "aunt",
                "egbon/aburo iya/baba nii obinrin",
                R.drawable.family_older_sister,
                R.raw.aunt
            )
        )
        numberArray.add(
            Word(
                "siblings",
                "omo iya ati baba mii",
                R.drawable.family_younger_sister,
                R.raw.siblings
            )
        )
        numberArray.add(
            Word(
                "step brother",
                "omo baba tabi iya mii okunrin",
                R.drawable.family_older_brother,
                R.raw.stepbrother
            )
        )
        numberArray += Word(
            "step sister",
            "omo baba tabi iya mii obinrin",
            R.drawable.family_older_sister,
            R.raw.stepsister
        )
    }

    /**
     * onStop method to stop the audio if the user stop interacting with the app
     */
    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    /**
     * the release media function
     */
    private fun releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer?.release()

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                focusRequest?.let { mAudioManager?.abandonAudioFocusRequest(it) }
            } else {
                @Suppress("DEPRECATION")
                mAudioManager?.abandonAudioFocus(audioFocusChangeListener)
            }
        }
    }
}
/**Jnr**/