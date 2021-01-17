package com.georgedrougas.json

fun String.unescape() : String{
    var temp = this.replace("\\n", "\n")
    temp = temp.replace("\\t", "\t")
    temp = temp.replace("\\b", "\b")
    temp = temp.replace("\\\"", "\"")
    return temp
}

fun String.escape() : String{
    var temp = this.replace("\n", "\\n")
    temp = temp.replace("\t", "\\t")
    temp = temp.replace("\b", "\\b")
    temp = temp.replace("\"", "\\\"")
    return temp
}