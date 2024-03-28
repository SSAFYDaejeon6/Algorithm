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
 *비트마스킹을 활용하면 1/10로 줄일 수 있음
 *answer = 1<<a, n, t, i, c로 하교 비교시에 &비교하면 빠를듯
 *
 *11956kb 344ms
 */
public class BJ_G4_1062_가르침_비트마스킹 {

	static int[] inputList;

	static int n, k;

	//	static int[] isSelect;



	static int flag;

	static int maxValue = 0;

	static void comb(int cnt, int idx, int isSelect) {

		if(cnt==k) {
			if((flag & isSelect) == flag) {
				isCheck(isSelect);
			}
			return;
		}

		for(int i=idx; i<26;i++) {
			comb(cnt+1, i+1, isSelect|(1<<i));
		}

	}

	static void isCheck(int num) {
		int cnt = 0;
		for(int i=0; i<n;i++) {
//			int check = (inputList[i] & num);
//			if((inputList[i] & check) == check) {
//				cnt++;
//			}
			if((inputList[i] & num)==inputList[i] ) {
				cnt++;
			}
			
		}
		maxValue = Math.max(maxValue, cnt);
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());


		inputList = new int[n];

		int[] isNeed = {'a','n','t', 'i', 'c'};
		for(int i=0; i<5;i++) {
			flag |= 1<<(isNeed[i] - 'a');
		}

		for(int i=0; i<n;i++) {
			String tmp = br.readLine();
			for(int j=0; j<tmp.length();j++) {
				int idx = tmp.charAt(j) - 'a';
				if((inputList[i] & (1<<idx))==0) {
					inputList[i] |= 1<<idx;
				}
			}
		}

		if(k < 5) {
			System.out.println(0);
			return;
		}
		comb(0, 0, 0);
		System.out.println(maxValue);


	}
}
