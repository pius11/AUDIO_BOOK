package com.dimata.demo.audiobook.demo_audio_book.controllers;

import com.dimata.demo.audiobook.demo_audio_book.forms.RatingHistoryForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.RatingHistory;
import com.dimata.demo.audiobook.demo_audio_book.services.api.RatingHistoryApi;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingHistoryControler {
    @Autowired
    private RatingHistoryApi ratingHistoryApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/rating_history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RatingHistory>maintainerAddRatingHistory(@RequestBody RatingHistoryForm form) {
        return ratingHistoryApi.createRatingHistory(form);

    }

    @GetMapping(path = BASE_URL + "/rating_history")
    public Flux<RatingHistory> maintainerGetAlslRatingHistory(CommonParam param) {
        return ratingHistoryApi.getAllRatingahistory(param);
    }

    @GetMapping(path = BASE_URL + "/rating_history/{id_history}")
    public Mono<RatingHistory> maintainerGetRatingHistory(@PathVariable("id_history") Long id_history) {
        
        return ratingHistoryApi.getRatingHistory(id_history);
    }

    @PutMapping(path = BASE_URL + "/rating_history/{id_history}")
    public Mono<RatingHistory> maintainerUpdateRatingHisory(@PathVariable("id_history") long id_history, @RequestBody RatingHistoryForm form) {
        return ratingHistoryApi.updateRatingHistory(id_history, form);
    }
}
