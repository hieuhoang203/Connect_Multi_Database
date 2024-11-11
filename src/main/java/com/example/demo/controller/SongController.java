package com.example.demo.controller;

import com.example.demo.common.request.SongRequest;
import com.example.demo.live.entity.SongLive;
import com.example.demo.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/song")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping(value = "/save")
    public SongLive save(@RequestBody SongRequest request) {
        return songService.save(request);
    }

    @PutMapping(value = "/update/{id}")
    public SongLive update(@PathVariable String id, @RequestBody SongRequest request) {
        return songService.update(id, request);
    }

    @DeleteMapping(value = "/delete/{id}")
    public SongLive delete(@PathVariable String id) {
        return songService.delete(id);
    }

    @GetMapping(value = "/search/{id}")
    public SongLive search(@PathVariable String id) {
        return songService.search(id);
    }

}
