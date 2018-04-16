package alikazi.com.sentia.models

data class Property(var id: Int) {

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
}
