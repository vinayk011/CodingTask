package com.android.codingtask.model

data class Repository(
    val name: String,
    val owner:Owner,
    val description:String,
    val stargazers_count : Int
    )

data class Owner(
    val login:String,
    val avatar_url: String
)