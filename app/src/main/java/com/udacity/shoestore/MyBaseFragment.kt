package com.udacity.shoestore

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class MyBaseFragment :Fragment(){
    fun initMyFragments(title:String, hasOwnMenu:Boolean){
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
        setHasOptionsMenu(hasOwnMenu)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.sign_out).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }
}