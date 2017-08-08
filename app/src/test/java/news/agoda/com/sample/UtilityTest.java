package news.agoda.com.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import news.agoda.com.sample.module.Utility;

public class UtilityTest {

    String testString = "TestSteamReading";
    InputStream inputStream;

    @Before
    public void setUp() throws Exception {
        inputStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void readStreamTest() throws Exception {
        Assert.assertEquals(testString, Utility.readStream(inputStream));
    }
}
