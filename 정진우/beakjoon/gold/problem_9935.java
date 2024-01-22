package com.ssafy.baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class problem_9935 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		Stack<Character> stack = new Stack<>();
				
		String st = br.readLine();
		String boom = br.readLine();
		
		StringBuilder result = new StringBuilder();
		for(int i=0; i<st.length(); i++) {
			char c = st.charAt(i);			
			stack.push(c);
			check(stack, boom);
						
		}
		
		for(Character c : stack) {
			result.append(c);
		}
		System.out.println(result.toString().length() == 0 ? "FRULA" : result.toString());

	}
	
	public static void check(Stack<Character> stack, String boom) {
		if (stack.size() < boom.length()) {
			return;
		}
		
		for(int i=0; i<boom.length(); i++) {
			if(stack.get(stack.size()-boom.length()+i) != boom.charAt(i)) {
				return;
			}
		}
		
		for(int i=0; i<boom.length(); i++) {
			stack.pop();
		}
		
	}

}
