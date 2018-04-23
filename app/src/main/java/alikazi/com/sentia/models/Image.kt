package alikazi.com.sentia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(var url: String,
                 var small: SpecificSizeUrl?,
                 var medium: SpecificSizeUrl?,
                 var large: SpecificSizeUrl?,
                 var profile: SpecificSizeUrl?) : Parcelable
