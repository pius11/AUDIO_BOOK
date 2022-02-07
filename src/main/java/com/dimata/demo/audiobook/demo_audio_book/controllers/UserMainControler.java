package com.dimata.demo.audiobook.demo_audio_book.controllers;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.forms.UserMainForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.UserMain;
import com.dimata.demo.audiobook.demo_audio_book.services.api.UserMainApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMainControler {
    @Autowired
    private UserMainApi UserMainApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/user_main", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserMain> maintainerAddUserMain(@RequestBody /*DataAll*/UserMainForm form) {
        return UserMainApi.createUserMain(form);
    }

    @GetMapping(path = BASE_URL + "/user_main")
    public Flux<UserMain> maintainerGetAllUserMain(CommonParam param) {
        return UserMainApi.getAllUserMain(param);
    }

    @GetMapping(path = BASE_URL + "/user_main/{id_main}")
    public Mono<UserMain> maintainerGetUserMain(@PathVariable("id_main") Long id_main) {
        return UserMainApi.getUserMain(id_main);
    }

    @PutMapping(path = BASE_URL + "/user_main/{id_main}")
    public Mono<UserMain> maintainerUpdateUserMain(@PathVariable("id_main") Long id_main, @RequestBody UserMainForm form) {
        return UserMainApi.updateUserMain(id_main, form);
    }
}
