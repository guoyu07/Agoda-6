package news.agoda.com.sample.module.newslist.model;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MediaEntityTest {

    private static final String URL = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbLarge.jpg";
    private MediaEntity mediaEntity;

    @Before
    public void setUp() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", URL);

        mediaEntity = new MediaEntity(jsonObject);
    }

    @Test
    public void validateImageUrl() throws Exception {
        assertEquals(URL, mediaEntity.getUrl());
    }
}
