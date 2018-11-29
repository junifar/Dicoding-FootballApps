package com.rubahapi.footballapps.util

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class UtilsKtTest {

    @Test
    fun toSimpleString() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse("2018-02-28")
        Assert.assertEquals("Wed, 28 Feb 2018", toSimpleString(date))
    }

    @Test
    fun toSimpleTimeString() {
        val timeFormat = SimpleDateFormat("HH:mm:ssXXX")
        timeFormat.timeZone = (TimeZone.getTimeZone("GMT"))
        val timeDate = timeFormat.parse("00:00:00+00:00")
        assertEquals("07:00", toSimpleTimeString(timeDate))
    }
}