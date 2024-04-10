import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조합 뽑기 -> 백트래킹
 * 	13432KB | 364ms
 */
public class BOJ_N1062_가르침_정재희 {
	static String candidate="";
	static int N, K, size;
	static int maxCnt;
	static char[] picked;
	static boolean[][] parts;
	static String[] strs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		maxCnt = 0;
		if(K < 5) {
			System.out.println(0);
			return;
		}
		
		K -= 5;
		picked = new char[K];
		size = 0;
		String basic = "antic";
		parts = new boolean[N][26];
		strs = new String[N];
		int cnt = 0;
		StringBuilder candidateSB = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			String p = str.substring(4, str.length()-4);
			
			if(p.length() == 0) {
				cnt++;
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < p.length(); j++) {
				String s = String.valueOf(p.charAt(j));
				if(basic.contains(s) || sb.toString().contains(s)) continue;
				if(!candidateSB.toString().contains(s))	candidateSB.append(s);
				sb.append(s);
				parts[size][p.charAt(j)-'a'] = true;
			}
			strs[size++] = sb.toString();
			candidate = candidateSB.toString();
		}
		if(K >= candidate.length()) {
			System.out.println(N);
			return;
		}
		deleteComb(0, 0);
		System.out.println(cnt + maxCnt);
	}
	
	static void deleteComb(int start, int depth) {
		if(depth == K) {
			
			maxCnt = Math.max(maxCnt, delete());
			if(maxCnt == N) {
				System.out.println();
			}
			return;
		}
		
		for (int i = start; i < candidate.length(); i++) {
			picked[depth] = candidate.charAt(i);
			deleteComb(i+1, depth+1);
		}
	}
	
	static int delete() {
		int cnt = 0;

		for (int i = 0; i < size; i++) {
			int len = 0;
			for(int j=0;j<K;j++) {
				if(parts[i][picked[j] - 'a']) len++;
			}
			if(len == strs[i].length()) cnt++;			

		}
		return cnt;
	}

}