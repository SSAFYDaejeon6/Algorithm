import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 26개의 알파벳중 조합을 뽑아서 비교하는 문제
 * anta와 tica에서 알파벳을 사용하는 개수는 a n t i c 5가지임
 * K가 5보다 작으면? 애초에 antatica도 못읽음
 * K가 26이면? 알파벳 총 숫자만큼 배웠기 때문에 어떠한 글자라도 읽기 가능
 * 13528KB | 260ms
 */
public class 가르침 {
	static int N, K;
	static boolean[] learned;
	static List<String> list;
	static int res;

	static void combi(int cnt, int idx) {
		if (cnt == K - 5) {
			int countRes = 0;
			A: for (int i = 0; i < list.size(); i++) {
				String str = list.get(i);
				for (int j = 0; j < str.length(); j++) {
					if (!learned[str.charAt(j) - 'a']) {
						continue A;
					}
				}
				countRes++;

			}
			res = Math.max(res, countRes);
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (learned[i])
				continue;
			learned[i] = true;
			combi(cnt + 1, i + 1);
			learned[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		}

		list = new ArrayList<>();
		learned = new boolean[26];
		learned['a' - 'a'] = true;
		learned['n' - 'a'] = true;
		learned['t' - 'a'] = true;
		learned['i' - 'a'] = true;
		learned['c' - 'a'] = true;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			str = str.substring(4, str.length() - 4);
			list.add(str);
		}

		combi(0, 0);
		System.out.println(res);
	}

}
