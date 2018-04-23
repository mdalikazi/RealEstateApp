package alikazi.com.sentia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(var id: Int,
                 var first_name: String?,
                 var last_name: String?,
                 var email: String?,
                 var avatar: Image?): Parcelable
