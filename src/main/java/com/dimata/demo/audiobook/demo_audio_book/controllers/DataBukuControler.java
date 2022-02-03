package com.dimata.demo.audiobook.demo_audio_book.controllers;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.forms.DataBukuForm;
import com.dimata.demo.audiobook.demo_audio_book.models.response.BookAndRating;
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
    private DataBukuApi DataBukuApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/data_buku", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DataBuku> maintainerAddDataBuku(@RequestBody DataBukuForm form) {
        return DataBukuApi.createDataBuku(form);
    }

    @GetMapping(path = BASE_URL + "/data_buku")
    public Flux<DataBuku> maintainerGetAllDataBuku(CommonParam param) {
        return DataBukuApi.getAllDataBuku(param);
    }

    @GetMapping(path = BASE_URL + "/data_buku/{id_book}")
    public Mono<DataBuku> maintainerGetDataBuku(@PathVariable("id_book") Long id_book) {
        return DataBukuApi.getDataBuku(id_book);
    }

    @PutMapping(path = BASE_URL + "/DataBuku/{id_book}")
    public Mono<DataBuku> maintainerUpdateDataBuku(@PathVariable("id_book") Long id_book, @RequestBody DataBukuForm form) {
        return DataBukuApi.updateDataBuku(id_book, form);
    }

    @GetMapping(path = BASE_URL + "/rating/{bookId}")
    public Mono<BookAndRating> maintainerGetBookAndRating(@PathVariable("bookId") Long bookId) {
        return DataBukuApi.getBookAndRating(bookId);
    }
}
