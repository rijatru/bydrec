package com.test.bydrec.view

import android.os.Build
import com.test.bydrec.TestApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class, sdk = [Build.VERSION_CODES.O])
class MainActivityTest {

    @Test
    fun test_onCreate_receivesInjectedObjects() {

    }
}
