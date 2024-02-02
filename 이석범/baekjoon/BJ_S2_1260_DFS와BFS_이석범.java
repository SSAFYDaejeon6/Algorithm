import java.util.*;

//33100kb 404ms
public class BJ_S2_DFS와BFS_이석범 {

    //DFS, BFS별로 방문 배열 만들기
//    static boolean[] BFSVisited;
//    static boolean[] DFSVisited;
    static boolean[] visited;
    //n이 1000이기 때문에 배열로 만들면 0이 많은 희소행렬로 되기때문에
    //인접리스트를 만들었다, 인접리스트는 리스트의 배열
    static ArrayList<Integer>[] graph;
    static StringBuilder sb;
    static int n, m;

    //DFS, 재귀 = 스택을 사용
    static void DFS(int current) {
        //현재 노드를 방문하고 출력에 저장
        visited[current] = true;
        sb.append(current).append(" ");


        //만약 현재 노드의 인접한 노드가 방문하지 않았다면 재귀인 DFS로 방문
        for(int i=0; i<graph[current].size();i++) {
            int nextNode = graph[current].get(i);
            if(visited[nextNode]) continue;
            DFS(nextNode);
        }
    }

    //BFS, 큐 사용
    static void BFS(int current) {
        //큐 선언
        Queue<Integer> queue = new ArrayDeque<>();
        //맨 처음 노드 저장
        queue.offer(current);
        visited[current] = true;
        sb.append(current).append(" ");
        //큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            int pollNode = queue.poll();
            //현재 노드와 인접한 노드중 방문하지 않은 노드 큐에 저장
            for(int i=0; i< graph[pollNode].size();i++) {
                int nextNode = graph[pollNode].get(i);
                if(visited[nextNode]) continue;
                visited[nextNode] = true;
                queue.add(nextNode);
                sb.append(nextNode).append(" ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        n = sc.nextInt();
        m = sc.nextInt();
        int start = sc.nextInt();
        //리스트 배열의 크기는 n+1개로 선언
        graph = new ArrayList[n+1];
        for(int i=1; i<n+1;i++) {
            //리스트를 사용하려면 초기화 해야함
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<m;i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            //a->b, b->a 각각이 연결
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i=1; i<n+1;i++) {
            //노드 번호가 작은 순서로 해야하므로 정렬
            Collections.sort(graph[i]);
        }


        visited = new boolean[n+1];


        DFS(start);
        sb.append("\n");

        visited = new boolean[n+1];

        BFS(start);
        sb.append("\n");

        System.out.println(sb);
    }
}
