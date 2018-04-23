package alikazi.com.sentia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(var id: Int, var image: Image?, var default: Boolean?) : Parcelable
