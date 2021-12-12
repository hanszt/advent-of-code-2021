package model

class Node<T>(val value: T) {

    var parent: Node<T>? = null
    val neighbors = LinkedHashSet<Node<T>>()

    fun addNeighbor(neighbor: Node<T>) = neighbors.add(neighbor)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node<*>

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

    override fun toString(): String = "Node(value=$value, neighbors=${neighbors.map(Node<T>::value)})"
}
