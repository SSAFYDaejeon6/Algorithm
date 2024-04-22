package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *	113284kb 852ms	
 */
public class BJ_G5_5430_AC {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		A: for(int t=1; t<=T;t++) {
			//앞뒤로 빼기 위해 덱큐 사용
			ArrayDeque<Integer> deque = new ArrayDeque<>();

			String input = br.readLine();
			int n = Integer.parseInt(br.readLine());

			//중간중간 다 없애기
			String[] numList = br.readLine().replace("[", "").replace("]", "").split(",");

			if(n!=0) {
				for(int i=0; i<numList.length;i++) {
					deque.offer(Integer.parseInt(numList[i]));
				}
			}
			//false면 앞에서 true면 뒤에서 빼기
			boolean flag = false;
			for(int i=0; i<input.length();i++) {
				char a = input.charAt(i);
				if(a=='D') {
					if(deque.size()>=1) {
						if(!flag) deque.pollFirst();
						else deque.pollLast();
					}
					//사이즈가 0이면 에러 발생
					else {
						sb.append("error").append("\n");
						continue A;
					}
				}
				else flag = !flag;
			}
			
			sb.append("[");
			int size = deque.size();
			for(int i=0; i<size;i++) {
				//false면 앞에서 true면 뒤에서 빼기
				if(!flag) sb.append(deque.pollFirst());
				else sb.append(deque.pollLast());
				if(i!=size-1) {
					sb.append(",");
				}
			}
			sb.append("]");
			sb.append("\n");
			
			
		}
		System.out.println(sb);
	}
}
