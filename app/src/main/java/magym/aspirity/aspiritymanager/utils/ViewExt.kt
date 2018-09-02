package magym.aspirity.aspiritymanager.utils

import android.app.Activity
import android.content.res.ColorStateList
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import magym.aspirity.aspiritymanager.R

fun ActionBar.init(title: String = "") {
    this.setDisplayHomeAsUpEnabled(true)
    this.setDisplayShowHomeEnabled(true)
    this.title = title
}

fun Toolbar.init(activity: AppCompatActivity): Toolbar {
    activity.setSupportActionBar(this)
    return this
}

fun SwipeRefreshLayout.init(listener: SwipeRefreshLayout.OnRefreshListener): SwipeRefreshLayout {
    this.setOnRefreshListener(listener)
    this.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent)
    return this
}

fun NavigationView.init(toolbar: Toolbar, drawerLayout: DrawerLayout, listener: NavigationView.OnNavigationItemSelectedListener, activity: Activity): Menu {
    val toggle = ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
    this.setNavigationItemSelectedListener(listener)

    val colorStateList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked)),
            intArrayOf(R.color.colorAccent.toColorInt(resources), R.color.menu.toColorInt(resources))
    )

    this.itemIconTintList = colorStateList
    this.itemTextColor = colorStateList

    return this.menu
}