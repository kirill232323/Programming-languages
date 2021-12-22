enum class OperatorType() { //перечисление знаков
    EQUAL, NO_EQUAL, LESS, MORE, LESS_EQUAL, MORE_EQUAL
}
class Operator(val arg: Int, val type: OperatorType) { //инициализация знаков

    fun initialization(value: Int) = when (type) {
        OperatorType.EQUAL -> arg == value
        OperatorType.NO_EQUAL -> arg != value
        OperatorType.LESS -> arg > value
        OperatorType.MORE -> arg < value
        OperatorType.LESS_EQUAL -> arg >= value
        OperatorType.MORE_EQUAL -> arg <= value
    }
}

class IndexedKeyLocation(private val map: HashMap<String, Int>) { //получение индекса элемента iloc

    operator fun get(index: Int): String{
        return map.keys.elementAt(index)
    }

}

fun rewriteOperator(rewriteOp: String): Operator {
    return when {
        rewriteOp.startsWith("<=") -> Operator(rewriteOp.drop(2).toInt(), OperatorType.LESS_EQUAL)
        rewriteOp.startsWith(">=") -> Operator(rewriteOp.drop(2).toInt(), OperatorType.MORE_EQUAL)
        rewriteOp.startsWith("<>") -> Operator(rewriteOp.drop(2).toInt(), OperatorType.NO_EQUAL)
        rewriteOp.startsWith("=") -> Operator(rewriteOp.drop(1).toInt(), OperatorType.EQUAL)
        rewriteOp.startsWith("<") -> Operator(rewriteOp.drop(1).toInt(), OperatorType.LESS)
        rewriteOp.startsWith(">") -> Operator(rewriteOp.drop(1).toInt(), OperatorType.MORE)
        else -> error("Оператор не известен")
    }

}

class NewHashMap(): HashMap<String, Int>() {

    public val ikloc = IndexedKeyLocation(this)
    public val iloc: ArrayList<Int> = ArrayList()
    override fun put(key: String, value: Int): Int? {
        val result = super.put(key, value)
        iloc.clear()
        val keys_ = keys.toSortedSet(Comparator<String> { s1, s2 -> s1.compareTo(s2) })
        for (key in keys_) {
            this[key]?.let { iloc.add(it) }
        }
        return result
    }

    public val ploc: Ploc = Ploc()

    inner class Ploc() {
        operator fun get(operators: String): Map<String, Int> {
            val Operators = "[<>=]{1,2}[0-9]+".toRegex().findAll(operators).map { rewriteOperator(it.value) }.toList()
            val result: MutableMap<String, Int> = mutableMapOf()
            for (key in keys) {
                var index = key
                if (!index.startsWith('(') || !(index.endsWith(')'))) {
                    if (Operators.size != 1) continue
                    val isCorrect = index.toIntOrNull()?.let { Operators.first().initialization(it) } ?: continue
                    if (isCorrect) {
                        this@NewHashMap[key]?.let { result.put(key, it) }
                    }
                    continue
                }
                index = index.drop(1).dropLast(1)
                val Answer = index.split(",").map { it.trim().toIntOrNull() }
                if (Answer.size != Operators.size)
                    continue
                if (Answer.zip(Operators).all { (value, operator) -> operator.initialization(value!!) }) {
                    this@NewHashMap[key]?.let { result.put(key, it) }
                }
            }
            return result
        }
    }
}

