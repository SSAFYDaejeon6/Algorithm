package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *11628kb 84ms
 */
public class BJ_G4_1043_거짓말 {

	static int N;
	static int Party;

	static List<Integer>[] partyList;
	static int[] parent;
	static boolean[] trueCheck;


	static int find(int x) {
		if(parent[x] != x) parent[x] = find(parent[x]);

		return parent[x];
	}

	/**
	 * 둘중에 하나가 진실을 알 경우 부모를 갱신
	 * 아닐경우도 둘중에 하나 갱신
	 */
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);

		if(trueCheck[pA]) {
			parent[pB] = pA;
			trueCheck[b] = true;
		}
		else if(trueCheck[pB]) {
			parent[pA] = pB;
			trueCheck[a] = true;
		}
		else {
			parent[pA] = pB;
		}
 
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int party = Integer.parseInt(st.nextToken());
		trueCheck = new boolean[N+1];
		parent = new int[N+1];
		for(int i=1; i<N+1;i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int manNumber = Integer.parseInt(st.nextToken());
		
		
		boolean flag= false;
		if(manNumber==0) flag = true;

		for(int i=0; i<manNumber;i++) {
			int idx = Integer.parseInt(st.nextToken());
			trueCheck[idx] = true;
		}

		partyList = new List[party];
		for(int i=0; i<party;i++) {
			partyList[i] = new ArrayList<>();
		}

		for(int p=0; p<party;p++) {
			st = new StringTokenizer(br.readLine());
			manNumber = Integer.parseInt(st.nextToken());
			
			//여기서 1개씩 들어올때마다 union해야함
			for(int n=0; n<manNumber;n++) {
				int idx = Integer.parseInt(st.nextToken());
				for(int i=0; i < partyList[p].size();i++) {
					union(idx, partyList[p].get(i));
				}
				partyList[p].add(idx);
			}
		}
//		System.out.println(Arrays.toString(trueCheck));

		//초기 진실 아는 사람 0인 경우 파티 수 출력
		if(flag) System.out.println(party);
		
		else {
			//진실을 모르는 사람들만 있는 파티 세기
			int result = 0;
			A: for(int p=0; p<party;p++) {
				for(int n=0; n<partyList[p].size() ;n++) {
					int idx = partyList[p].get(n);
					if(trueCheck[find(idx)]) continue A;
				}
				result++;
			}
			System.out.println(result);
		}

	}
}
