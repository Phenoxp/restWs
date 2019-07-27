package com.techInterview.restWs.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class NumberTranslateServiceImpl implements NumberTranslateService {
	
	class ConvertInternal{
		String[] firstLevel = {"zero", "one", "two", "three", "four", "five", "six", "seven",
				"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
				"sixteen", "seventeen", "eighteen", "nineteen" };
		String[] secondLevel = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
					"eighty", "ninety"};	 
		String [] thirdLevel = { "hundred","thousands", "million"};
		
		String letterUnder99(int value){
				 if( value < 20)
					 return firstLevel[value];
				 StringBuilder str = new StringBuilder();
				 str.append(secondLevel[(value / 10) - 2]);
				 if( value % 10 == 0)
					 return str.toString();
				 else 
					 return str.append(" ")
							 .append(firstLevel[value % 10])
							 .toString();
			 
		 }
		
		String letterUnder999(int value) {
			StringBuilder sb1 = new StringBuilder(firstLevel[value/100]).append(" ").append(thirdLevel[0]);
			StringBuilder sb2 = new StringBuilder(letterUnder99(value%100)); 
			if(value <= 99) {
				return sb2.toString();
			} else if(value % 100 == 0) {
				return sb1.toString();
			} else {
				return sb1.append(" ").append(sb2.toString()).toString();
			}
		}
		
		String convert(int value) {
			if(value <= 999) {
				return letterUnder999(value);
			}
			StringBuilder sb = new StringBuilder();
			int level = 0;
			while( value > 0 ) {
				if( value % 1000 != 0) {
					StringBuilder sb2 = new StringBuilder(letterUnder999( value % 1000));
					if( level > 0) {
						sb2.append(" ").append(thirdLevel[level]);
					}
					if( sb.length() == 0) {
						sb = sb2;
					} else {
						sb = new StringBuilder(sb2.toString()+ ", "+sb.toString());
					}				
				}
				value = value / 1000;
				level ++;
			}
			return sb.toString();
		}
	}

	@Override
	public String numberToLetter(int value) {
		ConvertInternal converter = new ConvertInternal();
		return StringUtils.capitalize(converter.convert(value));

	}

}
