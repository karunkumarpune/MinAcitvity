package com.newyear.christmas.fragment.model_avatar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AvatarExample {

    @SerializedName("result")
    @Expose
    var result: List<AvatarResult>? = null

}
