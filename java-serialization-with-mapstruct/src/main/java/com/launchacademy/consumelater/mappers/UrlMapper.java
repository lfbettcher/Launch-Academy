package com.launchacademy.consumelater.mappers;

import com.launchacademy.consumelater.dto.UrlDto;
import com.launchacademy.consumelater.models.Url;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface UrlMapper {

  @Mapping(source = "mediaType", target = "contentType")
  UrlDto urlToUrlDto(Url url);

  List<UrlDto> urlsToUrlDtos(List<Url> urls);
}