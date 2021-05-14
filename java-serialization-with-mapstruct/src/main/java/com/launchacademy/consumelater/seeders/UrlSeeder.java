package com.launchacademy.consumelater.seeders;

import com.launchacademy.consumelater.models.Url;
import com.launchacademy.consumelater.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UrlSeeder implements CommandLineRunner {
  private UrlRepository urlRepo;

  @Autowired
  public void setUrlRepository(UrlRepository urlRepo) {
    this.urlRepo = urlRepo;
  }
  @Override
  public void run(String... args) throws Exception {
    final String[] urls = {
      "https://launchacademy.com/blog/launched-stories-from-pastry-chef-to-developer-facebook",
      "https://launchacademy.com/blog/outcomes-five-things-to-ask-when-selecting-a-coding-bootcamp",
      "https://keepachangelog.com/en/1.1.0/"
    };

    for(String stringUrl : urls) {
      if(urlRepo.findAllByUrlStartingWith(stringUrl).size() == 0) {
        Url url = new Url();
        url.setUrl(stringUrl);
        url.setMediaType("text");
        urlRepo.save(url);
      }
    }
  }
}
