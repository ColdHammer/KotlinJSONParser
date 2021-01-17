package com.georgedrougas.json

import java.io.File
import java.io.FileInputStream
import kotlin.text.Charsets.UTF_8

class FileStream(fileName: String) : ICharStream {
    var file: File
    var inputStream: FileInputStream
    var count = 0
    var hasMore = true
    init {
        file = File(fileName)
        inputStream = FileInputStream(file)
    }

    var next = sequence {
        while(inputStream.available() >= 1)
        {
            val bytes = ByteArray(1){0}
            yield(String(bytes, UTF_8)[0])
        }
    }

    override fun hasNext(): Boolean {
        return inputStream.available() >= count + 1
    }

    override fun next(): Char {
        val bytes = ByteArray(1){0}
        inputStream.read(bytes)
        val str = String(bytes, UTF_8)[count]
        //print(str)
        return str
    }

    fun close()
    {
        inputStream.close()
    }
}