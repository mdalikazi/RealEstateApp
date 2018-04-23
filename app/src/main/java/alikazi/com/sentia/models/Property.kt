package alikazi.com.sentia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(var id: Int?,
                    var title: String?,
                    var is_premium: Boolean?,
                    var state: String?,
                    var bedrooms: Int?,
                    var bathrooms: Int?,
                    var carspots: Int?,
                    var description: String?,
                    var price: Float?,
                    var owner: Owner?,
                    var location: Location?,
                    var photo: Photo?) : Parcelable
