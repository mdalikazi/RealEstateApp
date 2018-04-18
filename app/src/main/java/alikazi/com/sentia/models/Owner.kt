package alikazi.com.sentia.models

import android.os.Parcel
import android.os.Parcelable

data class Owner(var id: Int): Parcelable {

    var first_name: String? = null
    var last_name: String? = null
    var email: String? = null
    var avatar: Image? = null

    companion object {
        val CREATOR: Parcelable.Creator<Owner>? = object : Parcelable.Creator<Owner> {

            override fun createFromParcel(p0: Parcel?): Owner {
                return createFromParcel(p0)
            }

            override fun newArray(p0: Int): Array<Owner?> {
                return arrayOfNulls(0)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(first_name)
        parcel?.writeString(last_name)
        parcel?.writeString(email)
        parcel?.writeParcelable(avatar, 0)
    }

    override fun describeContents(): Int {
        return 0
    }
}
