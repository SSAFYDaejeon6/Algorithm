import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [BOJ] 21608 상어 초등학교
 * 24412KB  |	236ms
 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 * 2. 1을 만족하는 칸이 여러 개이면,
 *      인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 * 3. 2를 만족하는 칸도 여러 개인 경우에는
 *  행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로
 * - 각 학생이 좋아하는 학생 : 4명
 * - 인접한 칸 : 상하좌우
 */
public class B21608 {
    static class StudentInfo implements Comparable<StudentInfo>{
         int r;
         int c;
         int friendCnt;
         int blankCnt;

        public StudentInfo(int r, int c, int friendCnt, int blankCnt){
            this.r = r;
            this.c = c;
            this.friendCnt = friendCnt;
            this.blankCnt = blankCnt;
        }

        @Override
        public String toString() {
            return "StudentInfo{" +
                    "r=" + r +
                    ", c=" + c +
                    ", friendCnt=" + friendCnt +
                    ", blankCnt=" + blankCnt +
                    '}';
        }

        @Override
        public int compareTo(StudentInfo o) {
            if(this.friendCnt == o.friendCnt && this.blankCnt == o.blankCnt && this.r == o.r) return o.c - this.c;
            if(this.friendCnt == o.friendCnt && this.blankCnt == o.blankCnt) return o.r - this.r;
            if(this.friendCnt == o.friendCnt) return this.blankCnt - o.blankCnt;
            return this.friendCnt - o.friendCnt;
        }
    }

    static int N;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        List[] friends = new List[N*N+1];
        for(int i = 1; i <= N*N; i++){
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            friends[target] = new ArrayList();
            for(int f = 0; f < 4; f++){
                friends[target].add(Integer.parseInt(st.nextToken()));
            }
            if(i == 1){
                // 초기 세팅
                map[1][1] = target;
                continue;
            }
            List<StudentInfo> infoList = new ArrayList<>();
            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    if(map[r][c] != 0) continue;
                    // 상하좌우에 좋아하는 학생이 있는지 확인
                    int friendCnt = 0;
                    int blankCnt = 0;

                    for(int d = 0; d < 4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(!inRange(nr,nc)) continue;
                        if(map[nr][nc] == 0) {
                            blankCnt++;
                            continue;
                        }
                        //좋아하는 친구인지 판단
                        for(int f = 0; f < 4; f++){
                            if((int)friends[target].get(f) == map[nr][nc]) friendCnt++;
                        }
                    }
                    infoList.add(new StudentInfo(r,c, friendCnt, blankCnt));
                }
            }
            Collections.sort(infoList);

            StudentInfo info = infoList.get(infoList.size()-1);
            map[info.r][info.c] = target;
        }


        //만족도
        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int target = map[i][j];
                int friendCnt = 0;
                for(int d = 0; d < 4; d++){
                    int nx = i + dr[d];
                    int ny = j + dc[d];
                    if(!inRange(nx,ny)) continue;
                    if(friends[target].contains(map[nx][ny])) friendCnt++;
                }
                if(friendCnt ==0){
                    continue;
                }
                answer += Math.pow(10,friendCnt-1);
            }
        }
        System.out.println(answer);
    }

    static boolean inRange(int r, int c){
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
