package test;

import java.util.Scanner;
import java.util.Stack;

//31940kb 552ms
public class BJ_G4_16120_PPAP {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		
		//중간 접근 하기 위해 stack으로 하기
		Stack<Integer> stack = new Stack<>();
//		Deque<Integer> stack1 = new ArrayDeque<>();
		
		for(int i=0; i<input.length();i++) {
			int text = input.charAt(i);
			
			//A일 경우 스택에 넣기
			if(text=='A') {
				stack.push(text);
			}
			//'P' 일 경우
			else {
				int size = stack.size();
				//앞에 'A'이고 'PPA' 인 경우 'A', 'P' 두개만 빼면 됨
				if(size>=3 && stack.peek()=='A' ) {
					if(stack.get(size-2) == 'P' && stack.get(size-3)=='P') {
						for(int j=0; j<2;j++) {
							stack.pop();
						}
					}
				}
				else {
					stack.push(text);
				}
			}
		}
		
		//마지막 출력 해보기, P하나만 있어야함
		//stack.forEach(System.out::println);
		
		if(stack.size()==1 && stack.peek()=='P') {
			System.out.println("PPAP");
		}
		else {
			System.out.println("NP");
		}
	}
}
