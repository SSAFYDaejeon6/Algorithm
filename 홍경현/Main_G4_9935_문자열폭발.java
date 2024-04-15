package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/* 87932kb 464ms
 * [문제해석]
 	폭발 문자열이 폭발하면 그 문자는 문자열에서 사라지며 남은 문자열은 합쳐지게 됨
	폭발 과정
		- 문자열이 폭발 문자열을 포함하고 있는 경우 모든 폭발 문자열이 폭발
			남은 문자열 순서대로 이어 붙여 새로운 문자열 생성
		- 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있음
		- 폭발은 폭발 문자열이 문자열에 없을 때까지 계속됨
	모든 폭발이 끝난 후 어떤 문자열이 남는지 구해보려 한다
	남아있는 문자가 없으면 "FRULA" 출력
	폭발 문자열은 같은 문자를 두 개 이상 포함하지 않음
	
	[입력]
	1. 문자열 1<=S1<=1,000,000
	2. 폭발 문자열 1<=S2<=36
	3. 알파벳 소문자와 대문자, 숫자로 이루어짐
	
	[출력]
	남은 문자열 출력
 */
public class Main_G4_9935_문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String tmp = br.readLine();
		int len = tmp.length();
		
		/* 처음 시도한 방법
		 * 4자리씩 비교 -> 메모리 초과ㅠㅠ
			boolean check = true;
			while(check) {
				check = false;
				int i = 0;
				int j = 0;
				while(true) {
					if(j == tmp.length()) {
						str = str.substring(0, i-j) + str.substring(i);
						check = true;
						break;
					}
					if(i == str.length()) break;
					if(str.charAt(i)==tmp.charAt(j)) {
						i++;
						j++;
						continue;
					}
					
					i = i-j+1;
					j = 0;
				}
			}
		 */
		
		Stack<Character> st = new Stack<>();
		
		for(int i=0; i<str.length();i++) {
			st.push(str.charAt(i));
			
			if(st.size()>=len) {
				boolean check = true;
				for(int j=0; j<len; j++) {
					if(st.get(st.size()-len+j)!=tmp.charAt(j)) {
						check = false;
						break;
					}
				}
				
				if(check) {
					for(int j=0; j<len; j++) st.pop();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(Character c : st) sb.append(c);
		
		System.out.println(sb.length()==0?"FRULA":sb);
	}

}
