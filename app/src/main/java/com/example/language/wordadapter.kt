package com.example.language

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat


/*
Word Adapter class with primary constructors that extend the ArrayAdapter<word> class
that was defined specially for this project
*/
class WordAdapter(context : Activity, words : ArrayList<Word>, private var colorResourceId : Int) :
    ArrayAdapter<Word>(context, 0, words) {


    //inflater that inflate the row view
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater

    // i override the getview method to inflate the rowView
    override fun getView(position : Int, convertView : View?, parent : ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_items, parent, false)

        //get the current word object located at this position
        val currentWord = getItem(position)


        //find the yoruba textView list_items.xml and get the current word
        val yorubaTextView = rowView.findViewById(R.id.yoruba_textView) as TextView
        if (currentWord != null) {
            yorubaTextView.text = currentWord.getYorubaTranslation()
        }

        // find the default textView list_items and get the yoruba translation also
        // check if default and currentWord is not empty
        val defaultTextView = rowView.findViewById<TextView>(R.id.default_textView)
        if (defaultTextView != null) {
            if (currentWord != null) {
                defaultTextView.text = currentWord.getDefaultTranslation()
            }
        }


        //find the image view from the list_item layout
        val listImage = rowView.findViewById<ImageView>(R.id.iconImage)
        if (currentWord != null) {
            if (currentWord.hasImage()) {
                // If an image is available,
                // Get the appropriate image from the current object and
                // set this resource id on the imageView
                listImage.setImageResource(currentWord.getImageResourceId())
                // If view was previously hidden, we need to make it visible again
                // If view was previously hidden, we need to make it visible again
                listImage.visibility = View.VISIBLE
            } else {
                // Hide the ImageView {set visibility to GONE}
                listImage.visibility = View.GONE
            }
        }

    // Set the theme color for the list item
    val textContainer = rowView.findViewById(R.id.text_container) as LinearLayout

    // Find the color that the resource ID maps to
    val color = ContextCompat.getColor(context, colorResourceId)
    // Set the background color of the text container View
    textContainer.setBackgroundColor(color)


        return rowView
}

}

/**
 * Jnr
 * */