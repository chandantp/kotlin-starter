package com.starter

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import io.mockk.every
import io.mockk.mockkStatic
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object AppTest: Spek({

    describe("sample tests") {

        context("adding") {
            it("2 + 3 equals 5") {
                assertThat(2 + 3, equalTo(5))
            }
        }

        context("mocking foo") {

            mockkStatic("com.starter.AppKt")
            every { foo() } returns 123L

            it("bar returns expected value") {
                assertThat(bar(), equalTo(246L))
            }
        }
    }
})