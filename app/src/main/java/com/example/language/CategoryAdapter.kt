package com.example.language

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link Word} objects.
 *
 * Create a new {@link CategoryAdapter} object.
 *
 * @param context is the context of the app
 * @param fragmentManager is the fragment manager that will keep each fragment's state in the adapter
 *           across swipes.
 */
@Suppress("DEPRECATION")
class CategoryAdapter(private var context : Context, fragmentManager : FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    override fun getItem(position : Int) : Fragment {
        return when (position) {
            0 -> {
                NumberFragment()
            }
            1 -> {
                FamilyFragment()
            }
            2 -> {
                ColorFragment()
            }
            else -> {
                PhrasesFragment()
            }
        }
    }

    /**
     * Return the total number of pages.
     */
    override fun getCount() : Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                context.getString(R.string.category_numbers)
            }
            1 -> {
                context.getString(R.string.category_family)
            }
            2 -> {
                context.getString(R.string.category_colors)
            }
            else -> {
                context.getString(R.string.category_phrases)
            }
        }
    }
}