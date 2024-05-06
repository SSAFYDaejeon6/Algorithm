import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//[BOJ] 1038 감소하는 수
// 11656KB | 	84ms수
public class B1038 {
    static List<Long> nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();   //감소하는 수를 list에 담아 N번째 수 출력

        if (N <= 10) {
            System.out.println(N);
            return;
        }
        if(N > 1022){ // 9876543210이 마지막 수
            System.out.println(-1);
            return;
        }

        for(int i = 0; i < 10; i++){
            decrease(i,1);
        }

        Collections.sort(nums);
        System.out.println(nums.get(N));




    }

    static void decrease(long num, int idx){
        if(idx > 10) return;

        nums.add(num);
        for(int i = 0; i < num % 10; i++){
            decrease((num * 10) + i, idx + 1);
        }
    }
}
