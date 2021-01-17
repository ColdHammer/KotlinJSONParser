package com.georgedrougas.json

class IntNode(override var value: Int) : VariableNode<Int> {
    override fun toString(): String {
        return value.toString().unescape()
    }
}
class FloatNode(override var value: Float) : VariableNode<Float> {
    override fun toString(): String {
        return value.toString().unescape()
    }
}
class StringNode(override var value: String) : VariableNode<String> {
    override fun toString(): String {
        return value.unescape()
    }
}
class BooleanNode(override var value: Boolean) : VariableNode<Boolean> {
    override fun toString(): String {
        return value.toString().unescape()
    }
}