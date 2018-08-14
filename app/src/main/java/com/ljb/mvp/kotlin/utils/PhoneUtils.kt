package com.ljb.mvp.kotlin.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import com.ljb.mvp.kotlin.R

object PhoneUtils {


    fun callPhone(context: Context?, phone: String?) {
        if (TextUtils.isEmpty(phone) || context == null) return
        var telPhone = phone
        if (!phone!!.contains("tel:")) {
            telPhone = "tel:$phone"
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(telPhone))
        context.startActivity(intent)
    }

    fun copyText2Phone(context: Context?, text: String, showToast: Boolean) {
        if (context == null) return
        val manager = context.getSystemService(Context.CLIPBOARD_SERVICE) ?: return
        val cm = manager as ClipboardManager
        cm.primaryClip = ClipData.newPlainText(null, text)
        if (showToast) Toast.makeText(context, R.string.copy_success, Toast.LENGTH_SHORT).show()
    }

}