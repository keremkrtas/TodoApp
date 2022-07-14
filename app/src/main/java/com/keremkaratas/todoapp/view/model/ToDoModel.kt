package com.keremkaratas.todoapp.view.model

data class ToDoModel(
    var id: String="",
    var publisherId: String="",
    var title: String="",
    var content: String="",
    var imgUrl: String="",
    var LikesCount: Int = 0,
    var likes: ArrayList<String> = ArrayList()
)

