import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
[BOJ] 18111 마인크래프트
34876KB |   588ms

N*M 크기의 집터 (1<=M,N<=500)
목표 : 모든 땅의 높이를 모두 동일하게 하는 최소 시간과 그 경우 땅의 높이 > 답이 여러 개면 높이가 가장 높은 것
땅고르기 작업
1. 좌표 i,j 가장 위에 있는 블록을 제거해 인벤토리에 넣기 -> 2초
2. 인벤토리에서 블록 하나 꺼내어 좌표 i,j의 가장 위에 있는 블록 위에 넣기   -> 1초
땅의 높이는 256블록을 초과 불가

풀이 : 땅의 높이 min~max일 때 모두 구하기
 */
public class B18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int max = 0;
        int min = 256;
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j]= Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        //땅의 높이 min~max일 때 모두 구하기
        int minTime = Integer.MAX_VALUE;
        int height = 0;
        for(int h = min; h <= max; h++){
            int time = 0;
            int tempB = B;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == h) continue;    //이미 땅고르기 된 경우
                    if(map[i][j] < h ){
                        //블록 제거 및 인벤에서 빼기
                            tempB -= (h - map[i][j]);
                            time += (h - map[i][j]);
                    }
                    else{   //블록 추가
                        tempB += (map[i][j] - h);
                        time += (2*(map[i][j] - h));
                    }

                }
            }
            if(tempB < 0) break;
            if(minTime >= time){
                minTime = time;
                height = h;
            }
        }
        System.out.println(minTime + " " + height);
    }
}
