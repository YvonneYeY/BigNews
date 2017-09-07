package yvonneyey.bignews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvonne on 2017/9/7.
 * 用于listView的适配器
 */

public class NewsAdapter extends ArrayAdapter <News>{
    private List<News> news=new ArrayList<>();


    public NewsAdapter(@NonNull Context context, List <News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    /**
     获取界面
     **/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemVies=convertView;
        if(listItemVies==null){
            listItemVies= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        News curentNews=(News)getItem(position);
        String title=curentNews.getNewTitle();
        Long publishDate=curentNews.getPublicationDate();
        TextView titleText=(TextView)listItemVies.findViewById(R.id.newsspTitle);
        TextView dateText=(TextView)listItemVies.findViewById(R.id.newsDate);
        titleText.setText(title);
        dateText.setText(publishDate.toString());
        return listItemVies;
    }
}
