package com.dimata.demo.audiobook.demo_audio_book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.forms.UserForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.User;
import com.dimata.demo.audiobook.demo_audio_book.services.api.UserApi;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Usercontroller {
    @Autowired
    private UserApi userApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> maintainerAddUser(@RequestBody UserForm form) {
        return userApi.createUser(form);

    }

    @GetMapping(path = BASE_URL + "/user")
    public Flux<User> maintainerGetAllUser(CommonParam param) {
        return userApi.getAllUser(param);
    }

    @GetMapping(path = BASE_URL + "/user/{id_user}")
    public Mono<User> maintainerGetUser(@PathVariable("user_code") Long user_code) {
        
        return userApi.getUser(user_code);
    }

    @PutMapping(path = BASE_URL + "/user/{id_user}")
    public Mono<User> maintainerUpdateUser(@PathVariable("user_code") long user_code, @RequestBody UserForm form) {
        return userApi.updateUser(user_code, form);
    }
}
