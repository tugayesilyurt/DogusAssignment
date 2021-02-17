package com.dogus.assisgnment.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogus.assignment.exception.AlreadyExistException;
import com.dogus.assignment.exception.ErrorResponse;
import com.dogus.assisgnment.dto.NumberDto;
import com.dogus.assisgnment.request.NumberRequest;
import com.dogus.assisgnment.service.NumberService;

@RestController
@RequestMapping("/number")
public class NumberController {

	@Autowired
	private NumberService numberService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/v1/add", produces = APPLICATION_JSON)
	public ResponseEntity<Object> addNumber(@RequestBody NumberRequest numberRequest) {

		try {
			numberService.insertNumber(numberRequest);
			return new ResponseEntity<Object>(Boolean.TRUE, new HttpHeaders(), HttpStatus.OK);
		} catch (AlreadyExistException e) {
			List<String> details = new ArrayList<>();
			details.add(e.getLocalizedMessage() + " Number : " + numberRequest.getNumber());
			ErrorResponse error = new ErrorResponse("Already Exist", details);
			return new ResponseEntity(error, HttpStatus.OK);
		}

	}

	@PostMapping(value = "/v1/update", produces = APPLICATION_JSON)
	public ResponseEntity<Boolean> updateNumber(@RequestBody NumberRequest numberRequest) {

		numberService.updateNumber(numberRequest);
		return new ResponseEntity<Boolean>(Boolean.TRUE, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = { "/v1/list", "/v1/list/{orderBy}"})
	public ResponseEntity<List<NumberDto>> getListNumber(
			@PathVariable(value = "orderBy", required = false) Optional<String> orderBy) {

		return new ResponseEntity<List<NumberDto>>(numberService.getListOfNumber(orderBy), new HttpHeaders(),
				HttpStatus.OK);
	}

	@GetMapping(value = "/v1/{number}")
	public ResponseEntity<NumberDto> getNumberDto(@PathVariable int number) {
		return new ResponseEntity<NumberDto>(numberService.getNumber(number), new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/v1/{number}")
	public ResponseEntity<Boolean> deleteNumber(@PathVariable int number) {

		numberService.deleteNumber(number);
		return new ResponseEntity<Boolean>(Boolean.TRUE, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/minimum")
	public ResponseEntity<NumberDto> getMinimumNumber() {
		return new ResponseEntity<NumberDto>(numberService.getMinimumNumber(), new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/maximum")
	public ResponseEntity<NumberDto> getMaximumNumber() {
		return new ResponseEntity<NumberDto>(numberService.getMaximumNumber(), new HttpHeaders(), HttpStatus.OK);
	}

}
