package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//11592KB 80ms
public class BJ_G5_2504_괄호의값 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Deque<Character> stack = new ArrayDeque<>();
		//맞는지 아닌지 확인 용도
		boolean flag = true; 

		//정답
		int answer = 0;
		//숫자 저장 용도
		int cnt =1;
		for(int i=0; i<input.length(); i++) {
			char cur = input.charAt(i);
			if(cur == '(') {
				stack.push(cur);
				cnt *= 2;
			}
			else if(cur == '[') {
				stack.push(cur);
				cnt *= 3;
			}
			else {
				if(cur == ')') {
					//맨 위가 없거나 (가아닌 즉 맞지 않는 괄호일 경우
					if(stack.isEmpty() || stack.peek() != '(') {
						flag=false;
						break;
					}
					if(input.charAt(i-1) =='(') {
						answer += cnt;
					}
					stack.pop();
					cnt /= 2;
				}else if(cur==']') {
					if(stack.isEmpty() || stack.peek() != '[') {
						flag=false;
						break;
					}
					if(input.charAt(i-1)=='[') {
						answer += cnt;
					}
					stack.pop();
					cnt /= 3;
				}
				else {
					flag = false;
					break;
				}
			}
		}
		//비어있지 않는 것도 포함
		if(!flag || !stack.isEmpty()) {
			System.out.println(0);
		}
		else {
			System.out.println(answer);
		}
	}
}