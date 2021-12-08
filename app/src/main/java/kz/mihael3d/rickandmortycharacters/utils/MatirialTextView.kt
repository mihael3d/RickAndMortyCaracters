package kz.mihael3d.rickandmortycharacters.utils

import com.google.android.material.textview.MaterialTextView
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.data.model.Status

fun setDrawableToMaterialTextView(view:MaterialTextView, status: Status){
    when(status){
        Status.ALIVE -> view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_profile,0,0,0)
        Status.DEAD -> view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_dashboard,0,0,0)
        Status.UNKNOWN -> view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_settings,0,0,0)
    }
}

