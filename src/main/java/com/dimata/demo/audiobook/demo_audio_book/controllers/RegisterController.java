package com.dimata.demo.sekolah.demo_audio_book.controllers;

import com.dimata.demo.sekolah.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.sekolah.demo_audio_book.forms.RegisterForm;
import com.dimata.demo.sekolah.demo_audio_book.models.table.Register;
import com.dimata.demo.sekolah.demo_audio_book.services.api.RegisterApi;

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
    private RegisterApi dataSiswaApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Register> maintainerAddRegister(@RequestBody RegisterForm form) {
        return dataSiswaApi.createRegister(form);
    }

    @GetMapping(path = BASE_URL + "/register")
    public Flux<Register> maintainerGetAllRegister(CommonParam param) {
        return dataSiswaApi.getAllRegister(param);
    }

    @GetMapping(path = BASE_URL + "/register/{register_code}")
    public Mono<Register> maintainerGetRegister(@PathVariable("regiser_code") Long register_code) {
        return dataSiswaApi.getRegister(register_code);
    }

    @PutMapping(path = BASE_URL + "/register/{email}")
    public Mono<Register> maintainerUpdateRegister(@PathVariable("register_code") Long register_code, @RequestBody RegisterForm form) {
        return dataSiswaApi.updateRegister(register_code, form);
    }


}
