import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11620KB 80ms
public class BJ_S5_7568_덩치 {

    static int N;

    static class Node {
        int weight;
        int height;

        public Node(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Node[] nodeList = new Node[N];

        for(int i=0; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            nodeList[i] = new Node(weight, height);
        }

        for(int i=0; i<N; i++) {
            int cnt = 1;
            for(int j=0; j<N; j++) {
                if(i==j) continue;
                //자기보다 큰 덩치
                if(nodeList[i].weight < nodeList[j].weight && nodeList[i].height < nodeList[j].height) {
                    cnt++;
                }
            }
            System.out.print(cnt+" ");
        }

    }
}
