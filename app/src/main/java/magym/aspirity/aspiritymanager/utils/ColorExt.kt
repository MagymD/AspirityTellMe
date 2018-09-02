package magym.aspirity.aspiritymanager.utils

import android.content.res.Resources
import android.support.v4.content.res.ResourcesCompat

fun Int.toColorInt(res: Resources): Int = ResourcesCompat.getColor(res, this, null)