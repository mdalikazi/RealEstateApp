package alikazi.com.sentia.models

import android.os.Parcel
import android.os.Parcelable

data class Properties(var data: ArrayList<Property>) : Parcelable {

    companion object {
        val CREATOR: Parcelable.Creator<Properties> = object : Parcelable.Creator<Properties> {
            override fun createFromParcel(p0: Parcel?): Properties {
                return createFromParcel(p0)
            }

            override fun newArray(p0: Int): Array<Properties?> {
                return arrayOfNulls(0)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeList(data)
    }

    override fun describeContents(): Int {
        return 0
    }
}
