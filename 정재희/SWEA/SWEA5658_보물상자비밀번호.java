import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 18,408 kb | 115 ms
 * 
 * 1. 문자열 N/4로 잘라 정수형 변환
 * 2. 문자열 시계 방향 회전
 * 3. 1~2과정 반복
 */
public class SWEA5658_보물상자비밀번호 {
	static int N;
	static int circle;
	static char[] number;
	static List<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			number = br.readLine().toCharArray();
			
			circle = N/4;
			splitNum();  // circle 길이씩 문자열 잘라 정수형으로 변환
			for(int i = 0; i< circle-1;i++) {
				rotateNum();	 // 시계방향으로 회전
				splitNum();
			}
			list.sort(Collections.reverseOrder());  // 내림차순 정렬
			sb.append("#").append(test_case).append(" ").append(list.get(K-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void rotateNum() {
		char temp = number[0];
		for (int j = 1; j < number.length; j++) {
			number[j-1] = number[j];
		}
		number[N-1] = temp;
	}
	private static void splitNum() {
		String n = String.valueOf(number);
		for (int i = 0; i < number.length; i+=circle) {
			int num = Integer.parseInt(n.substring(i, i+circle), 16);
			if(!list.contains(num)) list.add(num);  // 중복되지 않는 경우만 저장
		}
	}

}
