package news.agoda.com.sample.module.newslist.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewsEntityTest {

    public static final String TITLE = "Work Policies May Be Kinder, but Brutal Competition Isn’t";
    public static final String SUMMARY = "Top-tier employers may be changing their official policies in a nod to work-life balance, but brutal competition remains an inescapable component of workers’ daily lives.";
    public static final String STORY_URL = "http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html";
    public static final String IMAGE_URL = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg";
    private NewsEntity newsEntity;

    @Before
    public void setUp() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NewsEntity.CONST_TITLE, TITLE);
        jsonObject.put(NewsEntity.CONST_SUMMARY, SUMMARY);
        jsonObject.put(NewsEntity.CONST_URL, STORY_URL);
        jsonObject.put(NewsEntity.CONST_IMAGE_URL, IMAGE_URL);
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("url", "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg");
        array.put(item);

        jsonObject.put(NewsEntity.CONST_MULTIMEDIA, array);
        newsEntity = new NewsEntity(jsonObject);
    }

    @Test
    public void validateStoryTitle() throws Exception {
        assertEquals(TITLE, newsEntity.getTitle());
    }

    @Test
    public void validateStorySummary() throws Exception {
        assertEquals(SUMMARY, newsEntity.getSummary());
    }

    @Test
    public void validateStoryUrl() throws Exception {
        assertEquals(STORY_URL, newsEntity.getArticleUrl());
    }

    @Test
    public void validateImageUrl() throws Exception {
        assertEquals(IMAGE_URL, newsEntity.getMediaEntity().get(0).getUrl());
    }
}
