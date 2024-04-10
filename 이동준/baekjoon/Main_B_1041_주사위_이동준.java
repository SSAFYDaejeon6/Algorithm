/**Main_B_1041_주사위 	11900KB 88ms
 *	IDEA
 *		가운데에 있는 주사위의 값은 절대 보이지 않는다.
 *		주사위 외곽만 신경쓰면 된다.
 *		N= 1일떄를 제외하면
 *		한 주사위는 최대 3 면을 보인다
 *		N =1일때는 특수 처리
 *		N != 2일때		
 *			3면짜리 최소 구해서 윗 모서리 4개 채우기
 *				주사위를 이렇게 나타낼 수 있다.
 *					top 숫자, bottom 숫자, 둘 사이의 숫자 4개.
 *				이때 top-bottom 조합은 AF, CD, BE의 세 가지만 신경쓰면 된다.
 *				4열에서 최소 구하면 됨
 *			위와 똑같은걸로 외곽 다 채우기. 총 (N - 1) * 4 개.
 *			최소면*4*(N - 1)^2 개 채우기
 *	
 * 	
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B_1041_주사위_이동준 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Dice dice = new Dice(br.readLine().split(" "));
		if(N == 1) {
			int total = dice.top + dice.sides[0] + dice.sides[1] + dice.sides[2] + dice.sides[3] + dice.bottom;
			int min = total - dice.bottom;
			dice.rotate_UP();//A는 이제 앞, F는 뒤
			dice.rotate_C();//A는 이제 오른쪽
			min = Math.min(min, total - dice.bottom);
			for(int i = 0; i < 3; i++) {
				dice.rotate_UP();
				min = Math.min(min, total - dice.bottom);
			}
			dice.rotate_CC();//A는 이제 앞
			dice.rotate_UP(); //A는 이제 밑
			min = Math.min(min, total - dice.bottom);
			System.out.println(min);
			System.exit(0);
		}
		//최약의 3, 2, 1찾기


		int[] temp = {dice.top, dice.sides[0], dice.sides[1], dice.sides[2], dice.sides[3], dice.bottom};
		Arrays.sort(temp);
		int min_one = temp[0];
		int min_two = Integer.MAX_VALUE;			
		int min_three = Integer.MAX_VALUE;
		
		//세 가지 주사위 경우에 대해 찾기
		int[] testResult = test(dice);
		min_two = Math.min(min_two, testResult[0]);	
		min_three = Math.min(min_three, testResult[1]);
		dice.rotate_UP();
		testResult = test(dice);
		min_two = Math.min(min_two, testResult[0]);	
		min_three = Math.min(min_three, testResult[1]);	
		dice.rotate_DOWN();
		dice.rotate_C();
		dice.rotate_UP();
		testResult = test(dice);
		min_two = Math.min(min_two, testResult[0]);	
		min_three = Math.min(min_three, testResult[1]);			
		
		//덧셈 시작
		long result = 0;
		result += 4 * min_three;//위 모서리 네개
		result += 4 * ((N - 1) * min_two); // 네 기둥
		if(N >= 3) {
			result += 4 * (min_two) * (N - 2);//위쪽의 네 변
			result += min_one * (long) Math.pow(N - 2, 2); //위쪽 내부
			result += 4 * (min_one * ((long) Math.pow(N - 2, 2) + N - 2));// 옆면들 내부
		}
		System.out.println(result);
	}
	
	static int[] test(Dice dice) {
		int cap = (dice.top < dice.bottom) ? dice.top : dice.bottom;
		int sideMin = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			sideMin = Math.min(sideMin, dice.sides[0] + dice.sides[1]);
			dice.rotate_C();
		}
		return new int[] {sideMin, cap + sideMin};
	}
	private static class Dice{
		int top, bottom;
		int[] sides;
		
		Dice(String[] faces){
			top = Integer.parseInt(faces[0]);
			bottom = Integer.parseInt(faces[5]);
			sides = new int[4];
			sides[0] = Integer.parseInt(faces[1]); //B
			sides[1] = Integer.parseInt(faces[2]); //C
			sides[2] = Integer.parseInt(faces[4]); //E
			sides[3] = Integer.parseInt(faces[3]); //D
		}
		
		void rotate_C() {//clockwise 
			int temp = sides[3];
			System.arraycopy(sides, 0, sides, 1, 3);
			sides[0] = temp;
		}
		
		void rotate_CC() {//counterclockwise 
			int temp = sides[0];
			System.arraycopy(sides, 1, sides, 0, 3);
			sides[3] = temp;
		}
		
		void rotate_UP() {
			int temp1 = top;
			int temp2 = bottom;
			top = sides[1];
			bottom = sides[3];
			sides[3] = temp1;
			sides[1] = temp2;
		}
		
		void rotate_DOWN() {
			int temp1 = top;
			int temp2 = bottom;
			top = sides[3];
			bottom = sides[1];
			sides[1] = temp1;
			sides[3] = temp2;
		}		
	}
}

