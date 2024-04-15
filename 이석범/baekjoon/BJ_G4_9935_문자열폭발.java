package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *38320kb 1104ms
 *
 * 문자열을 비교하면서 할 경우 시간초과 뜸
 *스택을 이용해야함
 */
public class BJ_G4_9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//기존 텍스트
		String text = st.nextToken();
		st = new StringTokenizer(br.readLine());
		
		//체크해야할 텍스트
		String check = st.nextToken();
		
		//각각의 길이
		int textLen = text.length();
		int checkLen = check.length();
		
		//스택으로 하는 이유는 중간에서 get으로 체크해야하므로 deque대신 사용
		Stack<Character> stack = new Stack<>();

		for(int i=0; i<textLen;i++) {
			//매칭이된 수 카운트
			int cnt = 0;
			stack.push(text.charAt(i));
			
			//사이즈가 체크길이보다 같거나 크면 다음을 봄
			if(stack.size()>=checkLen) {
				
				//만약 스택에서 맨위에서 문자열 길이만큼 보면서 같으면 cnt++
				for(int j=0; j<checkLen;j++) {
					if(stack.get(stack.size()-checkLen + j) == check.charAt(j)) {
						cnt++;
					}
					//만약 같으면 그 것들 다 버리기
					if(cnt==checkLen) {
						for(int k=0; k<checkLen;k++) {
							stack.pop();
						}
					}
				}
			}
		}
		
		//비어있으면 frula반환
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		//아니면 스택에서 출력
		else {
			StringBuilder sb = new StringBuilder();
			for(char ch: stack) {
				sb.append(ch);
			}
			System.out.println(sb);
		}
	}
}
