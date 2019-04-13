package calculator

import java.lang.IllegalArgumentException
import java.util.*

class Calculator{
    fun calculation(formula: String): String{
        val decode = DecodeFormula()
        val list = decode.decodeFormula(decode.decomposeFormula(formula))
        var tmpList = execution(list as ArrayList)

        while(listNumberSize(tmpList) > 1){

            tmpList = execution(tmpList)
        }

        //println(tmpList)
        val resultList = tmpList.filter{
            it is Element.Number
        }
        //println(result)
        val result = resultList.first() as Element.Number
        return result.number.toString()

    }

    private fun execution(list: ArrayList<Element>): ArrayList<Element>{

        //operatorが配置されているIndexをここに入れる
        val operatorList = ArrayList<Pair<Int, Element.Operator>>()

        for(n in 0.until(list.size)){
            val element = list[n]
            if(element is Element.Operator){
                operatorList.add(n to element)
            }
        }

        operatorList.sortWith(Comparator{ a, b ->
            b.second.weight - a.second.weight
        })

        val opPair = operatorList.first()
        val number1 = (list[opPair.first - 1] as Element.Number).number
        val op = opPair.second.type
        val number2 = (list[opPair.first + 1] as Element.Number).number

        list[opPair.first - 1] = Element.Number(tmpCalc(number1, number2, op))
        list.removeAt(opPair.first)

        list.removeAt(opPair.first)

        return list

    }

    private fun tmpCalc(n1: Int, n2: Int, op: String): Int{
        return when (op) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> n1 / n2
            else -> throw IllegalArgumentException()
        }
    }

    private fun listNumberSize(list: List<Element>): Int{
        return list.count{
            it is Element.Number
        }
    }


}