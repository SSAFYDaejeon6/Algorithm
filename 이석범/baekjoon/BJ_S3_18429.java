import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_18429 {

    static int[] weightList;
    static int n, k;
    static int count;
    static boolean[] visited;

    static void Exercise(int weight, int depth) {
        if(depth == n) {
            count++;
            return;
        }

        for(int i=0; i< n; i++) {
            int curWeight = weight + weightList[i] - k;
            if(!visited[i] && curWeight >= 500) {
                visited[i] = true;
                Exercise(curWeight, depth+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        weightList = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i< n; i++) {
            weightList[i] = Integer.parseInt(st.nextToken());
        }

        Exercise(500, 0);
        System.out.println(count);

    }
}
