package alikazi.com.sentia.models

import android.os.Parcel
import android.os.Parcelable

data class Image(var url: String) : Parcelable {

    var small: SpecificSizeUrl? = null
    var medium: SpecificSizeUrl? = null
    var large: SpecificSizeUrl? = null
    var profile: SpecificSizeUrl? = null

    companion object {
        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {

            override fun createFromParcel(p0: Parcel?): Image {
                return createFromParcel(p0)
            }

            override fun newArray(p0: Int): Array<Image?> {
                return arrayOfNulls(0)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeParcelable(small, 0)
        parcel?.writeParcelable(medium, 0)
        parcel?.writeParcelable(large, 0)
        parcel?.writeParcelable(profile, 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    data class SpecificSizeUrl(var url: String) : Parcelable {

        companion object {
            val CREATOR: Parcelable.Creator<SpecificSizeUrl> = object : Parcelable.Creator<SpecificSizeUrl> {
                override fun createFromParcel(p0: Parcel?): SpecificSizeUrl {
                    return createFromParcel(p0)
                }

                override fun newArray(p0: Int): Array<SpecificSizeUrl?> {
                    return arrayOfNulls(0)
                }
            }
        }

        override fun writeToParcel(parcel: Parcel?, p1: Int) {
            parcel?.writeString(url)
        }

        override fun describeContents(): Int {
            return 0
        }
    }
}
