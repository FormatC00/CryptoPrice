package com.github.formatc00.ui.dialog

import android.app.Activity
import android.text.TextUtils
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.github.formatc00.R
import com.github.formatc00.mvp.base.MessageInterface
import com.github.formatc00.util.Logger

class AlertMessageInterface(private val activity: Activity) : MessageInterface {
    
    private val logger = Logger.create(this)
    
    private var alertDialog: AlertDialog? = null
    
    @Synchronized
    private fun showAlert(title: String, message: String, confirmationListener: () -> Unit = {}) {
        if (alertDialog == null || alertDialog?.isShowing == false) {
            val builder = AlertDialog.Builder(activity).apply {
                setTitle(title)
                setMessage(message)
                setPositiveButton(R.string.ok) { _, _ -> confirmationListener() }
                setNegativeButton(R.string.cancel, null)
            }
            
            alertDialog = builder.show()
        } else {
            logger.d("skip alert message: title=$title;message=$message")
        }
    }
    
    private fun convertMessage(throwable: Throwable): String {
        val messageBuilder = StringBuilder(throwable.javaClass.simpleName)
        
        val message = throwable.message
        if (!TextUtils.isEmpty(message)) {
            messageBuilder.append(": ").append(message)
        }
        
        return messageBuilder.toString()
    }
    
    override fun showMessage(
        @StringRes titleResId: Int,
        @StringRes messageResId: Int,
        confirmationListener: () -> Unit
    ) {
        showAlert(
            activity.getString(titleResId),
            activity.getString(messageResId),
            confirmationListener
        )
    }
    
    override fun showError(throwable: Throwable) {
        showAlert(activity.getString(R.string.error_alert_title), convertMessage(throwable))
    }
    
    override fun showError(
        @StringRes messageResId: Int,
        confirmationListener: () -> Unit
    ) {
        showAlert(
            activity.getString(R.string.error_alert_title),
            activity.getString(messageResId),
            confirmationListener
        )
    }
    
    override fun showInfo(@StringRes messageResId: Int, confirmationListener: () -> Unit) {
        showAlert(
            activity.getString(R.string.info_alert_title),
            activity.getString(messageResId),
            confirmationListener
        )
    }
}
