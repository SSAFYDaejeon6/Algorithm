/**Main_B_16953_AtoB_이동준 13824KB 104ms
 * 
 * Idea
 * 		목표 수의 끝자리가 1이 아닌 홀수이면 불가능하다.
 * 		a를 k1 회 사용하면 B보다 처음으로 같거나 커진다고 하자.
 * 		이때 목표하는 값은 k1보다 작다
 * 
 * 		b를 k2회 사용하면 B보다 처으으로 같거나 커진다고 하자.
 * 		이때 목표하는 값은 k2보다 크다.
 * 		k2 <= x <= k1
 *     
 *     위 구간에서 백트래킹을 적용한 BFS 중복순열을 사용해 문제를 푼다,.
 *  
 *  
 *  문제해결 프로세스
 *  	Queue에서 수를 하나 뽑는다
 *  	a 연산과 b 연산을 진행시킨다. 매 연산마다 현재 연산자 자리수의 연산수를 감소시킨다.
 *   	연산 결과 B이면 중지하고, B보다 크면  continue
 *   	B보다 작으면 다음 연산자 자리수의 연산수를 1 증가시킨다
 *   	Q에 연산한 결과를 저장하고(한번에 최대 두개 저장하겠지..?)
 *   	만약 현재 연산자 자리수가 0이면 
 *  	다음 연산자 자리수를 가져온다.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_B_16953_AtoB_이동준 {
	static BufferedReader br;
	static long A;
	static long B;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] ast = br.readLine().split(" ");
		A = Integer.parseInt(ast[0]);
		B = Integer.parseInt(ast[1]);
		System.out.println(BFS(A, B));
	}
	
	static int BFS(long A, long B) {
		Queue<Long> q = new ArrayDeque<>();
		q.offer(A);
		long cur;
		long[] opResult = new long[2];
		int currentOp = 1;
		int currentOpCount = 1;
		int nextOpCount = 0;
		while(!q.isEmpty()){
			cur = q.poll();
			opResult[0] = cur * 2; opResult[1] = cur * 10 + 1L;
			for(int i = 0; i < 2; i++) {
				if(opResult[i] == B) {
					return currentOp + 1;
				}else if(opResult[i] < B){
					q.offer(opResult[i]);
					nextOpCount++;
				}
			}
			currentOpCount--;
			if(currentOpCount == 0) {
				currentOp++;
				currentOpCount = nextOpCount;
				nextOpCount = 0;
			}
		}
		return -1;//불가
	}

}
