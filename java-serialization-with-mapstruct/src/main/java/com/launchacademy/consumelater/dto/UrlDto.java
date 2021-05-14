package com.launchacademy.consumelater.dto;

import com.launchacademy.consumelater.models.Url;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UrlDto {

  private Integer id;
  private String url;
  private String description;
  private String contentType;

  public static UrlDto fromUrl(Url url) {
    UrlDto dto = new UrlDto();
    dto.setId(url.getId());
    dto.setUrl(url.getUrl());
    dto.setDescription(url.getDescription());
    dto.setContentType(url.getMediaType());
    return dto;
  }
}