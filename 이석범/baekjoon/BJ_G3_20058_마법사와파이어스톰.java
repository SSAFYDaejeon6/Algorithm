import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *NxN격자
 *2^L의 칸을 각각 90도 회전, 인접한 칸 3이상아니면 얼음--
 *입력
 *N Q
 *NxN
 *Q의 개수
 * 이건 국어 지문 읽는 것도 아니고 그지 같은 문제
 *
 *
 * 75076kb 456ms
 *
 */
public class BJ_G3_20058_마법사와파이어스톰 {
    static int n, q, number;
    
    static int[][] graph;
    
    //이웃집 확인 & DFS이동 할 목적
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    
    //회전 포지션 잡기 -> 주의 사항 파라미터로 입력값 n의 제곱이 들어와야함
    static void getRotatePosition(int L) {
        for(int r=0; r<number; r+= L) {
            for(int c=0; c<number;c += L) {
                rotate(r, c, L);
            }
        }
    }

    //회전 회오리
    static void rotate(int r, int c, int num) {

        //90도 회전 하기 위해 담을 그래프 저장
        int[][] tmpGraph = new int[num][num];
        for(int i=0; i<num;i++) {
            tmpGraph[i] = Arrays.copyOfRange(graph[r+i], c, c+num);
        }

        //num, 0 -> 0, 0 ||| 0, 0 -> 0, num
        for(int x=0; x<num;x++) {
            for(int y=0; y<num;y++) {
                graph[r+x][c+y] = tmpGraph[num-1-y][x];
            }
        }

    }
    
    //이웃집 찾기
    static void checkNeighbor() {
        Queue<int[]> queue = new ArrayDeque<>();

        for(int r=0; r<number;r++) {
            for(int c=0; c<number;c++) {
                int cnt=0;
                for(int i=0; i<4;i++) {
                    int nr = r+dr[i];
                    int nc = c+dc[i];

                    //주의 사항 벽에는 얼음이 없다
                    if(nr < 0 || nr >= number || nc < 0 || nc >= number) {
                        continue;
                    }

                    if(graph[nr][nc] != 0) cnt++;
                }
                //얼음이 0인 경우를 추가하지 않으면 음수가 발생하기때문에 0이 아닌 것만 넣기
                if(cnt < 3 && graph[r][c] != 0) {
                    queue.offer(new int[] {r, c});
                }
            }
        }

        //얼음에서 1빼기
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            graph[arr[0]][arr[1]] -= 1;
        }

    }

    static int maxResult = 0;

    static int tmpResult = 0;

    //주변 얼음 탐색 메서드 호출될때마다 +1하고 최대값 저장
    static void DFS(int r, int c) {
        tmpResult++;
        for(int i=0; i<4;i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr >= number || nc < 0 || nc >= number || graph[nr][nc] == 0) continue;
            graph[nr][nc] = 0;
            DFS(nr, nc);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //n과 q
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        number = (int)Math.pow(2, n);


        graph = new int[number][number];
        
        //그래프
        for(int r=0;r<number;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<number;c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<q;i++) {
            int L = Integer.parseInt(st.nextToken());
            if(L!=0) getRotatePosition((int)Math.pow(2, L));
            checkNeighbor();
            //print();
        }


        int sum = 0;
        for (int[] line : graph) {
            //System.out.println(sum);
            sum += Arrays.stream(line).sum();
        }

        System.out.println(sum);

        for(int r=0; r<number;r++) {
            for(int c=0; c<number;c++) {
                if(graph[r][c] == 0 ) continue;
                graph[r][c] = 0;
                DFS(r, c);
                maxResult = Math.max(tmpResult, maxResult);
                tmpResult = 0;
            }
        }


        System.out.println(maxResult);

    }

    static void print() {
        for (int[] ints : graph) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
