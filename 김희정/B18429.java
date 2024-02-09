import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] 18429. 근손실
내풀이 : 백트래킹 순열을 이용해 운동 키트를 사용하는 모든 경우의 수 구함
 */
public class B18429 {

    static int n;
    static int k;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;
        int[] kit = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            kit[i] = Integer.parseInt(st.nextToken());
        }

        int[] output = new int[n];
        perm(kit, output, new boolean[n], 0, n, n); //백트래킹 순열 : n개 중 중복이 허용되지 않도록 r개를 뽑아서 일렬로 세우는 경우의 수
        System.out.println(answer);
    }

    static void checkWeight(int[] output){
        int weight = 500;
        for(int i = 0; i < n; i++){
            int w = output[i];
            weight -= k;
            weight += w;
            if(weight < 500){
                return;
            }
        }
        answer++;
    }

    static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            //근손실 판단
            checkWeight(output);
        }
        else{
            for(int i = 0; i < n; i++){
                if(!visited[i]){
                    visited[i] = true;
                    output[depth] = arr[i];
                    perm(arr,output, visited, depth + 1, n, r);
                    visited[i] = false; //원상복귀
                }
            }
        }
    }
}
