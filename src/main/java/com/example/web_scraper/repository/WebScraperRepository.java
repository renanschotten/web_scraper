package com.example.web_scraper.repository;

import com.example.web_scraper.model.DataModel;
import org.springframework.stereotype.Repository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WebScraperRepository {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    public static final String BASE_URL = "https://google.com/search?q=";
    public List<DataModel> getData(String searchContent)  {
        List<String> titleList = new ArrayList();
        List<String> urlList = new ArrayList();
        List<DataModel> dataModels = new ArrayList();

        final Document doc;
        try {
            doc = Jsoup.connect(BASE_URL.concat(searchContent)).userAgent(USER_AGENT).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Element result : doc.select("div.vvjwJb")){
            titleList.add(result.text());
        }

        for (Element result : doc.select("div.egMi0 a")){
            String url = result.attr("href").substring(30);
            url = url.substring(0, url.indexOf('&'));
            urlList.add(url);
        }

        if(titleList.size() == urlList.size()) {
            for (Integer i = 0; i < titleList.size(); i++) {
                dataModels.add(new DataModel(titleList.get(i), urlList.get(i)));
            }
        }
        if(titleList.size() >= urlList.size()) {
            for (Integer i = 0; i < urlList.size(); i++) {
                dataModels.add(new DataModel(titleList.get(i), urlList.get(i)));
            }
        }
        if(titleList.size() <= urlList.size()) {
            for (Integer i = 0; i < titleList.size(); i++) {
                    dataModels.add(new DataModel(titleList.get(i), urlList.get(i)));
            }
        }
        return dataModels;
    }
}
