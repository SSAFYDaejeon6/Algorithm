/**Solution_5658_보물상자비밀번호_이동준 27,152 kb 171 ms
 * Facts
 * 	N은 4의 배수이다
 * 	N개의 나열된 수를 원형으로 이어붙인 뒤 4등분해 만든 모든 수 중 K번째로 큰 수를 구해야 함.
 * 	16진수는 4bit. 정수는 4bytes 이므로 overflow는 일어나지 않는다.
 * Idea
 * 	문제를 두 단계로 나눌 수 있다.
 * 	수 구하기, 그리고 정렬시키기.
 * 	List를 이용해 정렬시킨다. 이때 input 과정을 정렬시켜서 중복 원소 체크와 원소 더하기를 동시에 한다.
 *  
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5658_보물상자비밀번호_이동준 {
	static BufferedReader br;
	static int T, N, K;
	static int subLength;
	static String wholeStr;
	static StringBuilder sb;
	static List<Integer> candidates;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
//			br = new BufferedReader(new FileReader("./swea/5658.txt"));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int eachCase = 1; eachCase <= T; eachCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			candidates = new ArrayList<>();
			subLength = N / 4;
			wholeStr = br.readLine();
			wholeStr = wholeStr.trim();
			wholeStr = wholeStr + wholeStr;//두배 만들기
			int newOne;
			for(int cursor = 0; cursor < subLength; cursor++) {
				for(int i = 0; i < 4; i++) {
					newOne = Integer.parseInt(wholeStr.substring(cursor + i * subLength, cursor + (i + 1) * subLength), 16);
					addDescending(newOne);
				}
			}
			sb.append('#').append(eachCase).append(' ').append(candidates.get(K - 1)).append('\n');
		}
		System.out.print(sb);
	}

	static public boolean addDescending(Integer element) {//내림차순 정렬
		int point = Collections.binarySearch(candidates, element, (o1, o2) -> Integer.compare(o2, o1));
		if(point >= 0) return false;//이미 있음
		point = ~point;
		candidates.add(point, element);
		return true;
	}
}
