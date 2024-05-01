package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *14852kb	96ms
 */
public class BJ_G4_19942_다이어트 {

	static int N;
	static class Essential {
		int p;
		int f;
		int s;
		int v;
		int price;


		public Essential() {
		}

		public Essential(int p, int f, int s, int v, int price) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.price = price;
		}
		@Override
		public String toString() {
			return "Essential [p=" + p + ", f=" + f + ", s=" + s + ", v=" + v + ", price=" + price + "]";
		}

		public Essential add(Essential e) {
			Essential output = new Essential(this.p + e.p, this.f + e.f, this.s + e.s, this.v + e.v, this.price + e.price);
			return output;
		}

		public boolean check() {
			if(this.p >= mp && this.f >= mf && this.s >= ms && this.v >= mv) return true;
			return false;
		}

	}

	static Essential[] list;
	//단, 지, 탄 비
	static int mp, mf, ms, mv;

	static int result;
	static int count;

	static List<String> combList = new ArrayList<>();

	static void comb(int idx, Essential total, boolean[] checked, int cnt) {

		if(idx==N) {
			//			System.out.println(Arrays.toString(checked));
			if(!total.check()) return;
			if(result >= total.price) {
				//				System.out.println(Arrays.toString(checked));
				//만약 같으면 string값 넣어서 사전순으로 비교할 수 있게 하기
				if(result == total.price) {
					String tmp ="";
					for(int i=0; i<N;i++) {
						if(checked[i]) tmp += (i+1)+" ";
					}
					combList.add(tmp);
				}
				//만약 최솟값 갱신시에는 리스트 내용 모두 초기화 후에 현재것 넣기
				else {
					result = total.price;
					String tmp ="";
					for(int i=0; i<N;i++) {
						if(checked[i]) tmp += (i+1)+" ";
					}
					combList.clear();
					combList.add(tmp);

					count = cnt;
				}
			}
			return;
		}

		//선택한 경우
		checked[idx] = true;
		comb(idx+1, total.add(list[idx]), checked, cnt+1);
		checked[idx] = false;

		//선택안 한 경우
		comb(idx+1, total, checked, cnt);




	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		list = new Essential[N];


		result = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());

		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());

		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());

			list[i] = new Essential(p, f, s, v, price);
		}

		comb(0, new Essential(), new boolean[N], 0);

		if(result==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(result);

			//사전순으로 보기위함
			Collections.sort(combList);
			
			System.out.println(combList.get(0));
		}

	}
}
