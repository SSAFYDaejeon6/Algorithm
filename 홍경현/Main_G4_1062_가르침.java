package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 14128kb 304ms
 * [문제 해석]
	k개의 글자로만 이루어진 단어만 읽을 수 있음
	학생들이 읽을 수 있는 단어의 개수가 최대가 되는 k개의 글자
	남극 언어의 모든 단어는 anta로 시작되고 tica로 끝남
	남극 단어는 N개밖에 없다고 가정
	읽을 수 있는 단어 최댓값
	
	[입력]
	1. N K (1<=N<=50, 0<=K<=26)
	2. 남극 언어의 단어 (소문자) : 8<=len<=15
	
	[출력]
	단어 개수의 최댓값
 */
public class Main_G4_1062_가르침{
	static int N, K, result, max;
	static int[] cnt = new int['z'+1]; //배워야 하는 글자
	static boolean[] check = new boolean[26]; //배울 글자
	static String word[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K < 5) {
			System.out.println(0);
			return;
		}
		
		//필수로 들어가는 단어
		check['a'-'a'] = true;
		check['c'-'a'] = true;
		check['i'-'a'] = true;
		check['n'-'a'] = true;
		check['t'-'a'] = true;
		
		word = new String[N];
		
		for(int i=0; i<N; i++) {
			word[i] = br.readLine();
			//배워야 할 글자를 배열에 저장함
			for(int j=4; j<word[i].length()-4; j++) {
				char c = word[i].charAt(j);
				if(c=='a'||c=='c'||c=='i'||c=='n'||c=='t') continue;
				cnt[c-'a']++;
				if(cnt[c-'a'] == 1) max++; //배워야 할 글자의 개수
			}
		}
		comb(5, 0);
		System.out.println(result);
	}

	private static void comb(int idx, int start) {
		//배워야 할 모든 글자를 선택했거나 배워야 할 글자의 수에 도달했으면 읽을 수 있는 단어 count
		if(idx == K || idx == 5+max) {
			int res = count();
			result = Math.max(res, result);
			return;
		}
		
		for(int i=start; i<cnt.length; i++) {
			if(cnt[i] == 0) continue;
			check[i] = true;
			comb(idx+1, i+1);
			check[i] = false;
		}
	}

	private static int count() {
		int count = 0;
		for(int i=0; i<N; i++) {
			boolean b = true;
			for(int j=4; j<word[i].length()-4; j++) {
				char c = word[i].charAt(j);
				if(!check[c-'a']) b = false;
			}
			if(b) count++;
		}
		
		return count;
	}

}
