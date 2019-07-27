package com.techInterview.restWs.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProcessStringServiceImpl implements ProcessStringService {

	@Override
	public String uniqueOrderedString(String input) {
		StringBuilder sb = new StringBuilder();
		if(!StringUtils.isEmpty(input)){
			char [] chars = input.toCharArray();
			Arrays.sort(chars);
			sb.append(chars[0]);
			for(int i=1; i<chars.length; i++) {
				if(chars[i] != chars[i-1]) {
					sb.append(chars[i]);
				}
			}
			
		}
		return sb.toString();
	}

}
