package com.example.newsly.helper

object ColorPicker {

//    val colors = arrayOf("#66cdaa", "#7fffd4", "#006400", "#556b2f", "#8fbc8f", "#2e8b57", "#3cb371", "#98fb98", "#00ff7f", "#7cfc00")

    private val colors = arrayOf("#FFFFFF", "#FFFFFF", "#FFFFFF")

    var colorIndex = 1

    fun getColor() : String {

        return colors[colorIndex++ % colors.size]
    }
}