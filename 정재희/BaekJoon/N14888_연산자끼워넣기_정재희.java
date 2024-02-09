import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14888_연산자끼워넣기_정재희 {
	/**
	 * [ 문제 해석 ]
	 * N개의 수 순서 고정
	 * N-1개의 연산자를 끼워넣어, 최대값과 최소값 구하기
	 * 
	 * [ 문제 해결 프로세스 ]
	 * 
	 * N-1 개 연산자의 순서에 대한 경우의 수를 구한 다음
	 * 연산 수행 -> 최대값, 최소값과의 비교 후 저장
	 * 
	 * N-1 개 연산자 정렬에 대한 경우의 수 -> 순열 => 재귀 이용
	 * 재귀를 진행하면서 연산 적용, 결과값을 매개변수로 전달
	 * 재귀가 N-1번 돌았을 경우, 최소, 최대 값 저장 및 종료
	 * 
	 * [ 결과 ]
	 * 시간: 364ms, 메모리: 12,248 KB
	 */
	static int N;
	static char[] operator = {'+', '-', '*', '/'}; // 연산 선택 조건에서 가독성을 높이기 위해 사용
	static int[] nums;  // N개의 수를 저장할 배열
	static char[] operArr;  // N-1개의 연산자들을 저장할 배열
	static boolean[] isSelected;  // 순열  재귀 동안 요소 선택 여부 저장 배열
	static int min=Integer.MAX_VALUE, max = Integer.MIN_VALUE;  // 최소, 최대값을 저장할 변수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operArr = new char[N-1];
		isSelected = new boolean[N-1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int k = 0;
		for (int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				operArr[k++] = operator[i];  // 입력받는 문자의 개수만큼 반복하면서, 배열에 연산자 저장
			}
		}
		
		permu(0, nums[0]);  //첫번째 피연산자 저장(연산자 선택 후 바로 연산을 적용하기 위해)
		System.out.println(max + "\n" + min);
	}
	private static void permu(int depth, int res) {
		if(depth == N-1) {
			min = min > res? res:min;
			max = max < res? res:max;
			return;
		}
		for (int i = 0; i < N-1; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			switch(operArr[i]) {  // 연산자 선택 후 연산 적용 및 다음 연산자 선택을 위해 재귀 호출
			case '+':				
				permu(depth+1, res+nums[depth+1]);
				break;
			case '-':
				permu(depth+1, res-nums[depth+1]);
				break;
			case '*':
				permu(depth+1, res*nums[depth+1]);
				break;
			case '/':
				permu(depth+1, res/nums[depth+1]);
				break;
			}
			isSelected[i] = false;
		}
		
	}

}
