package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 *
 *최대 21C10 ==> 35만
 *antarctica
 *
 *a, n, t, i, c는 이미 존재
 *31232kb 1000ms
 *비트마스킹을 활용하면 1/10로 줄일 수 있음
 *answer = 1<<a, n, t, i, c로 하교 비교시에 &비교하면 빠를듯
 */
public class BJ_G4_1062_가르침 {
	
	static boolean[][] check;
	
	static int n, k;
	
	static int[] isSelect;
	
	static int[] isNeed = {'a','n','t', 'i', 'c'};
	
	static int maxValue = 0;
	
	static void comb(int cnt, int idx) {
		
		if(cnt==k) {
			isCheck();
			return;
		}
		
		for(int i=idx; i<26;i++) {
			isSelect[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}
	
	static void isCheck() {
		int flag = 0;
		for(int i=0; i<isSelect.length;i++) {
			for(int j=0; j<isNeed.length;j++) {
				if(isSelect[i] == (isNeed[j]-'a')) {
					flag++;
				}
			}
		}
		if(flag!=5) {
			return;
		}
		
		int result = 0;
		
		boolean[] ans = new boolean[26];
		
		for(int i=0; i<k;i++) {
//			System.out.print(isSelect[i]+" ");
			ans[isSelect[i]] = true;
		}
		
		A: for(int i=0; i<n;i++) {
			for(int j=0; j<26;j++) {
				if(check[i][j]) {
					if(!ans[j]) continue A;
				}
			}
			result++;
		}
//		System.out.println(result);
		
		maxValue = Math.max(maxValue, result);
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		

		check = new boolean[n][26];
		
		isSelect = new int[k];
		
		for(int i=0; i<n;i++) {
			String tmp = br.readLine();
			for(int j=0; j<tmp.length();j++) {
				int idx = tmp.charAt(j) - 'a';
				check[i][idx] = true;
			}
		}

		if(k < 5) {
			System.out.println(0);
			return;
		}
//		print();
		comb(0, 0);
		System.out.println(maxValue);
		
		
	}
	
	static void print() {
		for(int r=0; r<n;r++) {
			for(int c=0; c<26;c++) {
				System.out.print(check[r][c]? (1+" "):(0+" "));
			}
			System.out.println();
		}
	}
}
