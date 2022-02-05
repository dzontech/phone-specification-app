package com.pma.specphone.base

import com.pma.specphone.base.functional.ICoroutineContextProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider : ICoroutineContextProvider {
    override val main: CoroutineContext by lazy { TestCoroutineDispatcher() }
    override val io: CoroutineContext by lazy { TestCoroutineDispatcher() }
    override val default: CoroutineContext by lazy { TestCoroutineDispatcher() }
}