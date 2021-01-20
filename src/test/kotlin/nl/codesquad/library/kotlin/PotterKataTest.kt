package nl.codesquad.library.kotlin

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

private val first = "HP and the goblet of fire"
private val second = "HP and the other book idgaf"
private val third = "HP and the cancellation of rowling"
private val fourth = "HP and the gayness of ron"
private val fifth = "HP and the sperm whale"

class PotterKataTest : WordSpec({
    "simple checkout" should {
        "return 0 given an empty shopping cart" {
            checkout(emptyList()) shouldBe 0.0
        }

        "return 8 as the price given a single book" {
            checkout(listOf(second)) shouldBe 8.0
        }

        "return 16 as the price for 2 identical books" {
            checkout(listOf(second, second)) shouldBe (8.0 + 8.0)
        }
    }
    "discount checkout" should {
        "return a 5% discounted price for 5 different books" {
            checkout(listOf(first, second)) shouldBe ((2 * 8.0) * 0.95)
        }
        "return a 10% discounted price for 3 different books" {
            checkout(listOf(first, second, third)) shouldBe ((8.0 + 8.0 + 8.0) * 0.90)
        }

        "return a 20% discounted price for 4 different books" {
            checkout(listOf(first, second, third, fourth)) shouldBe ((4 * 8.0) * 0.80)
        }

        "return a 25% discounted price for 5 different books" {
            checkout(listOf(first, second, third, fourth, fifth)) shouldBe ((5 * 8.0) * 0.75)
        }
    }
    "composite checkout" should {
        "return a 5% discounted price for 2 different and full price for remaining book" {
            checkout(listOf(first, second, first)) shouldBe ((8.0 + 8.0) * 0.95 + 8.0)
        }
        "return 5% discounted price for 2 sets of 2 books" {
            checkout(listOf(first, first, second, second)) shouldBe 2 * (8 * 2 * 0.95)
        }

        "return complex discounted price for set of 4 and set of 2" {
            checkout(listOf(first, first, second, third, third, fourth)) shouldBe (8 * 4 * 0.8) + (8 * 2 * 0.95)
        }

        "return complex discounted price for set of 5 and set of 2" {
            checkout(listOf(first, first, second, third, third, fourth, fifth)) shouldBe (8 * 5 * 0.75) + (8 * 2 * 0.95)
        }

        "return complex discounted price for set of 2 sets of 4" {
            checkout(listOf(first, second, third, fourth, second, third, fourth, fifth)) shouldBe (8 * 4 * 0.8) + (8 * 4 * 0.8)
        }

        "return complex discounted price for set of 5, 5, 4, 5, 4" {
            val books = first.times(5) + second.times(5) + third.times(4) + fourth.times(5) + fifth.times(4)
            checkout(books) shouldBe (2 * (8 * 4 * 0.8) + 3 * (8 * 5 * 0.75))
        }
    }

})

private fun String.times(number: Int): List<String> = (0 until number).map{this}
