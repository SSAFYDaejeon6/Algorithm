import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 문자열집합조합하기 {

	static Map<String, Integer> m = new HashMap<>();
	static List<String> list = new LinkedList<>();

	static void dfs(String s, int idx, int cnt) {
		if (cnt == 0) {
			String str = String.join("", list);
//			System.out.println(str);
			if (m.containsKey(str)) {
				m.put(str, m.get(str) + 1);
			} else {
				m.put(str, 1);
			}
			return;
		}

		for (int i = idx; i < s.length(); i++) {
			list.add(Character.toString(s.charAt(i)));
			dfs(s, i + 1, cnt - 1);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String x = br.readLine();
		String y = br.readLine();
		String z = br.readLine();

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		dfs(x, 0, n);
		list.clear();
		dfs(y, 0, n);
		list.clear();
		dfs(z, 0, n);
		list.clear();

		List<String> res = new LinkedList<>();
		m.forEach((key, value) -> {
			if (value >= 2)
				res.add(key);
		});

		if (res.size() == 0) {
			System.out.println(-1);
		} else {
			res.sort(Comparator.naturalOrder());

			for (String output : res) {
				System.out.println(output);
			}
		}

	}
}
