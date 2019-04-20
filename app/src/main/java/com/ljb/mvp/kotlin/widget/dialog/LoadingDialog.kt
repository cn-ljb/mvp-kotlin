package com.ljb.mvp.kotlin.widget.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import com.ljb.mvp.kotlin.R

class LoadingDialog(context: Context, val isBack: Boolean = true) : Dialog(context) {

    constructor(context: Context) : this(context, true)

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawableResource(android.R.color.transparent)
        window.setDimAmount(0f)
        setContentView(View.inflate(getContext(), R.layout.dialog_loading, null))
        setCancelable(isBack)
        setCanceledOnTouchOutside(isBack)
    }

}