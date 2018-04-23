package alikazi.com.sentia.models

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PropertyTest {

    companion object {
        const val TEST_ID: Int = 1
        const val TEST_TITLE: String = "Test Title 1"
        const val TEST_IS_PREMIUM: Boolean = true
        const val TEST_STATE: String = "private"
        const val TEST_BEDROOMS = 4
        const val TEST_BATHROOMS = 2
        const val TEST_CARSPOTS = 1
        const val TEST_DESCRIPTION: String = "This is a description"
        const val TEST_PRICE: Float = 10000000.00f
        const val TEST_EMPTY_STRING = ""
        const val TEST_LONGITUDE = 10.00
        const val TEST_LATITUDE = 20.00
    }

    private val mSpecificSizeUrl = SpecificSizeUrl(TEST_EMPTY_STRING)
    private val mImage = Image(TEST_EMPTY_STRING, mSpecificSizeUrl, mSpecificSizeUrl, mSpecificSizeUrl, mSpecificSizeUrl)
    private val mOwner = Owner(TEST_ID, TEST_EMPTY_STRING, TEST_EMPTY_STRING, TEST_EMPTY_STRING, mImage)
    private val mLocation = Location(TEST_ID, TEST_EMPTY_STRING, TEST_EMPTY_STRING, TEST_EMPTY_STRING, TEST_EMPTY_STRING, TEST_EMPTY_STRING,
            TEST_EMPTY_STRING, TEST_LATITUDE, TEST_LONGITUDE, TEST_EMPTY_STRING)
    private val mPhoto = Photo(TEST_ID, mImage, true)

    private val mProperty: Property = Property(
            TEST_ID,
            TEST_TITLE,
            TEST_IS_PREMIUM,
            TEST_STATE,
            TEST_BEDROOMS,
            TEST_BATHROOMS,
            TEST_CARSPOTS,
            TEST_DESCRIPTION,
            TEST_PRICE,
            mOwner,
            mLocation,
            mPhoto)

    @Test
    fun testSpecificUrlParcelableReadWrite() {
        assertTrue(mSpecificSizeUrl?.url.equals(TEST_EMPTY_STRING))
    }

    @Test
    fun testImageParcelableReadWrite() {
        assertTrue(mImage?.url.equals(TEST_EMPTY_STRING))
        assertTrue(mImage?.large?.url.equals(TEST_EMPTY_STRING))
    }

    @Test
    fun testOwnerParcelableReadWrite() {
        assertTrue(mOwner?.id == TEST_ID)
        assertTrue(mOwner?.first_name.equals(TEST_EMPTY_STRING))
        assertTrue(mOwner?.last_name.equals(TEST_EMPTY_STRING))
        assertTrue(mOwner?.email.equals(TEST_EMPTY_STRING))
        assertTrue(mOwner?.avatar?.url.equals(TEST_EMPTY_STRING))
    }

    @Test
    fun testLocationParcelableReadWrite() {
        assertTrue(mLocation?.id == TEST_ID)
        assertTrue(mLocation?.address_1.equals(TEST_EMPTY_STRING))
        assertTrue(mLocation?.address_2.equals(TEST_EMPTY_STRING))
        assertTrue(mLocation?.full_address.equals(TEST_EMPTY_STRING))
        assertTrue(mLocation?.state.equals(TEST_EMPTY_STRING))
        assertTrue(mLocation?.postcode.equals(TEST_EMPTY_STRING))
        assertTrue(mLocation?.country.equals(TEST_EMPTY_STRING))
        assertTrue(mLocation?.latitude == TEST_LATITUDE)
        assertTrue(mLocation?.longitude == TEST_LONGITUDE)
    }

    fun testPhotoParcelableReadWrite() {
        assertTrue(mPhoto?.id == TEST_ID)
        assertTrue(mPhoto?.default == true)
        assertTrue(mPhoto?.image?.url.equals(TEST_EMPTY_STRING))
    }

    @Test
    fun testPropertyParcelableReadWrite() {
        assertTrue(mProperty.id == TEST_ID)
        assertTrue(mProperty.is_premium == TEST_IS_PREMIUM)
        assertTrue(mProperty.state.equals(TEST_STATE, true))
        assertTrue(mProperty.bedrooms == TEST_BEDROOMS)
        assertTrue(mProperty.bathrooms == TEST_BATHROOMS)
        assertTrue(mProperty.carspots == TEST_CARSPOTS)
        assertTrue(mProperty.description.equals(TEST_DESCRIPTION))
        assertTrue(mProperty.price == TEST_PRICE)
        assertTrue(mProperty.owner?.id == TEST_ID)
        assertTrue(mProperty.location?.id == TEST_ID)
        assertTrue(mProperty.location?.latitude == TEST_LATITUDE)
        assertTrue(mProperty.location?.longitude == TEST_LONGITUDE)
        assertTrue(mProperty.photo?.id == TEST_ID)
        assertTrue(mProperty.photo?.default == true)
    }
}
