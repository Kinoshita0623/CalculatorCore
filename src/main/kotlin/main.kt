import calculator.Calculator

fun main(args: Array<String>){
    val formula = args.firstOrNull()
    if(formula == null){
        println("式を入力してください")
        return
    }else{
        val calc = Calculator()

        println("$formula = ${calc.calculation(formula)}")
    }
}