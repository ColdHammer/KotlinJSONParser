# KotlinJSONParser
JSON Parser in Kotlin
* Java SDK: 14 
* Kotlin version: 1.4.21

# ICharStream

The json parser needs a stream of characters. I used this interface to allow users to create custom Character Streams.
The character Stream is very simple, you just need to define whether the stream hasNext() and if it does, it should return it with
the next()

I have made some default Character Streams. The FileStream and the StringStream.

# Parsing JSon Data

We instantiate a RootNode with our prefered ICharStream then we get the ObjectNode
that ObjectNode contains all the data we provided with the stream.
```
    val node = RootNode(FileStream("example.json"))
    val objectNode = node.objectNode
```

# Creating a new JSon Format
We can add nodes to an ObjectNode that we will have as a root node and then call the toEscapedString() function that it inherits.
This will return a string in a json format.
```
fun main() {
    val objectNode = ObjectNode()
    objectNode.put("name", StringNode("value"))
    val rootNode = ObjectNode()
    rootNode.put("subObject", objectNode)
    print(rootNode.toEscapedString())
}
```
### Will print
```
{
	"subObject" : {
		"name" : "value"
	}
}
```

We can add nodes a 


# Nodes
the nodes have a function called toEscapedString. This returns an escaped string with all it's subnodes.


## ObjectNode
The object node is a a Hashmap based node. It takes a string as a key and returns a Node
```
fun main() {
    val objectNode = ObjectNode()
    objectNode.put("name", ObjectNode())
    val subObjectNode = objectNode["name"]
}

```
This gives us the Object node that we added earlier

## ArrayNode
```
fun main() {
    val arrayNode = ArrayNode()
    arrayNode.addAll(arrayOf(StringNode("Helllo"), IntNode(5), FloatNode(5.63f), BooleanNode(false)))
    val variableNode = arrayNode[2]
}
```
This lines adds some nodes to the arrayNode and then returns the 3rd node

## VariableNodes
includes StringNode, IntNode, FloatNode, BooleanNode
These return a value
