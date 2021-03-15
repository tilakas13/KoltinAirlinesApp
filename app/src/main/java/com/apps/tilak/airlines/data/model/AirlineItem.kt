package com.apps.tilak.airlines.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AirlineItem(

    @SerializedName("__clazz")
    var clazzName: String? = null,

    @SerializedName("code")
    var code: String? = null,

    @SerializedName("defaultName")
    var defaultName: String? = null,

    @SerializedName("logoURL")
    var logoUrl: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("phone")
    var phoneNumber: String? = null,

    @SerializedName("site")
    var siteUrl: String? = null,

    @SerializedName("usName")
    var airlinesName: String? = null

) : Parcelable
