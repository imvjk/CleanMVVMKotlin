package com.example

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

/**
 * Base class for Unit tests. Inherit from it to create test cases which DO NOT contain android
 * framework dependencies or components.
 *
 * @see AndroidTest
 */
@RunWith(MockitoJUnitRunner::class)
@Config(manifest=Config.NONE)
abstract class UnitTest {

    @Rule @JvmField val injectMocks = InjectMocksRule.create(this@UnitTest)
}
