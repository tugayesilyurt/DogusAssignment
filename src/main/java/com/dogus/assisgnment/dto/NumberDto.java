package com.dogus.assisgnment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NumberDto {
	private int number;
	private String createdDate;
	private String updateDate;
}
