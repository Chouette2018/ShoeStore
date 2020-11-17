package com.udacity.shoestore

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val SIGN_IN_STATE = "com.udacity.shoestore.signin.state"
class Utility {
    companion object{
        fun signIn(email:String, password:String, activity: MainActivity){
            saveSignInState(activity, true)
        }

        fun createAccount(email:String, password:String, activity: MainActivity){
            saveSignInState(activity, true)
        }

        fun signOut(activity: MainActivity){
            saveSignInState(activity, false)
        }

        fun isSignedIn(activity: MainActivity):Boolean{
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
            return sharedPref.getBoolean(SIGN_IN_STATE, false)
        }

        private fun saveSignInState(activity: MainActivity, value:Boolean){
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()){
                putBoolean(SIGN_IN_STATE, value)
                apply()
            }
        }
    }
}