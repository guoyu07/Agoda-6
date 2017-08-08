package news.agoda.com.sample.module.newslist.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MediaParserTest {

    private static final String CONTENT = "{\n" +
            "  \"status\": \"OK\",\n" +
            "  \"copyright\": \"Copyright (c) 2015 The New York Times Company. All Rights Reserved.\",\n" +
            "  \"section\": \"technology\",\n" +
            "  \"last_updated\": \"2015-08-18T10:15:06-05:00\",\n" +
            "  \"num_results\": 24,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"section\": \"Business Day\",\n" +
            "      \"subsection\": \"\",\n" +
            "      \"title\": \"Work Policies May Be Kinder, but Brutal Competition Isn’t\",\n" +
            "      \"abstract\": \"Top-tier employers may be changing their official policies in a nod to work-life balance, but brutal competition remains an inescapable component of workers’ daily lives.\",\n" +
            "      \"url\": \"http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html\",\n" +
            "      \"byline\": \"By NOAM SCHEIBER\",\n" +
            "      \"item_type\": \"Article\",\n" +
            "      \"updated_date\": \"2015-08-17T22:10:02-5:00\",\n" +
            "      \"created_date\": \"2015-08-17T22:10:04-5:00\",\n" +
            "      \"published_date\": \"2015-08-18T04:00:00-5:00\",\n" +
            "      \"material_type_facet\": \"News\",\n" +
            "      \"kicker\": \"\",\n" +
            "      \"des_facet\": [\n" +
            "        \"Workplace Environment\",\n" +
            "        \"Executives and Management (Theory)\",\n" +
            "        \"Paid Time Off\",\n" +
            "        \"Legal Profession\",\n" +
            "        \"Banking and Financial Institutions\",\n" +
            "        \"Computers and the Internet\"\n" +
            "      ],\n" +
            "      \"org_facet\": [\n" +
            "        \"Amazon.com Inc\",\n" +
            "        \"Cravath Swaine & Moore\"\n" +
            "      ],\n" +
            "      \"per_facet\": [\n" +
            "        \"Bezos, Jeffrey P\"\n" +
            "      ],\n" +
            "      \"geo_facet\": [\n" +
            "        \"Silicon Valley (Calif)\"\n" +
            "      ],\n" +
            "      \"multimedia\": [\n" +
            "        {\n" +
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg\",\n" +
            "          \"format\": \"Standard Thumbnail\",\n" +
            "          \"height\": 75,\n" +
            "          \"width\": 75,\n" +
            "          \"type\": \"image\",\n" +
            "          \"subtype\": \"photo\",\n" +
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n" +
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbLarge.jpg\",\n" +
            "          \"format\": \"thumbLarge\",\n" +
            "          \"height\": 150,\n" +
            "          \"width\": 150,\n" +
            "          \"type\": \"image\",\n" +
            "          \"subtype\": \"photo\",\n" +
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n" +
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-articleInline.jpg\",\n" +
            "          \"format\": \"Normal\",\n" +
            "          \"height\": 127,\n" +
            "          \"width\": 190,\n" +
            "          \"type\": \"image\",\n" +
            "          \"subtype\": \"photo\",\n" +
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n" +
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-mediumThreeByTwo210.jpg\",\n" +
            "          \"format\": \"mediumThreeByTwo210\",\n" +
            "          \"height\": 140,\n" +
            "          \"width\": 210,\n" +
            "          \"type\": \"image\",\n" +
            "          \"subtype\": \"photo\",\n" +
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n" +
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private MediaParser mediaParser;
    private List<NewsEntity> dummyNewsList;
    private List<NewsEntity> parsedNewsList;

    @Before
    public void setUp() throws Exception {

        dummyNewsList = new ArrayList<>();

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.title = "Work Policies May Be Kinder, but Brutal Competition Isn’t";
        newsEntity.summary = "Top-tier employers may be changing their official policies in a nod to work-life balance, but brutal competition remains an inescapable component of workers’ daily lives.";
        newsEntity.articleUrl = "http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html";

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.url = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg";

        newsEntity.mediaEntityList = new ArrayList<>();
        newsEntity.mediaEntityList.add(mediaEntity);

        dummyNewsList.add(newsEntity);

        mediaParser = new MediaParser();
        parsedNewsList = mediaParser.parseMediaContent(CONTENT);
    }

    @Test
    public void validateStoryTitle() throws Exception {
        assertEquals(dummyNewsList.get(0).getTitle(), parsedNewsList.get(0).getTitle());
    }

    @Test
    public void validateStorySummary() throws Exception {
        assertEquals(dummyNewsList.get(0).getSummary(), parsedNewsList.get(0).getSummary());
    }

    @Test
    public void validateStoryUrl() throws Exception {
        assertEquals(dummyNewsList.get(0).getArticleUrl(), parsedNewsList.get(0).getArticleUrl());
    }

    @Test
    public void validateImageUrl() throws Exception {
        assertEquals(dummyNewsList.get(0).getMediaEntity().get(0).getUrl(), parsedNewsList.get(0).getMediaEntity().get(0).getUrl());
    }
}
