package alikazi.com.sentia.models

data class Image(var url: String) {

    var small: SpecificSizeUrl? = null
    var medium: SpecificSizeUrl? = null
    var large: SpecificSizeUrl? = null
    var profile: SpecificSizeUrl? = null

    data class SpecificSizeUrl(var url: String)
}
