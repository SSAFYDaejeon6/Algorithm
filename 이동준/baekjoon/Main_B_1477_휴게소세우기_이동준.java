import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1477 휴게소 세우기 18372KB 244ms
 * 
 * 현재 있는 휴게소는 N개 0부터최대 50 지을 건 1부터 100개
 * 
 * IDEA 완탐 1000C100 -> 불가
 * 
 * 조건을 만족하는 경우는 여러 개이다.
 * 없는 구간이 현재 최대구간보다 작은 점들을 마구 움직여도 되기 때문이다. 
 * 휴게소를 건설할 때, 무조건 현재 최대구간 안에 건설해야 한다.
 * 		그렇지 않으면 최대구간이 변하지 않기 때문이다. 
 * M번째 휴게소가 있을 때, 그것은 무조건 M-1의 최대구간 안에 존재한다. 
 * 		그렇지 않으면 M의 최대구간 길이와 M-1의 최대구간 길이가 똑같아지기 때문. 
 * M 번째 휴게소를 놓았을 때의 최대 구간은 M번째 휴게소를 포함하지 않을 수 있다.
 * M 번째 휴게소를 놓은 경우를 생각해 보자. 그 결과 M 번째에서 최대 구간은 둘 중 하나다.
 * 	Case 1: M - 1번째 최대 구간의 절반
 *		번째의 최대 구간은 최소여야 한다
 *	Case 2: M - 1 번째의 두 번째 구간
 *		두 경우 모두 어찌되었든 M - 1번째 구간을 절반으로 나눠야 함.
 *이렇게 안해도 된다?? 
 *	1. 최대구간을 처리해야 한다
 *	2. 처리한 결과가 모두 현재 두번째 구간보다 같거나 작아야 한다 이것을 최소한의 휴게소로 하는
 * 방법은? 최대구간 / 2번째 구간 만큼 현재 최대구간을 나누는 거다 그렇다면 이 작업이 불가능하다면?(휴게소 부족 등) 그때부터는 절반으로
 * 나눈다
 *이것도 틀려?
 *	그러면 그냥 Target Span을 쑤셔넣는 방법으로 시도하자..
 * 
 */
public class Main_B_1477_휴게소세우기_이동준 {
	static int N, M, L;
	static StringTokenizer st;
	static BufferedReader br;
	static List<Integer> spaces;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int[] points = new int[N + 2];
		points[N + 1] = L;// 마지막 점
//		points[0] = 0;
		if (N > 0)
			st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(points);
		spaces = new ArrayList<>();
		for (int i = 1; i < N + 2; i++) {
			spaces.add(points[i] - points[i - 1]);
		}
		Collections.sort(spaces, (a, b) -> (Integer.compare(b, a)));
		int maxSpan;
		int targetSpan = 1;
		search : for(; targetSpan < spaces.get(0); targetSpan++) {
			List<Integer> temp_spaces = new ArrayList<>(spaces);
			maxSpan = temp_spaces.remove(0);
			bsAdd(temp_spaces, maxSpan - targetSpan);
			bsAdd(temp_spaces, targetSpan);
			for (int i = 1; i < M; i++) {
				maxSpan = temp_spaces.remove(0);
				if(maxSpan > targetSpan) {
					bsAdd(temp_spaces, maxSpan - targetSpan);
					bsAdd(temp_spaces, targetSpan);			
				}else {
					if(maxSpan == 1) continue search;
					bsAdd(temp_spaces, maxSpan / 2);
					bsAdd(temp_spaces, maxSpan - maxSpan / 2);		
				}
			}
			if(temp_spaces.get(0) == targetSpan) break search;
		}
		System.out.println(targetSpan);
	}

	private static void bsAdd(List<Integer> list, Integer toAdd) {
		if (toAdd == 0)
			return;
		if (list.size() == 0) {
			list.add(toAdd);
			return;
		}
		int idx = Collections.binarySearch(list, toAdd, (a, b) -> (Integer.compare(b, a)));
		if (idx < 0)
			idx = ~idx;
		list.add(idx, toAdd);
	}
}
