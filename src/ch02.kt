import sun.security.provider.Sun
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num
        return n.value
    }

    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }

    throw IllegalArgumentException("Unknown expression")
}

fun eval2(e: Expr): Int =
        if (e is Num) {
            e.value
        } else if (e is Sum) {
            eval2(e.left) + eval2(e.right)
        } else {
            throw IllegalArgumentException("Unknown expression")
        }

fun eval3(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval2(e.left) + eval3(e.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }

fun evalWithLogging(e: Expr): Int =
        when (e) {
            is Num -> {
                p("num: ${e.value}")
                e.value
            }
            is Sum -> {
                val left = evalWithLogging(e.left)
                val right = evalWithLogging(e.right)
                println("sum: ${left}+${right}")
                left + right
            }
            else -> throw IllegalArgumentException("Unknow expression")
        }

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun itMap() {
    val binaryReps = TreeMap<Char,String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}

fun itList() {
    val list = arrayListOf("10","11","1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

fun isLetter(c:Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c:Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z',in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}

fun main(args: Array<String>) {
    p(recognize('8'))
//    p(isLetter('s'))
//    p(isNotDigit('5'))
//    itList()
//    itMap()

//    for (i in 100 downTo 1 step 2) {
//        print(fizzBuzz(i))
//    }

//    p(evalWithLogging(Sum(Sum(Num(1),Num(2)),Num(4))))
//    p(eval2(Sum(Num(5), Num(2))))
//    p(eval2(Num(1)))
//    p(eval(Num(5)))
//    p(eval(Sum(Sum(Num(1),Num(2)),Num(4))))
}