package util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class DateTimeUtilTest {

    @Test
    void getDaysForWeek() {
        Assert.assertNotNull(DateTimeUtil.getDaysForWeek());
    }

    @Test
    void getDaysForWeekReversed() {
        Assert.assertNotNull(DateTimeUtil.getDaysForWeekReversed());
    }
}