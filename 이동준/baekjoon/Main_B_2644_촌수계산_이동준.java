/**Main_B_2644_촌수계산_이동준 11524KB 76ms
 * 
 * Idea
 * 	두 사람이 촌수 관계가 있으려면 같은 조상을 공유해야 한다.
 * 	Node object의 연속으로 Tree를 표현한다
 * 		각 Node는 자신의 부모 node의 번호를 가진다.
 * 		또한 자신이 두 TargetNode의 부모인지 여부를 나타내는 두 변수를 가진다.
 * 		입력을 마친 후,
 * 		두 TargetNode를 거슬러 올라간다.
 * 		그러던 중 두 변수가 true인 조상을 만나면 거기까지의 거리의 합이 촌수이다.
 * 		
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_B_2644_촌수계산_이동준 {
	static BufferedReader br;
	static person A;//관심있는 첫번째 사람
	static person B;//관심있는 첫번째 사람
	static Map<Integer, person> persons;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		//		br = new BufferedReader(new FileReader("./b/2644.txt"));
		int N = Integer.parseInt(br.readLine());
		int M;
		persons = new HashMap<Integer, person>();
		String[] tempStringArr = br.readLine().split(" ");
		A = new person(Integer.parseInt(tempStringArr[0]));
		B = new person(Integer.parseInt(tempStringArr[1]));
		persons.put(A.number, A);
		persons.put(B.number, B);
		M = Integer.parseInt(br.readLine());
		int[] tempPC = new int[2];
		for(int i = 0; i < M; i++) {
			tempStringArr = br.readLine().split(" ");
			tempPC[0] = Integer.parseInt(tempStringArr[0]);
			tempPC[1] = Integer.parseInt(tempStringArr[1]);
			for(int i2 = 0; i2 < 2; i2++) {
				if(!persons.containsKey(tempPC[i2])) persons.put(tempPC[i2], new person(tempPC[i2]));
			}
			persons.get(tempPC[1]).parent = persons.get(tempPC[0]);//부모 등록
		}
		br.close();
		//입력 끝------
		int result = -1;
		person[] targets = {A, B};
		outer : for(int i = 0; i < 2; i++) {
			person current = targets[i];
			while(true) {
				current.parentOf[i] = true;
				if((current.parentOf[0] == true) && (current.parentOf[1] == true)) {
					result = current.distanceTo[0] + current.distanceTo[1];
					break outer;
				}
				if(current.parent == null) break;
				current.parent.distanceTo[i] = current.distanceTo[i] + 1;
				current = current.parent;
			}
		}
		System.out.println(result);
	}
}

class person{
	final int number;
	boolean[] parentOf = new boolean[2];
	int[] distanceTo = new int[2];
	person parent;
	person(int num){
		number = num;
	}

}
