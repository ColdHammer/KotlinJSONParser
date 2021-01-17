package com.georgedrougas.json

class SIterator(val charStream: ICharStream) {
    fun next() : Char{
        return charStream.next()
    }
    fun hasNext() : Boolean {
        return charStream.hasNext()
    }
}