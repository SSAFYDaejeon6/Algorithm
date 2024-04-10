import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시작부터 k개를 먼저 먹고 시작해서
 * 똑같은 종류가 아닌 초밥을 몇개 먹었는지 세면 됨
 * 예를 들어 k가 3이고 0, 1, 2 인덱스의 초밥이 1, 2, 1의 번호를 가졌다면
 * 2가지 종류의 초밥을 먹었다고 생각하면 됨
 * 
 * k개의 접시를 연속해서 먹으면 보너스 초밥은 무조건 먹는것이기 때문에
 * 미리 먹고 시작
 * 168608KB | 560ms	
 */


public class 회전초밥 {
	static int N, d, k, c;
	static int[] arr; // 초밥의 번호(종류)를 담는 배열
	static int[] noft; // 초밥(인덱스가 초밥의 번호)을 몇개 먹었는지 확인
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new int[N];
		// 초밥의 번호가 최소가 1이고 가짓수가 d개이기 때문에 d+1
		noft = new int[d + 1]; 

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		sushi();
		
		System.out.println(res);
	}

	static void sushi() {
		// 처음에 k개의 초밥을 먹고 시작하기 때문에
		// 보너스 초밥은 무조건 먹고 시작
		// 처음에 보너스 초밥을 먹은것이니 현재 1가지 종류의 초밥을 먹은 것
		int eat = 1;
		noft[c]++;
		
		for (int i = 0; i < k; i++) {
			// 겹치지 않게 먹기
			// k가 4고 1 1 2 2가 들어오면
			// noft[1] = 2, noft[2]==2, eat=2임 (보너스 초밥을 고려를 안하고 예시를 들기 위함)
			if (noft[arr[i]]++ == 0) {
				eat++;
			}
		}

		// 일단 처음부터 최대값 갱신
		res = eat;
		
		for (int i = 0; i < N; i++) {
			// - - - - 0~3
			//   - - - - 1~4
			// 위처럼 뒤에값은 더하고 앞에값은 빼는것처럼 수행
			
			// 뒤에값(end) 갱신
			if(noft[arr[(i+k)%N]]++ == 0) eat++;
			
			// 앞(first) 갱신
			// 맨앞 종류의 초밥을 먹은것이 하나도 없다면
			// 먹은 가짓수가 줄어든 것이기 때문에 eat를 줄여줌
			if(--noft[arr[i]] == 0) eat--;
			
			res = Math.max(res, eat);
		}
	}

}
