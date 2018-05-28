package alikazi.com.codesample.propertyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(var id: Int,
                    var address_1: String?,
                    var address_2: String?,
                    var suburb: String?,
                    var state: String?,
                    var postcode: String?,
                    var country: String?,
                    var latitude: Double?,
                    var longitude: Double?,
                    var full_address: String?) : Parcelable
