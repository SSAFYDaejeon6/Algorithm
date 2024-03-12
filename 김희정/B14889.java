import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//	26248KB | 	424ms
public class B14889 {
    static int n;
    static int[][] arr;
    static int[] output;
    static int min;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        output = new int[n/2];
        min = Integer.MAX_VALUE;
        visited = new boolean[n];
        visited[0] = true;
        comb(1, 1); //0번 사람은 무조건 포함
        System.out.println(min);


    }

    static void comb(int idx, int start){   //사람들의 번호 뽑기
        if(idx == n/2){
            int[] output2 = new int[n/2];
            int index = 0;
            for(int i = 0; i < n; i++){ //output에서 뽑지 않은 사람들 번호 저장
                if(!visited[i]){
                    output2[index] = i;
                    index++;
                }
            }
            int sum1 = calculateSum(output);    // 뽑은 팀의 능력치 계산
            int sum2 = calculateSum(output2);
            min = Math.min(min, Math.abs(sum1 - sum2));
            return;
        }

        for(int i = start; i < n; i++){
            output[idx] = i;
            visited[i] = true;
            comb(idx+1, i+1);
            visited[i] = false;
        }
    }

    static int calculateSum(int[] output){ // n/2C2 조합 계산
        int sum = 0;
        for(int i = 0; i < n/2; i++){   //Sij + Sji 능력치 더하기
            for(int j = i+1; j < n/2; j++){
                sum += arr[output[i]][output[j]] + arr[output[j]][output[i]];
            }
        }
        return sum;
    }
}
