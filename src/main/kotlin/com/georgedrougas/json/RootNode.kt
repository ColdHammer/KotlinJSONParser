package com.georgedrougas.json

class RootNode(charStream: ICharStream) : Node {
    var objectNode: ObjectNode = ObjectNode()

    init {
        val sIterator = SIterator(charStream)
        this.setNode(sIterator)
    }
    override fun toEscapedString(): String {
        return objectNode.toEscapedString()
    }
    override fun push(character: Char, sIterator: SIterator): Boolean {
        return when (character) {
            '{' -> {
                objectNode = ObjectNode()
                objectNode.setNode(sIterator)
                true
            }
            '}' -> false
            else -> true
        }
    }

    fun getRootNode(): ObjectNode {
        return objectNode
    }
}