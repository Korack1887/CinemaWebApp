package util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Date;


class ConvertStringToDateSQLTest {

    @Test
    void convert() {
        Date exp = Date.valueOf("2010-01-01");
        Date act = ConvertStringToDateSQL.convert("2010-01-01");
        Assert.assertEquals(exp, act);
    }
}