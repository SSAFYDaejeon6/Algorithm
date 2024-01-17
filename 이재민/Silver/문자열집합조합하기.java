import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

	static Map<String, Integer> m = new HashMap<>();

	static void bit(String s, int n) {
		int size = s.length();

		for (int i = 1; i < (1 << size); i++) { // i << n => 2^n
			if (Integer.bitCount(i) == n) { // bit에서 1 개수
				String str = "";
				for (int j = 0; j < size; j++) {
					if ((i & (1 << j)) != 0) {
						str += s.charAt(j);
					}
				}
				if (!str.isEmpty()) {
					if (m.containsKey(str)) {
						m.put(str, m.get(str) + 1);
					} else {
						m.put(str, 1);
					}
				}
			}
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

		bit(x, n);
		bit(y, n);
		bit(z, n);
		List<String> res = new LinkedList<String>();
		m.forEach((key, value) -> {
			if (value >= 2) {
				res.add(key);
			}
		});

		if (res.size() == 0)
			System.out.println(-1);

		else {
			res.sort(Comparator.naturalOrder());

			res.forEach((str) -> {
				System.out.println(str);
			});
		}
	}
}
