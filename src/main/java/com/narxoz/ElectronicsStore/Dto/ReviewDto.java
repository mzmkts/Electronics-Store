package com.narxoz.ElectronicsStore.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewIdDto;
    private String reviewDto;
    private int ratingDto;
    private UserDto userDto;
}
