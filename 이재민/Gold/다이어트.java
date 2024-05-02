package algo0430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 부분집합으로 모든 경우의 수를 구하는데
 * 가지치기도 같이 실행
 * 13604KB | 88ms
 */
public class 다이어트 {
	static int N, mMp, mMf, mMs, mMv;
	static food[] f;
	static boolean[] isSelected;
	static int res;
	static List<Integer> list;

	static class food {
		int mp, mf, ms, mv, price;
		// 단백질, 지방, 탄수화물, 비타민

		public food(int mp, int mf, int ms, int mv, int price) {
			this.mp = mp;
			this.mf = mf;
			this.ms = ms;
			this.mv = mv;
			this.price = price;
		}

	}

	static void subset(int cnt, int mp, int mf, int ms, int mv, int price) {
		// 가지치기
		if (price >= res) {
			return;
		}
		// 가지치기, 위 코드에서 여기로 넘어와서 실행되면 최소값이 갱신이 되고
		// 다음꺼를 탐색할 필요가 없음
		if (mp >= mMp && mf >= mMf && ms >= mMs && mv >= mMv) {
			list.clear();
			for (int i = 0; i < N; i++) {
				if (isSelected[i])
					list.add(i + 1);
			}
			res = price;
			return;
		}

		if (cnt == N)
			return;

		isSelected[cnt] = true;
		subset(cnt + 1, mp + f[cnt].mp, mf + f[cnt].mf, ms + f[cnt].ms, mv + f[cnt].mv, price + f[cnt].price);
		isSelected[cnt] = false;
		subset(cnt + 1, mp, mf, ms, mv, price);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		f = new food[N];
		isSelected = new boolean[N];
		res = Integer.MAX_VALUE;
		list = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		mMp = Integer.parseInt(st.nextToken());
		mMf = Integer.parseInt(st.nextToken());
		mMs = Integer.parseInt(st.nextToken());
		mMv = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int mp = Integer.parseInt(st.nextToken());
			int mf = Integer.parseInt(st.nextToken());
			int ms = Integer.parseInt(st.nextToken());
			int mv = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			f[i] = new food(mp, mf, ms, mv, price);
		}

		subset(0, 0, 0, 0, 0, 0);
		if (res != Integer.MAX_VALUE) {
			System.out.println(res);
			for(int i=0; i<list.size(); i++) System.out.print(list.get(i) + " ");
		}
		else {
			System.out.println(-1);
		}
	}
}
