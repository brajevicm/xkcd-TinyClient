package Model;

/**
 * Created by Milos on 3/10/2017.
 */
public class Xkcd {

    public Xkcd() {}

    private String month;

    public String getMonth() { return this.month; }

    public void setMonth(String month) { this.month = month; }

    private int num;

    public int getNum() { return this.num; }

    public void setNum(int num) { this.num = num; }

    private String link;

    public String getLink() { return this.link; }

    public void setLink(String link) { this.link = link; }

    private String year;

    public String getYear() { return this.year; }

    public void setYear(String year) { this.year = year; }

    private String news;

    public String getNews() { return this.news; }

    public void setNews(String news) { this.news = news; }

    private String safe_title;

    public String getSafeTitle() { return this.safe_title; }

    public void setSafeTitle(String safe_title) { this.safe_title = safe_title; }

    private String transcript;

    public String getTranscript() { return this.transcript; }

    public void setTranscript(String transcript) { this.transcript = transcript; }

    private String alt;

    public String getAlt() { return this.alt; }

    public void setAlt(String alt) { this.alt = alt; }

    private String img;

    public String getImg() { return this.img; }

    public void setImg(String img) { this.img = img; }

    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    private String day;

    public String getDay() { return this.day; }

    public void setDay(String day) { this.day = day; }

    @Override
    public String toString() {
        return "Xkcd{" +
                "month='" + month + '\'' +
                ", num=" + num +
                ", link='" + link + '\'' +
                ", year='" + year + '\'' +
                ", news='" + news + '\'' +
                ", safe_title='" + safe_title + '\'' +
                ", transcript='" + transcript + '\'' +
                ", alt='" + alt + '\'' +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
