package com.georgedrougas.json

import java.util.*

interface Node {

    fun push(character: Char, sIterator: SIterator): Boolean{return false}

    fun toEscapedString() :String

    fun setNode(sIterator: SIterator){
        while (sIterator.hasNext()) {
            val next = sIterator.next()
            if (!this.push(next, sIterator)) {
                break
            }
        }
    }

    companion object{

        fun getNode(value: String) : Node
        {
            when(getType(value))
            {
                Type.Float -> {
                    return FloatNode(value.toFloat())
                }
                Type.String -> {
                    return StringNode(value)
                }
                Type.Integer -> {
                    return IntNode(value.toInt())
                }
                Type.False -> {
                    return BooleanNode(false)
                }
                Type.True -> {
                    return BooleanNode(true)
                }
            }
        }
        private enum class Type{
            True,
            False,
            Integer,
            Float,
            String
        }

        private fun getType(value: String) : Type
        {
            if (value.toLowerCase(Locale.getDefault()) == "true")
                return Type.True
            if(value.toLowerCase(Locale.getDefault()) == "false")
                return Type.False
            if(value.toIntOrNull() != null)
                return Type.Integer
            if(value.toFloatOrNull() != null)
                return Type.Float
            return Type.String
        }
    }
}