package yvonneyey.bignews;

import java.util.Date;

/**
 * Created by Yvonne on 2017/9/7.
 */

public class News {
    private String title;
    private Date date;
    private String url;

    public News(String title, Date date, String url) {
        this.title=title;
        this.date=date;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
