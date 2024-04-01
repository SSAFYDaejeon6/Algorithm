import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/** Main_B_5557_1학년_이동준 11748KB 80ms
 * 숫자의 개수: N개
 * 	3<= 수수수숫자 <= 100
 * 마지막 숫자를 만들어야 함. 
 * 	중간에 나오는 정수들은 0~9 10개
 * 	중간에 0이 되면 안되므로 숫자의 순서는 유지해야 함.
 * 어떤 숫자를 만드는 가짓수
 * 
 * 
 * 
 */
public class Main_B_5557_1학년_이동준 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] reverseSum = new int[N - 1];

		String tempStr = br.readLine();
		for(int i = 0; i < N; i++) {
			nums[i] = tempStr.charAt(2 * i) - '0';
		}
		//입력끝
		reverseSum[N - 2] = nums[N - 1];//idx N - 2 까지 연산을 했을 때, 이 숫자 이하어야 한다. 초과이면 N - 2를 빼도 절대 원하는 값을 만들 수 없기 때문
		for(int i = N - 3; i > 0; i--) {
			reverseSum[i] = Math.min(20, nums[i + 1] + reverseSum[i + 1]);
		}
		
		Map<Integer, Long> cl = new HashMap<>();
		Map<Integer, Long> pl = new HashMap<>();
		cl.put(nums[0], 1L);
		for(int i = 1; i < (N - 1); i++) {
			for(Entry<Integer, Long> e : cl.entrySet()) {
				int sum = e.getKey();
				long occur = e.getValue();
				int[] made = {sum + nums[i], sum - nums[i]};
				for(int n : made) {
					if(n < 0 || n > reverseSum[i]) continue;
					if(pl.containsKey(n)) {//이미 있음
						pl.put(n, pl.get(n) + occur);
					}else {//새 sum임
						pl.put(n, occur);
					}
				}
			}
			cl = pl;
			pl = new HashMap<>();
		}
		
		if(cl.containsKey(nums[N - 1])) {
			System.out.println(cl.get(nums[N - 1]));
		}else {
			System.out.println(0);
		}
		
	}
}
