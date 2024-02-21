import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 13780KB  |	148ms
// [BOJ] 11403 경로 찾기
// 풀이 : adjMatrix[a][k] == 1 이고, adjMatrix[k][b] == 1일 때, a->b연결 가능
// 사이값 k를 모든 경우 다 넣기
public class B11403 {
    static int N;
    static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int k = 0; k < N; k++){
            for(int a = 0; a < N; a++){
                for(int b = 0; b < N; b++){
                    if(adjMatrix[a][k] ==  1 &&  adjMatrix[k][b] == 1){
                        adjMatrix[a][b] = 1;
                    }
                }
            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(adjMatrix[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
