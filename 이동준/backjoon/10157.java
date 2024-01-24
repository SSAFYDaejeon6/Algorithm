import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;

	/*
	 * 주어진 문제의 예시 6X7 공연장의 경우 가장 바깥 둘레부터 한 겹씩 총 3겹(레이어)의 직사각형으로 생각할 수 있다.
	 * 각 직사각형은 매 겹마다 둘레가 8씩 줄어든다
	 * 좌석번호가 주어졌을 때, 해당 좌석번호가 "속하는 레이어" x 에 대해 좌석번호의 부등식을 세울 수 있다
	 * 해당 부등식의 좌변을 "레이어"에 대한 이차방정식으로 정리한뒤 풀면(solvePolynomial) 
	 * 원하는 티켓 번호가 주어졌을 때, 해당 티켓이 속하는 "레이어" 번호를 구할 수 있다.
	 * 각 레이어의 시작 좌석은 1번 좌석이 (1, 1) 이라고 할 때 레이어 n의 시작 좌석은 (n, n)이다
	 * 등차수열의 합(getSum)을 이용하면 레이어 n의 마지막 좌석의 번호 k를 구할 수 있다.
	 * k - 해당 레이어의 둘레 + 1 하면 레이어 n의 첫 좌석의 번호를 구할 수 있다. 이때 이 좌석의 좌표는 (n, n) 이다.
	 * 이제, 원하는 티켓 번호 - 레이어 n의 첫 좌석의 번호 만큼 시계방향으로 전진하면 원하는 티켓 번호의 좌표를 구할 수 있다.
	 */

public class Main{
	private static final int[][] dxy = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0},
	};
	
	static BufferedReader br;		
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/10157.txt"));
		
		int[] RC = new int[2];
		String tempString[] = br.readLine().split(" ");
		for(int i = 0; i < 2; i++) {
			RC[i] = Integer.parseInt(tempString[1 - i]);
		}
		int ticketNumber = Integer.parseInt(br.readLine());
		//----입력 끝
		int outer = getOuter(RC[0], RC[1]);
		if(getSum(outer, -8, 1 + outer/8) < ticketNumber) {// ticket번호가 최대 ticket 번호보다 큼
			System.out.println(0);
			return;
		}
		int[] cursor = {0, 0};
		//ix^2 + (2s - i)x - 2k = 0
		int layer = solvePolynomial(
				-8,
				(2 * outer) - (-8),
				-2 * ticketNumber
		);

		cursor[0] += layer;
		cursor[1] += layer;
		
		int[] CurrentXY = { RC[1] - 2 * (layer - 1), RC[0] - 2 * (layer - 1) };
		int cursorNumber = getSum(outer, -8, layer) - getOuter(CurrentXY[0], CurrentXY[1]) + 1 ;
		//이제 시계방향으로 돌면 된다. 최대 한바퀴만 돌면 됨
		int toGo = ticketNumber - cursorNumber;
		int direction = 0;
		int[] internalCursor = {1, 1};
		int[] cursorDiff = {cursor[0] - internalCursor[0], cursor[1] - internalCursor[1]};
		int[] tempOut;
		
		
		while(toGo > 0) {
			tempOut = Clockwise(toGo, direction, CurrentXY, internalCursor);
			toGo = tempOut[0];
			direction = tempOut[1];
		}
		
		int[] finalCursor = {cursorDiff[0] + internalCursor[0], cursorDiff[1] + internalCursor[1]};
		
		System.out.println(finalCursor[0] + " " + finalCursor[1]);
	}

	public static int solvePolynomial(int a, int b, int c) {
		//take smallest natural root only
		//ix^2 + (2s-i)x - 2c = 0
		int numerator_int = -b;
		double numerator_double = Math.sqrt(
			Math.pow(b, 2) - (4 * a * c)
		);
		double[] twoRoots = {numerator_int + numerator_double, numerator_int - numerator_double};
		twoRoots[0] /= (2 * a);
		twoRoots[1] /= (2 * a);
		Arrays.sort(twoRoots);
		if(twoRoots[0] < 0)
			return (int) Math.ceil(twoRoots[1]); 
		return (int) Math.ceil(twoRoots[0]); 
	}
	
	public static int getSum(int start, int increment, int n) {
		return (n * (2 * start + increment * (n - 1)))/2;
	}
	
	public static int[] Clockwise(int toBack, int direction, int[] XY, int[] internalCursor) {
		direction = direction%4;
		int XorY = (direction + 1)%2;
		int maxBack = Math.abs(new int[]{XY[1],	XY[0], 1, 1}[direction] - internalCursor[XorY]);
		int went = Integer.min(toBack, maxBack);
		internalCursor[XorY] = internalCursor[XorY] + went * dxy[direction][XorY];
		toBack -= went;
		direction++;
		return new int[] {toBack, direction};
	}
	
	public static int getOuter(int a, int b) {
		return 2 * a + 2 * b - 4;
	}
}
