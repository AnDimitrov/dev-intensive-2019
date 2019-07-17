package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.round

fun Activity.hideKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = findViewById<View>(android.R.id.content)
    val r = Rect()
    rootView.getWindowVisibleDisplayFrame(r)
//    val dm = rootView.resources.displayMetrics
//    val heightDiff = rootView.bottom - r.bottom
//
//    Log.d("M_Activity", rootView.bottom.toString())
//    Log.d("M_Activity", r.bottom.toString())

//    return heightDiff > dm.density * 128

    val heightDiff = rootView.height - r.height()
    val marginOfError = round(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            50F,
            this.resources.displayMetrics
        )
    )
    
//    val orientation = rootView.resources.configuration.orientation
//    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//        return heightDiff > marginOfError
//    }
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}