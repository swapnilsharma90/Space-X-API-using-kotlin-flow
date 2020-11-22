package com.swap.spacex.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProvider(private val context: Context) {
    fun getString(@StringRes resId: Int): String = context.getString(resId)

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

}