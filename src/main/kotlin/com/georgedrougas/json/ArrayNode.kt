package com.georgedrougas.json

class ArrayNode : ArrayList<Node>(),
        Node {
    var text = ""
    var isText = false
    var escaped = false
    override fun push(character: Char, sIterator: SIterator): Boolean {
        if(escaped) {
            escaped = false
            when (character) {
                in arrayOf('\\', '\"') -> {
                    text += character
                    return true
                }
            }
        }
        if (character == '\"') {
            isText = !isText
            return true
        }

        if (isText) {
            text += character
            return true
        }

        if(character == '\\')
        {
            escaped = true
            return true
        }

        if (character == ' ' || character == '\n' || character == '\t') return true
        when (character) {
            ']' -> {
                if (text != "") {
                    add(Node.getNode(text))
                    text = ""
                }
                return false
            }
            ',' -> if (text != "") {
                add(Node.getNode(text))
                text = ""
            }
            '{' -> {
                val objectNode = ObjectNode()
                objectNode.setNode(sIterator)
                add(objectNode)
            }
            '[' -> {
                val arrayNode = ArrayNode()
                arrayNode.setNode(sIterator)
                add(arrayNode)
            }
            else -> text += character
        }
        return true
    }

    override fun toEscapedString(): String {
        var text = "["
        var first = true
        for (node in this) {
            text += if (first) {
                first = false
                "\n${tabEscaped(node.toEscapedString())}"
            } else {
                ",\n${tabEscaped(node.toEscapedString())}"
            }
        }
        text += "\n]"
        return text
    }

    fun tabEscaped(string: String): String{
        var value = ""
        for(line in string.lines()){
            value += "\t$line\n"
        }
        value = value.substring(0, value.length-1)
        return value
    }
}