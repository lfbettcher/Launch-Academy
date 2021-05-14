package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import com.launchacademy.marathon.services.SongService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongsController {

  private final SongRepository songRepository;
  private final SongService songService;

  @Autowired
  public SongsController(SongRepository songRepository, SongService songService) {
    this.songRepository = songRepository;
    this.songService = songService;
  }

  @GetMapping
  public String getSongs(Pageable pageable, Model model) {
    model.addAttribute("songs", songRepository.findAll(pageable));
    return "songs/index";
  }

  @GetMapping("/new")
  public String newSong(@ModelAttribute Song song) {
    return "songs/new";
  }

  @PostMapping
  public String addSong(@ModelAttribute @Valid Song song, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "songs/new";
    } else {
      songRepository.save(song);
      return "redirect:/songs";
    }
  }
}
