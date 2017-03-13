package Utils;

import Model.Xkcd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milos on 3/10/2017.
 */
public class XkcdServices implements Services<Xkcd>{
    private String url = "https://xkcd.com/";
    private String urlAfter = "info.0.json";

    public XkcdServices() {}

    public XkcdServices(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlAfter() { return urlAfter; }

    public void setUrlAfter(String urlAfter) {
        this.urlAfter = urlAfter;
    }

    @Override
    public String toString() {
        return "XkcdServices{" +
                "url='" + url + '\'' +
                '}';
    }

    public List<Xkcd> methodGet() {
        List<Xkcd> Xkcds = new ArrayList<Xkcd>();

        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuilder sb = new StringBuilder();

            Gson gson = new Gson();
            Type type = new TypeToken<List<Xkcd>>() {}.getType();
            while ((output = br.readLine()) != null) {
                Xkcds = gson.fromJson(output, type);
            }
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Xkcds;
    }

    @Override
    public Xkcd methodGetSpecific(int id) {
        Xkcd xkcd = new Xkcd();
        try {
            URL url = new URL(this.url + "/" + id + "/" + urlAfter);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuilder sb = new StringBuilder();

            Gson gson = new Gson();

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            xkcd = gson.fromJson(sb.toString(), Xkcd.class);
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xkcd;
    }

    public void methodPost(Xkcd t) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(new Gson().toJson(t));
            pw.close();
            pw.flush();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Xkcd methodGetNewest() {
        Xkcd xkcd = new Xkcd();
        try {
            URL url = new URL(this.url + "/" + urlAfter);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuilder sb = new StringBuilder();

            Gson gson = new Gson();

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            xkcd = gson.fromJson(sb.toString(), Xkcd.class);
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xkcd;
    }

    @Override
    public boolean methodGetExists(int id) {
        return true;
    }
}
