package magym.aspirity.aspiritymanager.utils

import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View

fun View.showSnackbarExt(text: String, buttonTitle: String = "", funAction: (() -> Unit)? = null): Snackbar {
    var snackbar = Snackbar.make(this, text, Snackbar.LENGTH_LONG)

    funAction?.let {
        snackbar = Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
                .setAction(buttonTitle, snackbarOnClickListener(funAction))
    }

    Log.d("qwerty", text)
    snackbar.show()
    return snackbar
}

private fun snackbarOnClickListener(funAction: () -> Unit) = View.OnClickListener { funAction() }