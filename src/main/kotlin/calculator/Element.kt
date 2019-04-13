package calculator

sealed class Element{
    data class Number(val number: Int): Element()
    data class Operator(val type: String, val weight: Int): Element(), Comparable<Operator>{
        override fun compareTo(other: Operator): Int {
            return this.weight - other.weight
        }
    }
}