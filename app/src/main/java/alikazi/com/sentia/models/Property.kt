package alikazi.com.sentia.models

import android.os.Parcel
import android.os.Parcelable

data class Property(var id: Int) : Parcelable {

    var title: String? = null
    var is_premium: Boolean? = false
    var state: String? = null
    var bedrooms: Int? = 0
    var bathrooms: Int? = 0
    var carspots: Int? = 0
    var description: String? = null
    var price: Float? = 0.00f
    var owner: Owner? = null
    var location: Location? = null
    var photo: Photo? = null

    companion object {
        val CREATOR: Parcelable.Creator<Property>? = object : Parcelable.Creator<Property> {

                    override fun createFromParcel(p0: Parcel?): Property {
                        return createFromParcel(p0)
                    }

                    override fun newArray(p0: Int): Array<Property?> {
                        return arrayOfNulls(0)
                    }
        }
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(title)
        parcel?.writeInt(writeBooleanToParcel(is_premium))
        parcel?.writeString(state)
        parcel?.writeInt(writeNullableIntToParcel(bedrooms))
        parcel?.writeInt(writeNullableIntToParcel(bathrooms))
        parcel?.writeInt(writeNullableIntToParcel(carspots))
        parcel?.writeString(description)
        parcel?.writeFloat(writeNullableFloatToParcel(price))
        parcel?.writeParcelable(owner, 0)
        parcel?.writeParcelable(location, 0)
        parcel?.writeParcelable(photo, 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    private fun writeBooleanToParcel(bool: Boolean?): Int = when(bool) {
        true -> 1
        false -> 0
        else -> -1
    }

    private fun writeNullableIntToParcel(i: Int?): Int = when(i) {
        null -> -1
        else -> i
    }

    private fun writeNullableFloatToParcel(f: Float?): Float = when(f) {
        null -> -1f
        else -> f
    }
}
