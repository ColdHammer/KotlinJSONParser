package com.georgedrougas.json

import com.georgedrougas.json.Node


class ObjectNode : HashMap<String, Node>(), Node {

    private var isString = false

    private var isValue = false
    private var var_name = ""
    private var var_value = ""
    private var escaped = false

    override fun push(character: Char, sIterator: SIterator): Boolean {
        //Returns true while the variable has next
        if(escaped) {
            escaped = false
            when (character) {
                in arrayOf('\\', '\"') -> {
                    if (isValue) {
                        var_value += character
                    } else {
                        var_name += character
                    }
                    return true
                }
            }
        }
        if (character == '\"' && !escaped) {
            isString = !isString
            return true
        }else if(character == '\"'){
            if (isValue) {
                var_value += character
            } else {
                var_name += character
            }
            return true
        }
        if (isString) {
            if (isValue) {
                var_value += character
            } else {
                var_name += character
            }
            return true
        }
        if(character == '\\' && !escaped)
        {
            escaped = true
            return true
        }else if(character == '\\')
        {
            if (isValue) {
                var_value += character
            } else {
                var_name += character
            }
            return true
        }
        if (character == ' ' || character == '\t' || character == '\n') {
            return true
        }
        when (character) {
            '}' -> {
                if (var_name !== "" && var_value !== "") {
                    put(var_name, com.georgedrougas.json.Node.getNode(var_value))
                    var_name = ""
                    var_value = ""
                    isValue = false
                }
                return false
            }
            '{' -> {
                val node = ObjectNode()
                node.setNode(sIterator)
                put(var_name, node)
                var_name = ""
                isValue = false
            }
            '[' -> {
                val arrayNode = ArrayNode()
                arrayNode.setNode(sIterator)
                put(var_name, arrayNode)
                var_name = ""
                isValue = false
            }
            ',' -> if (var_name !== "" && var_value !== "") {
                put(var_name, com.georgedrougas.json.Node.getNode(var_value))
                var_name = ""
                var_value = ""
                isValue = false
            }
            ':' -> isValue = true
            else -> if (isValue) {
                        var_value += character
                    } else {
                        var_name += character
                    }
        }
        return true
    }

    override fun toEscapedString(): String {
        var text = "{"
        var first = true
        for (str in keys) {
            text += if (first) {
                first = false
                "\n\t\"$str\" : ${tabEscaped(this[str]!!.toEscapedString())}"
            } else {
                ",\n\t\"$str\" : ${tabEscaped(this[str]!!.toEscapedString())}"
            }
        }
        text += "\n}"
        return text
    }
    fun tabEscaped(string: String): String{
        var value = ""
        for(line in string.lines()){
            value += "$line\n\t"
        }
        value = value.substring(0, value.length-2)
        return value
    }
}