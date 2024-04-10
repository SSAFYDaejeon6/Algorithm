import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 20488KB  |	176ms
// [BOJ] 21610 마법사 상어와 비바리기
public class B21610 {
    static class Basket {
        int water;
        boolean isInCloud;

        public Basket(int water, boolean isInCloud) {
            this.water = water;
            this.isInCloud = isInCloud;
        }
    }

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int N;
    static Basket[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new Basket[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = new Basket(Integer.parseInt(st.nextToken()), false);
            }
        }

        Queue<int[]> cloud = new ArrayDeque<>();
        cloud.add(new int[]{N - 1, 0});
        cloud.add(new int[]{N - 1, 1});
        cloud.add(new int[]{N - 2, 0});
        cloud.add(new int[]{N - 2, 1});
        //구름 이동 명령
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            // 1. 구름 d방향으로 s만큼 이동
            for (int[] c : cloud) {
                c[0] = (N + c[0] + dx[d] * (s % N)) % N;
                c[1] = (N + c[1] + dy[d] * (s % N)) % N;
                //2. 구름이 있는 칸 물의 양 1 증가
                map[c[0]][c[1]].water++;

            }
            while (!cloud.isEmpty()) {
                // 3. 구름 소멸
                int[] c = cloud.poll();
                map[c[0]][c[1]].isInCloud = true;
                //4. 물복사버그
                int cnt = 0;
                for (int di = 1; di < 8; di += 2) {
                    int bugX = c[0] + dx[di];
                    int bugY = c[1] + dy[di];
                    if (inRange(bugX, bugY)) {
                        if (map[bugX][bugY].water > 0) {  //물이 있으면
                            cnt++;
                        }
                    }
                }

                // 바구니 수만큼 물 증가
                map[c[0]][c[1]].water += cnt;
            }

            // 5. 물의 양이 2이상인 모든 칸에 구름 생성, 물의 양이 2줄어듬
            // 2의 구름은 구름 생성 불가 (isCloud = true인것)
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c].isInCloud) {
                        map[r][c].isInCloud = false;
                        continue;
                    }
                    if (map[r][c].water >= 2) {
                        cloud.offer(new int[]{r, c});
                        map[r][c].water -= 2;
                    }
                }
            }

        }

        //모든 칸의 물의 총합
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j].water;
            }
        }

        System.out.println(answer);
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(map[i][j].water + " ");
            }
            System.out.println();
        }
    }
}
