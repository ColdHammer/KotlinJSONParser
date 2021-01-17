package com.georgedrougas.json

interface VariableNode<T> : Node {
    var value: T
    override fun toEscapedString() :String{
        return "\"${value.toString().escape()}\""
    }
    operator fun invoke() : T {
        return value
    }
    operator fun invoke(value: T){
        this.value = value
    }
}