

fun drawHpBar(current : Int, max : Int ): String {
    val block = "\u2588"
    val shade = "\u2591"
    val filled = (current.toDouble() / max * 10).toInt()
    val empty = 10 - filled
    val bar = "[" + block.repeat(filled) + shade.repeat(empty) + "]$current/$max"
    val percentage = (current.toDouble() / max * 100).toInt()
    val indicator = when {
        percentage > 65 -> "🟢"
        percentage > 35 -> "🟡"
        else -> "🔴"
    }
    return "$indicator $bar"
}
// exp.....
//    val color = when {
//        percentage > 65 -> "\u001B[32m" // green
//        percentage > 35 -> "\u001B[33m" // yellow
//        else -> "\u001B[31m" // red
//    }
//    val reset = "\u001B[0m"
//    return color+bar+reset
fun drawXpBar(current : Int, needed :Int,  level : Int ): String {

    val filled = if(needed == 0)10
    else(current.toDouble() / needed * 10).toInt()
    val empty = 10 - filled.coerceAtMost(10)
    val block = "\u2588"
    val shade = "\u2591"
    val bar = "[" + block.repeat(filled) + shade.repeat(empty) + "]$current/$needed"
    return "✨LVL $level $bar"
}
fun drawSeparator(title : String = "")
{
    val line = "\u2501".repeat(50)
    if (title.isEmpty())
    {
        println(line)
    }
    else
    {
        println("\u2501\u2501\u2501$title\u2501\u2501\u2501")
    }
}
