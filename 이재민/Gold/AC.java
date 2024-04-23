package algo0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
 * AC는 정수 배열을 연산하기 위한 언어
 * R(뒤집기)와 D(버리기)가 있음
 * 뒤집기는 순서를 뒤집는 함수이고, 버리기는 첫번째 수를 버리는 함수
 * 배열이 비어있는데 D를 사용한 경우 에러가 발생 
 * 
 * 덱을 사용해서 R에 따라 원소를 빼내는 방향을 정하기
 * 108284KB | 780ms 
 */

public class AC {
	static int reverse; // 뒤집은걸 판단하는 변수
	static String command; // 명령
	static int N;
	static Deque<Integer> dq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		dq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		A: for(int tc = 1; tc<=T; tc++) {
			reverse = 1; // 순방향
			command = br.readLine();
			N = Integer.parseInt(br.readLine());
			String[] line = br.readLine().replaceFirst("\\[", "").replaceFirst("\\]", "").split(",");
			for(int i=0; i<N; i++) {
				dq.addLast(Integer.parseInt(line[i]));
			}

			for(int i=0; i<command.length(); i++) {
				if(command.charAt(i) == 'R') reverse = -reverse;
				else {
					if(dq.isEmpty()) {
						sb.append("error").append('\n');
						continue A;
					}
					
					if(reverse == 1) dq.pollFirst();
					else dq.pollLast();
				}
			}
			
			sb.append('[');
			while(!dq.isEmpty()) {
				if(reverse == 1) {
					sb.append(dq.pollFirst());
				}
				else sb.append(dq.pollLast());
				
				if(!dq.isEmpty()) sb.append(',');
			}
			sb.append(']').append('\n');
		}
		System.out.println(sb);
		
	}

}