package com.dogus.assisgnment.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dogus.assignment.exception.AlreadyExistException;
import com.dogus.assignment.exception.RecordNotFoundException;
import com.dogus.assisgnment.dto.NumberDto;
import com.dogus.assisgnment.entity.Numbers;
import com.dogus.assisgnment.repository.NumberRepository;
import com.dogus.assisgnment.request.NumberRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NumberService {

	private final NumberRepository numberRepository;
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void insertNumber(NumberRequest numberRequest) {
		
		Optional<Numbers> numberData = numberRepository.findByNumber(numberRequest.getNumber());
		if (numberData.isPresent())
			throw new AlreadyExistException();
		
		Numbers number = new Numbers();
		number.setNumber(null != numberRequest ? numberRequest.getNumber() : null);
		number.setCreatedDate(new Date());
		numberRepository.save(number);

	}

	public NumberDto getNumber(int number) {

		Numbers numberData = numberRepository.findByNumber(number).orElseThrow(RecordNotFoundException::new);

		return getNumberDto(numberData);

	}

	public void updateNumber(NumberRequest numberRequest) {

		Numbers numberData = numberRepository.findByNumber(numberRequest.getNumber())
				.orElseThrow(RecordNotFoundException::new);
		numberData.setNumber(numberRequest.getNumber());
		numberData.setUpdatedDate(new Date());
		numberRepository.save(numberData);

	}

	public List<NumberDto> getListOfNumber(Optional<String> orderBy) {
		List<Numbers> numbers = null;
		if (orderBy.isPresent()) {
			if (orderBy.get().equalsIgnoreCase("DESC"))
				numbers = numberRepository.findAll(Sort.by(Sort.Direction.DESC, "number"));
			else
				numbers = numberRepository.findAll(Sort.by(Sort.Direction.ASC, "number"));
		} else {
			numbers = numberRepository.findAll(Sort.by(Sort.Direction.ASC, "number"));
		}

		return numbers.parallelStream().map(this::getNumberDto).collect(Collectors.toList());

	}

	public NumberDto getNumberDto(Numbers number) {
		if (null == number)
			return null;
		NumberDto dto = new NumberDto();
		dto.setNumber(number.getNumber());
		dto.setCreatedDate(null != number.getCreatedDate() ? formatter.format(number.getCreatedDate()) : null);
		dto.setUpdateDate(null != number.getUpdatedDate() ? formatter.format(number.getUpdatedDate()) : null);
		return dto;
	}

	public void deleteNumber(int number) {

		numberRepository.deleteByNumber(number);

	}

	public NumberDto getMinimumNumber() {

		return getNumberDto(numberRepository.findTopByOrderByNumberAsc().isPresent()
				? numberRepository.findTopByOrderByNumberAsc().get()
				: null);

	}

	public NumberDto getMaximumNumber() {

		return getNumberDto(numberRepository.findTopByOrderByNumberDesc().isPresent()
				? numberRepository.findTopByOrderByNumberDesc().get()
				: null);

	}

}
