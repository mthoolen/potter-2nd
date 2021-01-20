package nl.codesquad.library.kotlin

const val BOOK_PRICE = 8.0

fun checkout(books: List<String>) =
    (0..books.numberOfEach().max())
        .map { formGroups(books, it) }
        .run(::optimizeValue)
        .map(::toPrice)
        .sum()

tailrec fun optimizeValue(groups: List<Int>): List<Int> {
    val mutable = groups.toMutableList()
    return if(groups.contains(5) && groups.contains(3)){
        mutable[groups.indexOf(5)] -= 1
        mutable[groups.indexOf(3)] += 1
        optimizeValue(mutable)
    } else mutable
}

private fun formGroups(books: List<String>, index: Int) = books.numberOfEach()
    .map { it - index }
    .filter { it > 0 }
    .size

private fun List<String>.numberOfEach() =
    groupBy(::id).values.map(List<*>::size)

private fun toPrice(it: Int) = it * BOOK_PRICE * discount(it)

private fun discount(numberOfBooks: Int): Double =
    when (numberOfBooks) {
        5 -> 0.75
        4 -> 0.80
        3 -> 0.90
        2 -> 0.95
        else -> 1.0
    }

private fun List<Int>.max(): Int = maxOrNull() ?: 0

fun <T> id(x: T) = x
