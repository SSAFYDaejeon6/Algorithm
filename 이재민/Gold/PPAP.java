package algo0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * P의 개수를 저장하고
 * A가 나왔을때 P의 개수가 2개 이상이고
 * 다음 문자가 P이면 PPAP 문자열 완성
 * 
 * A가 나왔을때 조건에 충족하지 않으면 PPAP를 만들 수 없음
 * 20136KB | 168ms
 */
public class PPAP {
	static int p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'P') p++;
			else if (p >= 2 && i < str.length() -1 && str.charAt(i + 1) == 'P') {
				p--;
				i++;
			} else {
				System.out.println("NP");
				return;
			}
		}

		if (p == 1) {
			System.out.println("PPAP");
		} else {
			System.out.println("NP");
		}
	}
}
