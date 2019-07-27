package com.techInterview.restWs.controllers.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.techInterview.restWs.exceptions.TreatmentException;
import com.techInterview.restWs.services.NumberTranslateService;
import com.techInterview.restWs.services.ProcessStringService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessControllerTest {
	
	@Mock
	ProcessStringService stringService;
	
	@Mock
	NumberTranslateService translateService;
	
	@InjectMocks
	ProcessController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void stringDedupTest() throws Exception {
		String chaine="testonsunpeu";
		
		String output="enopstu";
		
		when(stringService.uniqueOrderedString(anyString())).thenReturn(output);
		
		mockMvc.perform(get("/stringDedup").
				param("value", chaine))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content",is(output)));
		
		verify(stringService, times(1)).uniqueOrderedString(chaine);
		verifyNoMoreInteractions(stringService);
	}
	
	@Test
	public void stringDedupTest_Exception() throws Exception {
		String chaine="ceciestunechainedelongeursuperieurea30quidevraitrenvoyeruneexception";
		
		assertThatThrownBy(() -> controller.stringDedup(chaine))
			.isInstanceOf(TreatmentException.class)
			.hasMessage("String size must be less than 30");
	}
	
	@Test
	public void stringDedupTest_Exception2() throws Exception {
		String chaine="ceciestunechainedelongeursuperieurea30quidevraitrenvoyeruneexception";
		
		mockMvc.perform(get("/stringDedup")
				.param("value", chaine))
					.andDo(print())
					.andExpect(status().isBadRequest());
		
		verifyZeroInteractions(stringService);

	}
	
	@Test
	public void writeNumberTest() throws Exception {
		Integer value = 255439;
		String translation="Two hundred thousand fixty five thousand four hundred thirty nine";
		
		when(translateService.numberToLetter(anyInt())).thenReturn(translation);
		
		mockMvc.perform(get("/writeNumber")
			.param("value", value.toString()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content", is(translation)));
		
		verify(translateService, times(1)).numberToLetter(value);
		verifyNoMoreInteractions(translateService);
	}
	
	@Test
	public void writeNumberTest_Exception() throws Exception{
		Integer value=123456798;
		
		mockMvc.perform(get("/writeNumber")
			.param("value",value.toString()))
				.andExpect(status().isBadRequest());
		
		verifyZeroInteractions(translateService);
	}
	
	@Test
	public void writeNumberTest_Exception2() {
		Integer value=123456798;
		
		assertThatThrownBy(() -> controller.writeNumber(value))
			.isInstanceOf(TreatmentException.class)
			.hasMessage("Number must be between 0 & 999 999");
	}

}
