/**B_14888_연산자끼워넣기_이동준 16468KB 100ms 가지치기 이전 12140KB 88ms
 * 
 * 가지치기를 활용한 순열 계산(성능이 오히려 떨어짐 ㅠㅠ)
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14888_연산자끼워넣기_이동준 {
	static BufferedReader br;
	static StringTokenizer st;

	static int N; //total number of operands
	static int T; //total number of operators. N - 1
	static int[] operands;// 피연산자
	static int MAX = Integer.MIN_VALUE; //최대
	static int MIN = Integer.MAX_VALUE; //최소
	static int[] OP = new int[4]; //operators; 연산자.

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
//				br = new BufferedReader(new FileReader("./b/14888.txt"));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = N - 1;

		st = new StringTokenizer(br.readLine());
		operands = new int[N];

		for(int i = 0; i < N; i++) {
			operands[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			OP[i] = Integer.parseInt(st.nextToken());
		}		
		//입력 끝-----
		permutation(0, operands[0]);
		System.out.println(MAX);
		System.out.println(MIN);
	}

	public static void permutation(int used, int current) {
		if(used == T) {
			MAX = Math.max(MAX, current);
			MIN = Math.min(MIN, current);
			return;
		}
		if(pruning(used, current)) return;

		for(int cursor = 0; cursor < 4; cursor++) {
			if(OP[cursor] == 0) continue;
			OP[cursor] -= 1;
			permutation(used + 1, operate(current, cursor, used + 1));
			OP[cursor] += 1;
		}
	}

	public static int operate(int left, int OPIdx, int operandIdx ) {
		switch(OPIdx) {
		case 0: //덧셈
			return left + operands[operandIdx];
		case 1: //뺄셈
			return left - operands[operandIdx];
		case 2: //곱셈
			return left * operands[operandIdx];
		case 3: //나눗셈. C++14
			return left / operands[operandIdx];
		}
		System.out.println("NO!");
		return 0;
	}

	//값이 true: pruning 가능함을 의미
	public static boolean pruning(int used, int current) {
		boolean maxAble = false;//값이 true: pruning 가능함을 의미
		boolean minAble = false;//값이 true: pruning 가능함을 의미
		//for MAX
		if(MAX - current >= 0) {//최댓값이 더 큼
			if(current >= 0)//현재 들고있는 수가 양수
				maxAble = zeroCheck(new boolean[] {true, false, true, false});//연산자 중 덧셈, 곱셈 없으면 증가못시킴
			else //현재 들고있는 수가 음수
				maxAble = zeroCheck(new boolean[] {true, false, false, true});//연산자 중 덧셈, 나눗셈 없으면 증가못시킴

		}
		
//		for MIN
		if(MIN - current >= 0) {//최솟값이 더 큼 -> Do Nothing
		}else {//최솟값이 더 작음
			if(current >= 0)//현재 들고있는 수가 양수
				maxAble = zeroCheck(new boolean[] {false, true, false, true});//연산자 중 뺼셈, 나눗셈 없으면 감소못시킴
			else //현재 들고있는 수가 음수
				maxAble = zeroCheck(new boolean[] {false, true, true, false});//연산자 중 뺄셈, 곱셈 없으면 증가못시킴	
		}
		return (maxAble == true && minAble == true);
	}

	public static boolean zeroCheck(boolean[] checker) {
		for(int i = 0; i < 4; i++) {
			if(checker[i] == true)
				if(OP[i] != 0) return false;
		}
		return true;
	}

}
