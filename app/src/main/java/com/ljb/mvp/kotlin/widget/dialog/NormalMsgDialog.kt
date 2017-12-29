package com.ljb.mvp.kotlin.widget.dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.ljb.mvp.kotlin.R

/**
 * Created by L on 2017/12/29.
 */
class NormalMsgDialog(val mActivity: Activity) : View.OnClickListener {


    private val mDialog: Dialog = Dialog(mActivity, R.style.mask_dialog)
    private val mView: View = View.inflate(mActivity, R.layout.dialog_msg_normal, null)
    private val mTvMsg = mView.findViewById(R.id.tv_msg) as TextView
    private val mBtnLeft = mView.findViewById(R.id.btn_left) as Button
    private val mBtnRight = mView.findViewById(R.id.btn_right) as Button

    private var mRightButtonClickListener: DialogInterface.OnClickListener? = null
    private var mLeftButtonClickListener: DialogInterface.OnClickListener? = null


    init {
        mDialog.setContentView(mView, ViewGroup.LayoutParams(dip2px(270f), ViewGroup.LayoutParams.MATCH_PARENT))
        mDialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 0)
        mDialog.window.setGravity(Gravity.CENTER)

        mBtnLeft.setOnClickListener(this)
        mBtnRight.setOnClickListener(this)
    }

    private fun dip2px(dip: Float): Int {
        val scale = mActivity.resources.displayMetrics.density
        return (dip * scale + 0.5f).toInt()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_left) {
            mLeftButtonClickListener?.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE)
        } else if (v.id == R.id.btn_right) {
            mRightButtonClickListener?.onClick(mDialog, DialogInterface.BUTTON_POSITIVE)
        }
        dismiss()
    }

    fun show() {
        mDialog.show()
    }

    fun dismiss() {
        if (mDialog.isShowing) {
            mDialog.dismiss()
        }
    }


    fun setMessage(strId: String): NormalMsgDialog {
        mTvMsg.text = strId
        return this
    }

    fun setMessage(strId: Int): NormalMsgDialog {
        mTvMsg.setText(strId)
        return this
    }


    fun setLeftButtonInfo(strId: Int, listener: DialogInterface.OnClickListener? = null): NormalMsgDialog {
        return setLeftButtonInfo(mActivity.getString(strId), listener)
    }

    fun setRightButtonInfo(strId: Int, listener: DialogInterface.OnClickListener? = null): NormalMsgDialog {
        return setRightButtonInfo(mActivity.getString(strId), listener)
    }

    fun setLeftButtonInfo(str: String, listener: DialogInterface.OnClickListener? = null): NormalMsgDialog {
        if (!TextUtils.isEmpty(str)) {
            mBtnLeft.text = str
        }
        this.mLeftButtonClickListener = listener
        return this
    }

    fun setRightButtonInfo(str: String, listener: DialogInterface.OnClickListener? = null): NormalMsgDialog {
        if (!TextUtils.isEmpty(str)) {
            mBtnRight.text = str
        }
        this.mRightButtonClickListener = listener
        return this
    }


}