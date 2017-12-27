package com.newyear.christmas.fragment.model_avatar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AvatarResult {

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
}