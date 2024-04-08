import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
11724KB |  	84ms
[BOJ] 1043 거짓말
목표 : 지민아가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 갯수의 최댓값
1: N : 사람의 수 (1~N번의 사람), M : 파티의 수
2: 진실을 아는 사람의 수, 진실을 아는 사람의 번호들
3: 파티마다 오는 사람의 수, 번호

- 진실을 아는 사람들이 파티에 왔을 때는 지민이는 진실만 이야기
- 어떤 사람이 어떤 파티에서 진실을 듣고, 또다른 파티에서는 과장된 이야기 -> 거짓말쟁이
=> 같은 사람이 참석한 파티에서는 진실만을 얘기

풀이
0. 한 파티에 참가하는 사람들 그래프로 관계 표현
1. 진실을 아는 사람이 포함된 파티에 참석한 사람들 따로 표시 -> DFS로 조부모의 파티 정보까지 표현
2. 모든 파티를 돌면서 1번에 해당하는 사람들이 포함되었다면 pass

 */
public class B1043 {

    static List<Integer>[] party;
    static boolean[] truePeople;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int trueCnt = Integer.parseInt(st.nextToken());
        truePeople = new boolean[N + 1];
        for (int i = 0; i < trueCnt; i++) {
            truePeople[Integer.parseInt(st.nextToken())] = true;
        }
        adj = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        //파티 정보
        party = new List[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new ArrayList<>();
            int partyCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < partyCnt; j++) {
                int partyPerson = Integer.parseInt(st.nextToken());
                party[i].add(partyPerson);
            }
            for(int j = 0; j < partyCnt; j++){
                for(int k = 0; k < partyCnt; k++){
                    if(j==k) continue;
                    int a = party[i].get(j);
                    int b = party[i].get(k);
                    adj[a].add(b);
                    adj[b].add(a);
                }

            }
        }

        // 로직
        // 1. 진실을 아는 사람이 포함된 파티에 참석한 사람들 따로 표시
        for (int i = 0; i < N + 1; i++) {
            if (truePeople[i]) {
                DFS(i);
            }
        }
        int answer = 0;
        // 2. 모든 파티를 돌면서 1번에 해당하는 사람들이 포함되었다면 pass
        A:
        for (List<Integer> p : party) {
            for (int person : p) {
                if (truePeople[person]) {
                    continue A;
                }
            }
            answer++;
        }

        System.out.println(answer);

    }

    static void DFS(int start) {
        truePeople[start] = true;
        for (int i = 0; i < adj[start].size(); i++) {
            int curr = adj[start].get(i);
            if (!truePeople[curr]) {
                DFS(curr);
            }
        }
    }
}
