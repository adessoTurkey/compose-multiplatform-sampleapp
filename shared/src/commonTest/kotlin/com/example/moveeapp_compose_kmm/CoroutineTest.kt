package com.example.moveeapp_compose_kmm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
abstract class CoroutineTest {

    abstract fun setUp()

    @BeforeTest
    fun start() {
        Dispatchers.setMain(UnconfinedTestDispatcher(TestCoroutineScheduler()))

        setUp()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
