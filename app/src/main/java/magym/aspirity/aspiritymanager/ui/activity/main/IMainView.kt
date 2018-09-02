package magym.aspirity.aspiritymanager.ui.activity.main

interface IMainView {

    fun setTitleToolbar(resId: Int)

    fun setVisibilityFab(visibility: Boolean)

    fun startChooseFilterActivity()

    fun showSnackbar(resId: Int)

    fun hasConnection(): Boolean

    fun showException(t: Throwable)

}