package com.example.mydiary.shared.model

import kotlinx.serialization.Serializable

@Serializable
enum class Mood {
    HAPPY,
    SAD,
    ANGRY,
    CALM,
    EXCITED
}
