package alikazi.com.sentia.models

import android.os.Parcel
import android.os.Parcelable

data class Location(var id: Int) : Parcelable {

    var address_1: String? = null
    var address_2: String? = null
    var suburb: String? = null
    var state: String? = null
    var postcode: String? = null
    var country: String? = null
    var latitude: Double? = 0.00
    var longitude: Double? = 0.00
    var full_address: String? = null

    companion object {
        val CREATOR: Parcelable.Creator<Location> = object : Parcelable.Creator<Location> {
            override fun createFromParcel(p0: Parcel?): Location {
                return createFromParcel(p0)
            }

            override fun newArray(p0: Int): Array<Location?> {
                return arrayOfNulls(0)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(address_1)
        parcel?.writeString(address_2)
        parcel?.writeString(suburb)
        parcel?.writeString(state)
        parcel?.writeString(country)
        parcel?.writeDouble(writeNullableDoubleToParcel(latitude))
        parcel?.writeDouble(writeNullableDoubleToParcel(longitude))
        parcel?.writeString(full_address)
    }

    override fun describeContents(): Int {
        return 0
    }

    private fun writeNullableDoubleToParcel(d: Double?): Double = when(d) {
        null -> -1.00
        else -> d
    }
}
