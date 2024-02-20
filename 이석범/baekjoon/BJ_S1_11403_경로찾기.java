import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
0 -> 3 -> 4,5
//100*100*100 = 100만
플로이드 워셜 for문 k -> r -> c순으로
혹은 dfs나 비트마스킹 방법도 있음
18708kb 312ms
 */
public class BJ_S1_11403_경로찾기 {
    static int[][] graph;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];

        for(int r=0; r<n;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<n;c++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    graph[r][c] = 1;
                }
            }
        }
//        for (int[] lines : graph) {
//            for (int line : lines) {
//                System.out.print(line+" ");
//            }
//            System.out.println();
//        }
        for(int k=0;k<n;k++) {
            for(int r=0;r<n;r++) {
                for(int c=0;c<n;c++) {
                    if(graph[r][k] == 1 && graph[k][c] == 1) {
                        graph[r][c] = 1;
                        //System.out.println(r + " " + c);
                    }
                }
            }
        }

        for (int[] lines : graph) {
            for (int line : lines) {
                System.out.print(line+" ");
            }
            System.out.println();
        }





    }
}
