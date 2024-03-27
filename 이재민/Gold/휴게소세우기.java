import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 이분 탐색
 * 짜놓고 값이 +1씩 더 나오는 예제가 있어서 고생했음
 * 11636KB | 84ms
 */
public class 휴게소세우기 {

	static int N, M, L;
	static List<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		
		
		list.add(0);
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		list.add(L);
	
		Collections.sort(list);
		
		int start = 1;
		int end = L-1;
		while(start <= end) {
			int mid = (start + end) / 2;
			int count = 0;
			for(int i=1; i<list.size(); i++) {
				int d = list.get(i) - list.get(i-1);
				count += (d / mid);
				
				// 값이 1씩 더 크게 나오는 부분
				if(d % mid == 0) count--;
			}
			
			if(count > M) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(start);
	}

}
