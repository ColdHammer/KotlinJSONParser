package com.georgedrougas.json

import java.util.function.Consumer

interface ICharStream : Iterator<Char> {
    override fun forEachRemaining(p0: Consumer<in Char>) {
        while(hasNext())
        {
            p0.accept(next())
        }
    }

    override fun hasNext(): Boolean
    override fun next(): Char
}