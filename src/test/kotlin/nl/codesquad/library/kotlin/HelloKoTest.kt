package nl.codesquad.library.kotlin

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HelloKoTest: StringSpec ({
    "canary" {
        true shouldBe false
    }
})