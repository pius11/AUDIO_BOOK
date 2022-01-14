package com.dimata.demo.audiobook.demo_audio_book.controllers;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.forms.DataBukuForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;
import com.dimata.demo.audiobook.demo_audio_book.services.api.DataBukuApi;

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
public class DataBukuControler {
    @Autowired
    private DataBukuApi dataBukuApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/data_buku", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DataBuku> maintainerAddDataBuku(@RequestBody DataBukuForm form) {
        return dataBukuApi.createDataBuku(form);

    }

    @GetMapping(path = BASE_URL + "/data_buku")
    public Flux<DataBuku> maintainerGetAlslDataBuku(CommonParam param) {
        return dataBukuApi.getAllDataBuku(param);
    }

    @GetMapping(path = BASE_URL + "/data_buku/{id_book}")
    public Mono<DataBuku> maintainerGetDataBuku(@PathVariable("id_book") Long id_book) {
        
        return dataBukuApi.getDataBuku(id_book);
    }

    @PutMapping(path = BASE_URL + "/data_buku/{id_book}")
    public Mono<DataBuku> maintainerUpdateDataBuku(@PathVariable("id_book") long id_book, @RequestBody DataBukuForm form) {
        return dataBukuApi.updateDataBuku(id_book, form);
    }
}
