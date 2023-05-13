package com.punyacile.newsapiorg.model


import com.google.gson.annotations.SerializedName

data class SourceArtikel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)