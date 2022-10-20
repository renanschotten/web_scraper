package com.example.web_scraper.service;

import com.example.web_scraper.model.DataModel;
import com.example.web_scraper.repository.WebScraperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebScraperService {
    @Autowired
    private WebScraperRepository repository;
    public List<DataModel> getData(String searchContent) {
        return repository.getData(searchContent);
    }
}
