package news.agoda.com.sample.module.newslist.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.module.newslist.model.MediaEntity;
import news.agoda.com.sample.module.newslist.model.NewsEntity;

public class NewsListAdapter extends ArrayAdapter {

    public NewsListAdapter(Context context, int resource, List<NewsEntity> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View cv = convertView;
        if (cv == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            cv = inflater.inflate(R.layout.list_item_news, parent, false);
            viewHolder.newsTitle = (TextView) cv.findViewById(R.id.news_title);
            viewHolder.imageView = (DraweeView) cv.findViewById(R.id.news_item_image);
            cv.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) cv.getTag();
        }

        NewsEntity newsEntity = (NewsEntity) getItem(position);
        List<MediaEntity> mediaEntityList;
        String thumbnailURL;
        if (newsEntity != null) {
            mediaEntityList = newsEntity.getMediaEntity();
            viewHolder.newsTitle.setText(newsEntity.getTitle());
            if (mediaEntityList != null && !mediaEntityList.isEmpty()) {
                MediaEntity mediaEntity = mediaEntityList.get(0);
                thumbnailURL = mediaEntity.getUrl();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri
                        (Uri.parse(thumbnailURL))).setOldController(viewHolder.imageView.getController()).build();
                viewHolder.imageView.setController(draweeController);
            } else {
                viewHolder.imageView.setController(null);
            }
        }

        return cv;
    }

    class ViewHolder {
        TextView newsTitle;
        DraweeView imageView;
    }
}
