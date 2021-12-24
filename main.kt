import java.lang.StringBuilder

fun isNumber(s: String): Boolean {
    if (s.isEmpty()) return false
    for (symbol in s){
        if (!symbol.isDigit()){
            return false
        }
    }
    return true
}
fun main(args: Array<String>) {
    val ops = arrayOf("+","-","*","/")
    print("Please input expression separated by space: ")
    val answer = readLine()
    val parts = answer?.split(' ')
    val stack = mutableListOf<Double>()
    val result = mutableListOf<String>()
    val stack_operand = mutableListOf<String>()
    var count_op = 0
    var count_numb = 0
    if (!parts.isNullOrEmpty()){
        for (part in parts){
            if (part in ops){ //если операция
                stack_operand.add(part)
                count_op++
            }else { //если число
                if (isNumber(part)) {
                    count_numb++
                    stack.add(part.toDouble())
                } else {
                    println("Wrong expression!")
                    break
                }
            }
        }
    }
    var i = -1
    var size_i = (stack.size + stack_operand.size) / 2
    if (count_numb != count_op + 1){
        println("Wrong expression!")
    }
    else{
        if (!stack.isNullOrEmpty() && !stack_operand.isNullOrEmpty()) {
            for (n in 1..size_i) {
                i++
                result.add(stack[i].toString())
                result.add(stack_operand[i])
            }
            result.add(stack[i+1].toString())
        }
        var result_size = result.size
        for (n in 0..result_size-1)
            print(result[n]+" ")
    }
}