package com.kori1304.jpayouthdepartmentregister._config;

import java.time.LocalDate;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    Converter<String, LocalDate> toLocalDate = ctx ->
        ctx.getSource() == null ? null : LocalDate.parse(ctx.getSource());

    modelMapper.addConverter(toLocalDate);

    return modelMapper;
  }

}

