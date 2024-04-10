import java.io.*;
import java.util.*;
/* 135232kb 652ms
 * [문제해석]
	선 플레이어: 홀수 번째 차례
	후 플레이어: 짝수 번째 차례
	0부터 n-1까지 고유한 번호가 부여된 평면 상의 점 n개
	어느 세 점도 일직선 위에 놓이지 않음
	두 점을 선택해서 이를 연결하는 선분을 긋는데, 이전에 그린 선분은 다시 그을 수 없지만
	이미 그린 다른 선분과 교차하는 것은 가능
	처음으로 사이클을 완성하는 순간 게임 종료
	
	사이클 C는 플레이어가 그린 선분들의 부분집합
	| C에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 
		출발점으로 되돌아올 수 있다.
		
	게임 진행 상황이 주어지면 몇 번째 차례에서 사이클이 완성되었는지, 혹은 게임이 진행 중인지
	판단하는 프로그램
	
	[입력]
	1. 3<=N<=500,000 (점의 개수), 3<=m<=1,000,000 (진행된 차례 수)
	3. 해당 플레이어가 선택한 두 점의 번호
	
	[출력]
	게임이 종료되었다면 사이클이 처음으로 만들어진 차례의 번호를 양의 정수로 출력
	종료되지 않았다면 0 출력
 */
public class Main_G4_20040_사이클게임 {
	static int N, M;
	static boolean visit[];
	static int[] parent;

	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    parent = new int[N];
	    for(int i=0;i<N; i++) 
	    	parent[i] = i;
	    
	    for(int i=1; i<=M; i++) {
	    	st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    
		    boolean check = union(a, b);
		    if(check) {
		    	System.out.println(i);
		    	return;
		    }
	    }
	    
	    System.out.println(0);
	}

	//서로소 집합
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return true; //이미 부모 노드가 같으면 사이클 발생
		if(pa>pb) parent[pa] = pb;
		else parent[pb] = pa;
		return false;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}


}
