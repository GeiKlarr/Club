package com.rungroop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);

		boolean isPal = isPalindrome("hanah");
		if (isPal){
			System.out.println("Pal");
		}else {
			System.out.println("not Pal");
		}
	}


	public static boolean isPalindrome(String word) {
		String rev = "";

		for(int x = word.length()-1; x>=0;x--){
			rev += word.charAt(x);
		}

		if(word.equals(rev)){
			System.out.println(rev);
			return true;
		}else {
			return false;
		}
	}





}
