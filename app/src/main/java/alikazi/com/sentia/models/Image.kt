package alikazi.com.sentia.models

object Image {
    var url: String? = null
    var small: Small? = null
    var medium: Medium? = null
    var large: Large? = null
    var profile: Profile? = null

    object Small {
        var url: String? = null
    }

    object Medium {
        var url: String? = null
    }

    object Large {
        var url: String? = null
    }

    object Profile {
        var url: String? = null
    }
}
