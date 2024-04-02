/**Main_B_1043_거짓말_이동준 11780KB 80ms
 *
 *Facts
 *	어떤 사람이 여러 파티에 걸쳐 다른 이야기를 들어도 안됨.
 *	
 *Idea
 *	진실은 1, 거짓은 -1, 둘다모름은 0 이라고 하자
 *	Map<Integer, int[]> party 를 생각해볼 수 있다.
 *	party의 조성
 *		진실아는사람 거짓아는사람
 *			0		0		진실 거짓 모두 가능
 *			있		0		진실 가능
 *			0 		있		거짓 가능
 *			있		있		이런 경우는 피해야함
 *	
 *	이경우, 모든 사람이 진실을 모르고, 각 파티에 한명만 오는 경우 가짓수가 2^50 가지가 되어버린다.
	진실을 아는 사람이 많아질 때 그게 이득이 되는 경우가 존재할 수 있다. 따라서 선택지가 존재할 때 거짓말만 할 수도 없음.
 *	
 *	어떤 파티에서 주장을 했을 때, 파티에 등장한 사람들이 등장하는 모든 파티는 같은 주장을 요구하게 된다.
 *	즉, 파티들을 Set으로 묶을 수 있다.
 *	모든 파티를 묶은 뒤, 진실을 알고 있는 사람들이 존재하는 파티를 제외한 나머지에서 모두 거짓말을 하면 된다.
 *
 *프로세스
 *	입력을 받은 뒤,
 *	int[] groups 를 만든다. index는 사람 번호이다. 값은 해당 사람의 parent인 사람 번호이다. 처음엔 -1로 init.
 *	int[] parties 역시 필요하다. index는 파티 번호이다. 값은 parent인 사람이다. 처음엔 -1로 init.
 *	모든 파티에 대해서
 *		모든 파티원에 대해서 첫 번째로 만나는 그룹에 다른 파티원을 전부 등록시킨다. unsigned 사람이면 parent를, 다른 그룹이 있으면 해당 그룹의 root을 등록시킨다.
 *		만약 모두가 unsigned이면 새 그룹을 만든다.
 *		마지막으로, 해당 파티의 정보를 parties에 등록한다.
 *	진실을 아는 사람에 대해 findSet를 진행한다. 그리고 root를 List<Integer> trueGroups 에 등록한다
 *	int lieCount	
 *	모든 파티에 대해 findSet을 진행하고, root가 trueGroup에 없으면 lieCount를 1 증가시킨다.
 *	
 * 	
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_B_1043_거짓말_이동준 {
	static int[] groups;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num_people, num_parties, num_awaken;
		int[] awaken;
		StringTokenizer st = new StringTokenizer(br.readLine());
		num_people = Integer.parseInt(st.nextToken());
		num_parties = Integer.parseInt(st.nextToken());
		groups = new int[num_people];
		int[] parties = new int[num_parties];
		for(int i = 0; i < num_people;i++) {
			groups[i] = i;//makeSet
		}
		
		st = new StringTokenizer(br.readLine());
		num_awaken = Integer.parseInt(st.nextToken());
		awaken = new int[num_awaken];
		if(num_awaken > 0 ) {
			for(int i = 0 ; i < num_awaken; i++) {
				awaken[i] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		for(int i = 0; i < num_parties; i++) {
			st = new StringTokenizer(br.readLine());
			int partySize = Integer.parseInt(st.nextToken());
			int[] curParty = new int[partySize];
			for(int p = 0; p < partySize; p++) {
				curParty[p] = Integer.parseInt(st.nextToken()) - 1;
			}
			for(int p = 1; p < partySize; p++) {
				merge(curParty[p], curParty[p - 1]);
			}
			parties[i] = findSet(curParty[0]);
		}
		boolean[] unlieable = new boolean[num_people];
		for(int trueOne : awaken) {
			int group = findSet(trueOne);
			unlieable[group] = true;
		}
		int lieCount = 0;
		for(int partyGroup : parties) {
			if(!unlieable[findSet(partyGroup)]) lieCount++;
		}
		System.out.println(lieCount);
	}
	
	static int findSet(int personIdx) {
		if(groups[personIdx] == personIdx) return personIdx;
		else{
			Deque<Integer> stack = new ArrayDeque<>();
			stack.push(personIdx);
			stack.push(groups[personIdx]);
			int highest = -1;
			while(true) {
				Integer cur = stack.pop();
				if(groups[cur] == cur) {
					highest = cur;
					break;
				}else stack.push(groups[cur]);
			}
			while(!stack.isEmpty()) {
				groups[stack.pop()] = highest;
			}
			return highest;
		}
	}
	
	static boolean merge(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot != bRoot) {
			groups[bRoot] = aRoot;
			return true;
		}
		return false;
	}
}

