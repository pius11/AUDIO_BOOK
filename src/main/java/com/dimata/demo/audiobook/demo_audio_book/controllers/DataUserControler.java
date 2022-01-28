package com.dimata.demo.audiobook.demo_audio_book.controllers;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.forms.DataUserForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataUser;
import com.dimata.demo.audiobook.demo_audio_book.services.api.DataUserApi;

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
public class DataUserControler {
    @Autowired
    private DataUserApi dataUserApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/data_user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DataUser> maintainerAddDataUser(@RequestBody DataUserForm form) {
        return dataUserApi.createDataUser(form);

    }

    @GetMapping(path = BASE_URL + "/data_user")
    public Flux<DataUser> maintainerGetAlslDataUser(CommonParam param) {
        return dataUserApi.getAllDataUser(param);
    }

    @GetMapping(path = BASE_URL + "/data_user/{user_code}")
    public Mono<DataUser> maintainerGetDataUser(@PathVariable("user_code") Long user_code) {
        
        return dataUserApi.getDataUser(user_code);
    }

    @PutMapping(path = BASE_URL + "/data_user/{user_code}")
    public Mono<DataUser> maintainerUpdateDataUser(@PathVariable("user_code") long user_code, @RequestBody DataUserForm form) {
        return dataUserApi.updateDataUser(user_code, form);
    }
}
