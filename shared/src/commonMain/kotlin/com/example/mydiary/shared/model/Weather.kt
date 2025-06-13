package com.example.mydiary.shared.model

import kotlinx.serialization.Serializable

@Serializable
enum class Weather {
    SUNNY,
    CLOUDY,
    RAINY,
    SNOWY,
    WINDY
}
