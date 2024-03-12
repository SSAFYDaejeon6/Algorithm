import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**Solution_7465_창용마을무리의개수_이동준 22,148 kb 142 ms
 * Facts
 * 		전형적인 union-find 문제
 * 		N <= 100 , 
 *Idea
 *프로세스
 *		EdgeList를 만든다.
 *		모든 N명의 사람들을 MakeSet 한다
 *		Edge 순회하면서 Merge 하고
 *		마지막에 Parent가 자기 자신인 사람의 개수가 답이다.
 */

public class Solution_7465_창용마을무리의개수_이동준{
	static BufferedReader br;
	static int T;
	static int N;
	static int M;
	static int[] people;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int eachCase = 1; eachCase <= T; eachCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			people = new int[N];
			for(int i = 0; i < N; i++) {
				people[i] = i;//makeSet
			}
			int a, b;
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken()); b = Integer.parseInt(st.nextToken());
				N = Math.max(N, Math.max(a, b));
				a--; b--; //Index로 바꿔주기
				merge(a, b);
			}
			int resultCount = 0;
			for(int i = 0; i < N; i++) {
				if(people[i] != i) continue;
				resultCount++;
			}
			sb.append('#').append(eachCase).append(' ').append(resultCount).append('\n');
		}
		System.out.print(sb);
	}
	static int findSet(int idx){
		if(people[idx] == idx) return idx;
		return people[idx] = findSet(people[idx]);
	}
	
	static int merge(int aIdx, int bIdx) {
		if(findSet(aIdx) == findSet(bIdx)) return -1;
		people[people[aIdx]] = people[bIdx];
		return people[bIdx];
	}
}