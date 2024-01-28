import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	static int caseCounter = 0;
	static int N ;
	static int filledN;
	static int K ; 
	static final int lowerLimit = 500;
	static int[] equips;
	public static void main(String[] args) throws Exception{
		BufferedReader br;	
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader("src/18429.txt"));
		
		String tempString[] = br.readLine().split(" ");
		N = Integer.parseInt(tempString[0]); // 두자리수 안됨
		K = Integer.parseInt(tempString[1]);
		filledN = (1 << N) - 1;
		
		equips = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		//----입력 끝
		dfs(0, 500);
		System.out.println(caseCounter);
	}

	
	static void dfs(int equipmentsUsed, int muscle) {
		//근손실 너무 심하면 종료
		if(muscle < lowerLimit) 
			return;
		//모든 장비를 다 사용한 경우->경우의 수 추가
		if(equipmentsUsed == filledN)
			caseCounter++;
		//사용할 장비 남은 경우
		int backwardCursor = 1;
		int tempNewUse;
		for(int backCounter = N-1; backCounter >= 0; backCounter--) {
			tempNewUse = equipmentsUsed | backwardCursor;
			if(tempNewUse > equipmentsUsed) {
				//해당 장비로 운동한 다음날 simulate
				dfs(tempNewUse, muscle + equips[backCounter] - K);
			}
			backwardCursor = backwardCursor << 1;
		}
	}
}
