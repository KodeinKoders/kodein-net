package net.kodein.pages.oss

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings


interface OssStrings {

    val cover: CoverStrings

    val layerKodein: TextHandler
    val layerUI: TextHandler
    val layerKF_DI: String
    val layerKF_DB: String
    val layerKF_Log: String
    val layerKF_MVI: String
    val layerKX_Coroutines: String
    val layerKX_Atomic: String
    val layerKX_Serialization: String
    val layerKX_Platform: String
    val layerLow: TextHandler

    val wantMore: CoverStrings
}
