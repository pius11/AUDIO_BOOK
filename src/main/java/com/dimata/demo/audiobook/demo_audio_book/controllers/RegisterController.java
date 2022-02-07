package com.dimata.demo.audiobook.demo_audio_book.controllers;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.forms.CheckUserAndPasswordForm;
import com.dimata.demo.audiobook.demo_audio_book.forms.RegisterForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataUser;
import com.dimata.demo.audiobook.demo_audio_book.models.table.Register;

import com.dimata.demo.audiobook.demo_audio_book.services.api.RegisterApi;


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
public class RegisterController {

    @Autowired
    private RegisterApi RegisterApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Register> maintainerAddRegister(@RequestBody RegisterForm form) {
        return RegisterApi.createRegister(form);
    }

    @GetMapping(path = BASE_URL + "/register")
    public Flux<Register> maintainerGetAllRegister(CommonParam param) {
        return RegisterApi.getAllRegister(param);
    }

    @GetMapping(path = BASE_URL + "/register/{register_code}")
    public Mono<Register> maintainerGetRegister(@PathVariable("register_code") Long register_code) {
        return RegisterApi.getRegister(register_code);
    }

    @PutMapping(path = BASE_URL + "/register/{register_code}")
    public Mono<Register> maintainerUpdateRegister(@PathVariable("register_code") Long register_code, @RequestBody RegisterForm form) {
        return RegisterApi.updateRegister(register_code, form);
    }

    
    @PostMapping(path = BASE_URL +"/user/login")
    public Mono<DataUser> maintainerLoginUser(@RequestBody CheckUserAndPasswordForm form) {
        return RegisterApi.checkAvailableData(form)
            .flatMap(f -> RegisterApi.getUserDetail(f.getEmail()));
    }
}