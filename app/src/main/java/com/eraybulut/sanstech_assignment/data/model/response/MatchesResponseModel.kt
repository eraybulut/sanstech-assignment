package com.eraybulut.sanstech_assignment.data.model.response


import com.google.gson.annotations.SerializedName

data class MatchesResponseModel(
    @SerializedName("at")
    val at: At? = At(),
    @SerializedName("br")
    val br: Br? = Br(),
    @SerializedName("bri")
    val bri: Int? = 0,
    @SerializedName("d")
    val d: Long? = 0,
    @SerializedName("ht")
    val ht: Ht? = Ht(),
    @SerializedName("i")
    val i: Int? = 0,
    @SerializedName("img")
    val img: Img? = Img(),
    @SerializedName("pe")
    val pe: Pe? = Pe(),
    @SerializedName("sc")
    val sc: Sc? = Sc(),
    @SerializedName("sgi")
    val sgi: Int? = 0,
    @SerializedName("st")
    val st: String? = "",
    @SerializedName("str")
    val str: Boolean? = false,
    @SerializedName("to")
    val to: To? = To(),
    @SerializedName("v")
    val v: String? = ""
)

data class At(
    @SerializedName("i")
    val i: Int? = null,
    @SerializedName("n")
    val n: String? = null,
    @SerializedName("p")
    val p: Int? = null,
    @SerializedName("rc")
    val rc: Int? = null,
    @SerializedName("sn")
    val sn: String? = null
)

data class AtX(
    @SerializedName("c")
    val c: Int? = null,
    @SerializedName("ht")
    val ht: Int? = null,
    @SerializedName("r")
    val r: Int? = null
)

data class Br(
    @SerializedName("id")
    val id: Int? = null
)

data class Ht(
    @SerializedName("i")
    val i: Int? = null,
    @SerializedName("n")
    val n: String? = null,
    @SerializedName("p")
    val p: Int? = null,
    @SerializedName("rc")
    val rc: Int? = null,
    @SerializedName("sn")
    val sn: String? = null
)

data class HtX(
    @SerializedName("c")
    val c: Int? = null,
    @SerializedName("ht")
    val ht: Int? = null,
    @SerializedName("r")
    val r: Int? = null
)

data class Img(
    @SerializedName("id")
    val id: Int? = null
)

data class Pe(
    @SerializedName("fi")
    val fi: String? = null,
    @SerializedName("si")
    val si: String? = null
)

data class Sc(
    @SerializedName("abbr")
    val abbr: String? = "",
    @SerializedName("at")
    val at: AtX? = AtX(),
    @SerializedName("ht")
    val ht: HtX? = HtX(),
    @SerializedName("min")
    val min: Int? = 0,
    @SerializedName("st")
    val st: Int? = 0
)

data class To(
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("i")
    val i: Int? = null,
    @SerializedName("n")
    val n: String? = null,
    @SerializedName("p")
    val p: Int? = null,
    @SerializedName("sn")
    val sn: String? = null
)