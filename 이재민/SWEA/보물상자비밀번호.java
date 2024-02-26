import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 덱에다 맨 뒤 값을 맨 앞에 넣고 회전
 * N/4만큼 회전하면 똑같은 값으로 나오기 때문에 N/4만큼만 회전해서 담으면 됨
 * list를 이용해서 값이 들어있나 확인 -> Integer.parseInt(s, 16)으로 16 to 10 변환
 * 27596KB | 196ms
 */
public class 보물상자비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			List<Integer> list = new ArrayList<>();
			Deque<Character> dq = new ArrayDeque<>();

			String line = br.readLine();
			
			for(int i=0; i<N; i++) {
				dq.addLast(line.charAt(i));
			}
			
			StringBuilder s = new StringBuilder();
			// 회전
			// N/4만큼 돌리면 원래 처음 모양이 나옴
			for(int i=0; i<N/4; i++) {
				dq.addFirst(dq.pollLast());
				
				s.setLength(0);
				Deque<Character> copyDq = new ArrayDeque<>();

				// 복사
				copyDq.addAll(dq);
				
				for(int j=1; j<=N; j++) {
					s.append(copyDq.pollFirst().toString());
					if(j%(N/4) == 0) {
						if(!list.contains(Integer.parseInt(s.toString(), 16))) {
							list.add(Integer.parseInt(s.toString(), 16));
						}
						s.setLength(0);
					}
				}
			}
			
			list.sort((a, b) ->b-a);
			sb.append("#" + tc + " " + list.get(K-1)).append('\n');
		}
		System.out.println(sb.toString());
	}

}
