package com.launchacademy.consumelater.services;

import com.launchacademy.consumelater.dto.UrlDto;
import com.launchacademy.consumelater.mappers.UrlMapper;
import com.launchacademy.consumelater.models.Url;
import com.launchacademy.consumelater.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UrlService {

  private final UrlRepository urlRepository;
  private final UrlMapper urlMapper;

  @Autowired
  public UrlService(UrlRepository urlRepository, UrlMapper urlMapper) {
    this.urlRepository = urlRepository;
    this.urlMapper = urlMapper;
  }

  public Page<UrlDto> findAll(Pageable pageable) {
    Page<Url> page = urlRepository.findAll(pageable);
    return new PageImpl<UrlDto>(urlMapper.urlsToUrlDtos(page.getContent()), pageable,
        page.getTotalElements());
  }
}