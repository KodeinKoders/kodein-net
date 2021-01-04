package net.kodein.pages.oss

import net.kodein.PageStrings
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings


interface OssStrings : PageStrings {

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

    val onionTitle: TextHandler
    val onionLayerAndroid: TextHandler
    val onionLayerDesktop: TextHandler
    val onionLayerIos: TextHandler
    val onionLayerJs: TextHandler
    val onionLayerJvm: TextHandler
    val onionLayerKodein: TextHandler
    val onionLayerKotlin: TextHandler
    val onionLayerNative: TextHandler
    val onionLayerWeb: TextHandler

    val wantMore: CoverStrings
}
