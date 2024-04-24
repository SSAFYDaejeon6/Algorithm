import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 80,300KB | 612ms
 * start, end 포인터 이용
 */
public class BOJ_N5430_AC {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		test: for (int test_case = 1; test_case <= T; test_case++) {
			String command = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			
			String arr = br.readLine();
			String[] num = arr.substring(1, arr.length()-1).split(",");			
			
			int[] pointer = new int[3]; // 0: start, 2: end
			int current = -1; //-1:left, 1:right
			pointer[2] = n;
			
			for (int i = 0; i < command.length(); i++) {
				char c = command.charAt(i);
				switch (c) {
				case 'R':  // 회전
					current = -current;
					break;

				case 'D':  // 현재 방향에서 요소 삭제
					pointer[current+1] -= current;
					if(pointer[0] > pointer[2]) {
						sb.append("error").append("\n");
						continue test;
					}
					break;
				}
			}
			
			// 원소가 하나도 없는 경우
			if(n==0 || pointer[0] == pointer[2]) {
				sb.append("[").append("]").append("\n");
				continue;
			}
			
			sb.append("[");
			if(current == -1) {
				for (int i = pointer[0]; i < pointer[2]-1; i++) {
					sb.append(num[i]).append(",");
				}
				sb.append(num[pointer[2]-1]).append("]").append("\n");
			}
			else {
				for (int i = pointer[2]-1; i > pointer[0]; i--) {
					sb.append(num[i]).append(",");					
				}
				sb.append(num[pointer[0]]).append("]").append("\n");
			}
			
		}
		System.out.println(sb);
	}

}
