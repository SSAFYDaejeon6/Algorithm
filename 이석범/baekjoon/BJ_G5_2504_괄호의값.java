package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//11592KB 80ms
public class BJ_G5_2504_��ȣ�ǰ� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Deque<Character> stack = new ArrayDeque<>();
		//�´��� �ƴ��� Ȯ�� �뵵
		boolean flag = true; 

		//����
		int answer = 0;
		//���� ���� �뵵
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
					//�� ���� ���ų� (���ƴ� �� ���� �ʴ� ��ȣ�� ���
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
		//������� �ʴ� �͵� ����
		if(!flag || !stack.isEmpty()) {
			System.out.println(0);
		}
		else {
			System.out.println(answer);
		}
	}
}