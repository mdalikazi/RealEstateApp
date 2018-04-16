package alikazi.com.sentia.models

data class Location(var id: Int) {

    var address_1: String? = null
    var address_2: String? = null
    var suburb: String? = null
    var state: String? = null
    var postcode: String? = null
    var country: String? = null
    var latitude: Double? = 0.00
    var longitude: Double? = 0.00
    var full_address: String? = null
}
