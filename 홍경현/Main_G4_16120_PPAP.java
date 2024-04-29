package algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/* 24904kb 256ms
 * [문제 해석]
	PPAP 문자열
	- p는 PPAP 문자열이다
	- PPAP 문자열에서 p 하나를 PPAP로 바꾼 문자열은 PPAP 문자열이다.
	
	[입력]
	1. 문자열 <=1,000,000
	
	[출력]
	PPAP
	NP
 */
public class Main_G4_16120_PPAP {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character> s = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			
			if(c=='P') s.push(c);
			//c=='A'일 때, P가 2개이상 있고, 뒤가 P인 경우 (=PPAP)
			else if (s.size()>=2 && i+1 < str.length() && str.charAt(i+1)=='P') {
				s.pop();
				s.pop();
			}
			else {
				System.out.println("NP");
				return;
			}
		}
		
		//PP는 PPAP가 아니다.
		if(s.size()==1)System.out.println("PPAP");
		else System.out.println("NP");

	}

}
