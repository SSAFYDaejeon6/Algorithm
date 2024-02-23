import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// [BOJ]  16918 봄버맨
// 47864KB  |	232ms
public class B16918 {

    static int R;
    static int C;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = s.charAt(j);
            }
        }

        //로직
        if(N % 2 == 0){ //짝수는 무조건 모든 칸이 폭탄
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    sb.append('O');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            return;
        }

        for(int turn = 1; turn < N; turn+=2){
            List<int[]> bomb = new ArrayList<>();   //폭탄 정보 저장
            //완탐
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    if(map[i][j] == '.') map[i][j] = 'O';   //빈칸은 0으로 채우기
                    else{
                        bomb.add(new int[]{i,j});
                    }
                }
            }

            //폭탄 터뜨리기
            for (int[] b : bomb) {
                int x = b[0];
                int y = b[1];
                map[x][y] = '.';
                for(int d = 0; d < 4; d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(!inRange(nx,ny)) continue;
                    if(map[nx][ny] == '.') continue;
                    map[nx][ny] = '.';

                }
            }

        }

        //출력
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static boolean inRange(int x, int y){
        return 0 <= x && x < R && 0 <= y && y < C;
    }


}
