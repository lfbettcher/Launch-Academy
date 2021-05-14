package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import com.launchacademy.marathon.services.SongService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/songs")
public class SongsApiV1Controller {

  private final SongRepository songRepository;
  private final SongService songService;

  @Autowired
  public SongsApiV1Controller(SongRepository songRepository, SongService songService) {
    this.songRepository = songRepository;
    this.songService = songService;
  }

  @GetMapping
  public Page<Song> getSongs(Pageable pageable) {
    return songRepository.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Song getSong(@PathVariable Integer id) {
    return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException());
  }

  @PostMapping()
  public Song createSong(@RequestBody Song song) {
    return songRepository.save(song);
  }
  // curl -X POST localhost:8080/api/v1/songs -H 'Content-type:application/json' -d
  // '{"title": "song thru curl", "genre": "genrecurl", "releaseYear": "2020", "explicitContent": "true"}'

  @NoArgsConstructor
  private class SongNotFoundException extends RuntimeException {

  }

  @ControllerAdvice
  private class SongNotFoundAdvice {

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String songNotFoundHandler(SongNotFoundException e) {
      return e.getMessage();
    }
  }
}
