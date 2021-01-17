package com.georgedrougas.json

class StringStream(val text: String = "Null") : ICharStream {
    var count = 0
    override fun hasNext(): Boolean {
        return text.length > count
    }

    override fun next(): Char {
        return text[count++]
    }
}
