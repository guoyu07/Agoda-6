package news.agoda.com.sample.module.newslist.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ServiceRequestTest {

    private ServiceRequest serviceRequest;
    private static final String API_URL = "https://api.myjson.com/bins/nl6jh";

    @Before
    public void setUp() throws Exception {
        serviceRequest = new ServiceRequest();
    }

    @Test
    public void webUrlTest() throws Exception {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void serviceResultTest() throws Exception {
        List<NewsEntity> list = serviceRequest.makeWebRequest(API_URL);
        assertNotNull(list);
    }

    @Test
    public void serviceFailedResultTest() throws Exception {
        List<NewsEntity> list = serviceRequest.makeWebRequest("nourl");
        assertNull(list);
    }
}
