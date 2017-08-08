package news.agoda.com.sample.module.newslist.view;

import android.support.test.InstrumentationRegistry;
import android.test.AndroidTestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.module.newslist.model.MediaEntity;
import news.agoda.com.sample.module.newslist.model.NewsEntity;

public class NewsListAdapterTest extends AndroidTestCase {
    private NewsListAdapter mAdapter;
    public NewsListAdapterTest() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();
        List<NewsEntity>  data = new ArrayList<>();

        NewsEntity newsEntity1 = new NewsEntity();
        newsEntity1.title = "Work Policies May Be Kinder, but Brutal Competition Isn’t";
        newsEntity1.summary = "Top-tier employers may be changing their official policies in a nod to work-life balance, but brutal competition remains an inescapable component of workers’ daily lives.";
        newsEntity1.articleUrl = "http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html";

        MediaEntity mediaEntity1 = new MediaEntity();
        mediaEntity1.url = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg";

        newsEntity1.mediaEntityList = new ArrayList<>();
        newsEntity1.mediaEntityList.add(mediaEntity1);

        data.add(newsEntity1);

        NewsEntity newsEntity2 = new NewsEntity();
        newsEntity2.title = "Work Policies May Be Kinder, but Brutal Competition Isn’t";
        newsEntity2.summary = "Top-tier employers may be changing their official policies in a nod to work-life balance, but brutal competition remains an inescapable component of workers’ daily lives.";
        newsEntity2.articleUrl = "http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html";

        MediaEntity mediaEntity2 = new MediaEntity();
        mediaEntity2.url = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg";

        newsEntity2.mediaEntityList = new ArrayList<>();
        newsEntity2.mediaEntityList.add(mediaEntity2);

        data.add(newsEntity1);

        mAdapter = new NewsListAdapter(InstrumentationRegistry.getTargetContext(), 0, data);
    }

    @Test
    public void testGetItemId() {
        assertEquals("Wrong ID.", 0, mAdapter.getItemId(0));
    }

    @Test
    public void testGetCount() {
        assertEquals("Contacts amount incorrect.", 2, mAdapter.getCount());
    }

}