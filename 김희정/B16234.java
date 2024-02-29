import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//	292492KB    |	460ms
public class B16234 {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] temp;
    static int[] groups;
    static int groupCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //로직
        int day = 0;
        while(true){
            // 1. 연합 표시 : 1번 연합 , 2번 연합 ..
            temp = new int[N][N];
            groups = new int[N*N];
            groupCnt = 1;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(temp[i][j] == 0){
                        BFS(i,j);
                    }
                }
            }

            if(groupCnt == 1){  //연합이 하나도 만들어지지 않았으면 인구 이동 종료
                System.out.println(day);
                return;
            }

            // 2. map값 업데이트
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int groupNum = temp[i][j];
                    if(groupNum == 0) continue;
                    map[i][j] = groups[groupNum];
                }
            }
            // 3. 날짜 증가
            day++;
        }
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static void BFS(int x, int y){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});
        boolean flag = false;
        int sum = 0;
        int cnt = 0;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int d = 0; d < 4; d++){
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];
                if(!inRange(nx, ny)) continue;
                if(temp[nx][ny] != 0) continue; //이미 연합에 속한 곳은 continue
                int diff = Math.abs(map[curr[0]][curr[1]] - map[nx][ny]);
                if(L <= diff && diff <= R){
                    temp[nx][ny] = groupCnt;
                    queue.offer(new int[]{nx,ny});
                    flag = true;
                    sum += map[nx][ny];
                    cnt++;
                }
            }
        }
        if(flag){   //연합이 하나라도 만들어졌다면
            groups[groupCnt] = sum/cnt;    //index 1 : 1번 그룹의 정보
            groupCnt++;
        }
    }

    static boolean inRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
