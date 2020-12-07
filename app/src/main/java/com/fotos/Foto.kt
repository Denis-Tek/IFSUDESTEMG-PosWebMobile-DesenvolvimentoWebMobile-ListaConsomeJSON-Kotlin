package com.fotos

data class Foto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String? = null,
    val thumbnailUrl: String? = null
)