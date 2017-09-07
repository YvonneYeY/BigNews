package yvonneyey.bignews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private NewsAdapter adapter;
    private List<News> newsList;
    private TextView emptyText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.newsListview);
        newsList=new ArrayList<>();
        adapter=new NewsAdapter(this,newsList);
        emptyText=(TextView)findViewById(R.id.empty_text);
        progressBar=(ProgressBar)findViewById(R.id.progresbar);

        listView.setAdapter(adapter);
        listView.setEmptyView(emptyText);


    }
}
