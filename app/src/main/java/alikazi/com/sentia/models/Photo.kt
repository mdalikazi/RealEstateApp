package alikazi.com.sentia.models

import android.os.Parcel
import android.os.Parcelable

data class Photo(var id: Int) : Parcelable {

    var image: Image? = null
    var default: Boolean? = false

    companion object {
        val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
            override fun createFromParcel(p0: Parcel?): Photo {
                return createFromParcel(p0)
            }

            override fun newArray(p0: Int): Array<Photo?> {
                return arrayOfNulls(0)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeParcelable(image, 0)
        parcel?.writeInt(writeBooleanToParcel(default))
    }

    override fun describeContents(): Int {
        return 0
    }

    private fun writeBooleanToParcel(bool: Boolean?): Int = when(bool) {
        true -> 1
        false -> 0
        else -> -1
    }
}
