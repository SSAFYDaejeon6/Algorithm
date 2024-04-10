import java.io.*;
import java.util.*;
/* 11744kb 84ms
 * [문제 해석]
	진실로 말하거나 엄청나게 과장해서 말함
	되도록 과장해서 이야기하려고 함
	몇몇 사람들은 그 이야기의 진실을 알고 있음
	이런 사람들이 파티에 왔으면 진실을 이야기할 수 밖에 없음
	어떤 사람이 어떤 파티에서 진실을 듣고 또다른 파티에서 과장된 이야기를 들어오 안됨
	
	사람의 수 N
	이야기의 진실을 아는 사람
	파티에 오는 사람들의 번호가 주어질 때
	지민이가 거짓말쟁이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티 개수의 최댓값
	
	[입력]
	1.  사람의 수 N, 파티의 수 M
	2. 1~N까지 진실을 아는 사람의 수와 번호
	3. 각 파티마다 오는 사람의 수와 번호
	
	1<=N,M<=50
	
	[출력]
	정답의 수
 */
public class Main_B_1043_거짓말_홍경현 {

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean person[] = new boolean[N+1]; //진실을 알고 있는 사람 true
        
        //서로 연결되어 있는 인물을 set에 담음
        Set<Integer>[] set = new Set[N+1];
        for(int i=1; i<=N; i++) set[i] = new HashSet<>();
        
        Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        if(K==0) { //진실을 아는 사람이 없으면 파티 수 출력 후 종료
        	System.out.println(M);
        	return;
        }
        
        for(int i=0; i<K; i++) {
        	int p = Integer.parseInt(st.nextToken());
        	person[p] = true;
        	q.add(p);
        }
        
        int result = 0;
        int party[][] = new int[M+1][]; //M번의 파티에 참석한 사람들
        for(int i=1; i<=M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int C = Integer.parseInt(st.nextToken());
        	party[i] = new int[C];
        	for(int j=0; j<C; j++) {
        		party[i][j] = Integer.parseInt(st.nextToken());
        	}
        	for(int j=1; j<C; j++) {
        		set[party[i][j-1]].add(party[i][j]);
        		set[party[i][j]].add(party[i][j-1]);
        	}
        }
        
        //진실을 알고 있는 사람과 연결된 사람을 모두 true로 해줌
        while(!q.isEmpty()) {
        	int idx = q.poll();
        	for(int n : set[idx]) {
        		if(!person[n]) q.add(n);
        		person[n] = true;
        	}
        }
        
        //진실을 알고 있는 사람이 없는 파티만 result++해줌
        for(int i=1; i<=M; i++) {
        	boolean b = false;
        	for(int j=0; j<party[i].length; j++) {
        		if(person[party[i][j]]) {
        			b = true;
        			break;
        		}
        	}
        	if(!b) result++;
        }
        System.out.println(result);
	}

}
