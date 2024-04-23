import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [BOJ] 5430 AC
// 80440KB	|	580ms
// start, end index 조정
public class B5430 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		A : for (int i = 0; i < T; i++) {
			String command = br.readLine();
			int N = Integer.parseInt(br.readLine());
			String num = br.readLine();
			num = num.substring(1, num.length() - 1);
			String[] arr = num.split(",");
      
			int start = 0;
			int end = N - 1;
		
			for (int c = 0; c < command.length(); c++) {
				if (command.charAt(c) == 'R') {
          // end, start swap
					int temp = end;
					end = start;
					start = temp;
				}
				if (command.charAt(c) == 'D') {
					if(N == 0) {  // 빈 배열인데 D 명령어 수행하려 할 때
						sb.append("error").append('\n');
						continue A;
					}
					if(end < start) {
						start -= 1;
					}
					if(end > start) {
						start += 1;
					}
					N -= 1;  //배열 사이즈 줄이기
					
				}

			}
			
			//결과 출력
			sb.append('[');
			if(N == 0) {
				sb.append(']').append('\n');
				continue;
			}
			if(end >= start) {
				for(int j = start; j <= end; j++) {
					sb.append(arr[j]).append(',');
				}
			}
			
			if(end < start) {
				for(int j = start; j >= end; j--) {
					sb.append(arr[j]).append(',');
				}
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(']').append('\n');

		}
		System.out.println(sb);
	}
}
