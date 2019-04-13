package calculator

class DecodeFormula{

    fun decomposeFormula(formula: String): List<CalcPair>{
        val formulaList = ArrayList<CalcPair>()

        var number = StringBuilder()
        formula.forEach{

            if(it.isDigit()){
                number.append(it)
            }else{
                if(number.isBlank()){
                    formulaList.add(CalcPair(it.toString(), false))
                }else{
                    formulaList.add(CalcPair(number.toString(), true))
                    formulaList.add(CalcPair(it.toString(), false))
                    number = StringBuilder()
                }
            }
        }
        if(number.isNotBlank()){
            formulaList.add(CalcPair(number.toString(), true))
        }
        return formulaList
    }

    fun decodeFormula(list: List<CalcPair>): List<Element>{
        var bracketsCounter = 1
        val formulaList = ArrayList<Element>()
        list.forEach{
            when {
                it.isNumber -> formulaList.add(Element.Number(Integer.parseInt(it.element)))
                it.element == "(" -> bracketsCounter += 4
                it.element == ")" -> bracketsCounter -= 4
                else -> {
                    val operator = it.element
                    val weight = weighting(operator)
                    formulaList.add(Element.Operator(operator, weight * bracketsCounter))
                }
            }
        }
        return formulaList
    }

    //括弧の中の演算子の場合呼び出し先からさらに重み付けする
    //例としては括弧ネストカウンターなどを作り括弧のネストカウンターの大きさに応じて重みを大きくする
    //もしネストカウンターの数字をそのままOperatorに積をする場合はカウンターは必ず3以上の数にする必要がある
    private fun weighting(operator: String): Int{
        return when(operator){
            "+" ->  1
            "-" -> 1
            "*" -> 2
            "/" -> 2
            else -> 0
        }
    }



}