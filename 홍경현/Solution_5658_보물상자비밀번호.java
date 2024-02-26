package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 18,860 kb 130 ms
 * 문제 해석
	16진수 숫자가 적힌 보물상자
	뚜껑 → 시계 방향, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전
	
	각 변에 동일한 개수의 숫자, 시계방향 순으로 높은 자리 숫자에 해당
	
	보물 상자 자물쇠 비밀번호: 보물 상자에 적힌 숫자로 만들 수 있는 수 중 K번쨰로 큰 수 (10진수)
	N개의 숫자, 보물 상자의 비밀 번호 출력
	
	입력
	1. T
	2. N, K
	3. N
	
	출력
	#t result
 */
public class Solution_5658_보물상자비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t<=T; t++) {
			Set<String> set = new HashSet<>(); //중복을 허락하지 않고 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			char[] cha = new char[N];
			cha = br.readLine().toCharArray();
			
			String str = "";
			int j=0;
			for(int k=0; k<N/4; k++) { //한 변에 들어올 수 있는 숫자만큼 반복문들 돌림
				for(int i=0; i<N; i++) {
					str += cha[i];
					j++;
					if(j==N/4) { //한 변의 길이만큼 str을 저장했다면 set에 담고 변수 초기화
						set.add(str);
						str = "";
						j = 0;
					}
				}
				char c = cha[N-1];
				System.arraycopy(cha, 0, cha, 1, N-1); //배열돌리기 
				cha[0] = c;
			}
			
			List<String> list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder()); //내림차순 정렬
			sb.append('#').append(t).append(" ")
			.append(Integer.parseInt(list.get(K-1), 16)).append('\n');
		}
		System.out.println(sb);
	}

}
