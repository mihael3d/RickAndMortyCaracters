package kz.mihael3d.rickandmortycharacters.utils

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.data.characters.Status

fun setDrawableToMaterialTextView(view:MaterialTextView, status: Status){

    with(view) {
        when (status) {
            Status.ALIVE -> {
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_alive2, 0, 0, 0)
                setDrawableLeft(R.color.statusAliveColor)
            }
            Status.DEAD -> {
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_status_ded, 0, 0, 0)
                setDrawableLeft(R.color.statusDedColor)}
            Status.UNKNOWN -> {
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_confuse, 0, 0, 0)
            setDrawableLeft(R.color.statusUnknownColor)}
        }
    }
}

fun MaterialTextView.setDrawableLeft(@ColorRes res: Int) {
    if (compoundDrawables[0] == null) return
    compoundDrawables[0].setTint(ContextCompat.getColor(context, res))
}


