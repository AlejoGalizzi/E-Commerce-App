package com.alejogalizzi.ecommerce.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDTO {


  private long id;

  private String name;

  private byte[] data;
}
