package com.launchacademy.marathon.services;

import com.launchacademy.marathon.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

  private final SongRepository songRepository;

  @Autowired
  public SongService(SongRepository songRepository) {
    this.songRepository = songRepository;
  }
}
