package com.techInterview.restWs.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techInterview.restWs.beans.Response;
import com.techInterview.restWs.exceptions.TreatmentException;
import com.techInterview.restWs.services.NumberTranslateService;
import com.techInterview.restWs.services.ProcessStringService;

@RestController
public class ProcessController {
	
	@Autowired
	private ProcessStringService stringService;
	
	@Autowired
	private NumberTranslateService numberService;
	
	/*@GetMapping("/stringDedup")
	@ResponseBody
	public ResponseEntity<String> stringDedup(@RequestParam String chaine) {
		return new ResponseEntity(stringService.uniqueOrderedString(chaine), HttpStatus.OK);
	}*/
	
	@GetMapping("/stringDedup")
	@ResponseBody
	public ResponseEntity stringDedup(@RequestParam String value) {
		if(value.length() <= 30) {
			return new ResponseEntity(new Response(stringService.uniqueOrderedString(value)), HttpStatus.OK);
		} else {
			throw new TreatmentException("String size must be less than 30");
		}
	}
	
	@GetMapping("/writeNumber")
	public ResponseEntity writeNumber(@RequestParam int value) {
		if(value > 0 && value < 1000000) {
			return new ResponseEntity(new Response(numberService.numberToLetter(value)), HttpStatus.OK);
		} else {
			throw new TreatmentException("Number must be between 0 & 999 999");
		}
	}

}
