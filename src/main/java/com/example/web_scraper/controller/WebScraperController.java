package com.example.web_scraper.controller;

import com.example.web_scraper.model.DataModel;
import com.example.web_scraper.service.WebScraperService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping(path = "api/v1/scraper")
public class WebScraperController {
    @Autowired
    private WebScraperService service;


    @GetMapping(path = "{searchContent}")
    public ResponseEntity<List<DataModel>> getData(@PathVariable("searchContent") String searchContent){
        return ResponseEntity.ok().body(service.getData(searchContent));
    }
}
