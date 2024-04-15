package algo0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 폭발하는 문자열 중 마지막을 char형으로 저장
 * 문자열 탐색중 폭발하는 문자열의 마지막 글자랑 똑같으면
 * 그만큼 뽑아내서 비교하기
 * 폭발 문자열이 아니면 다시 스택에 넣음
 * 
 * 111448KB | 576ms
 */

public class 문자열폭발 {

	static String str, explosion;
	static Stack<Character> st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		explosion = br.readLine();

		st = new Stack<>();

		StringBuilder sb = new StringBuilder();
		char lastExp = explosion.charAt(explosion.length() - 1);
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != lastExp) {
				st.add(str.charAt(i));
			} else {
				sb.setLength(0);
				sb.append(lastExp);
				for (int j = 0; j < explosion.length() - 1; j++) {
					if (!st.isEmpty()) {
						sb.append(st.pop());
					}
				}
				if (!explosion.equals(sb.reverse().toString())) {
					for (int j = 0; j < sb.length(); j++) {
						st.add(sb.charAt(j));
					}
				}
			}
		}

		if (st.isEmpty()) {
			System.out.println("FRULA");
			return;
		}
		sb.setLength(0);

		while (!st.isEmpty()) {
			sb.append(st.pop());
		}
		System.out.println(sb.reverse());
	}

}
