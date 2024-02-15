package com.example.test


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int
) {
    data class Data(
        @SerializedName("name")
        val name: String,
        @SerializedName("slug")
        val slug: String,
        var isExpanded : Boolean,
        @SerializedName("taxonomies")
        val taxonomies: List<Taxonomy>
    ) {
        data class Taxonomy(
            @SerializedName("city")
            val city: String,
            @SerializedName("Guid")
            val guid: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("locations")
            val locations: List<Location>,
            @SerializedName("name")
            val name: String,
            @SerializedName("slug")
            val slug: String,
            var isChecked : Boolean
        ) {
            data class Location(
                @SerializedName("Guid")
                val guid: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("slug")
                val slug: String
            )
        }
    }
}