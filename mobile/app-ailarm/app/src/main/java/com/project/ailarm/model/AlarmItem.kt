package com.project.ailarm.model

data class AlarmItem(
    val timeText: String,
    val tags: List<String> = emptyList()
)