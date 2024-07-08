import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
[BOJ] 24954 물약 구매
 24044KB | 	296ms
풀이 : 물약의 구매 순서에 따라 최소 비용이 결정되므로, 물약 구매 순서를 순열로 구함
- 할인될 때마다 코인 수를 줄였다가 다시 원상복귀하는 로직 필요
 */
public class B24954 {
    static class MedicineInfo{
        int idx;
        int coin;

        public MedicineInfo(int idx, int coin){
            this.idx = idx;
            this.coin = coin;
        }
    }

    static int N;
    static boolean[] isSelected;
    static int min;

    static List<MedicineInfo>[] medicineInfos;
    static int[] originalPrice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        originalPrice = new int[N+1];
        st = new StringTokenizer(br.readLine());
        medicineInfos = new List[N+1];

        for(int i = 1; i <= N; i++){
            originalPrice[i] = Integer.parseInt(st.nextToken());
            medicineInfos[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++){
            int discountInfoCnt = Integer.parseInt(br.readLine());
            for(int j = 0; j < discountInfoCnt; j++){
                st = new StringTokenizer(br.readLine());
                medicineInfos[i].add(new MedicineInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
        }
        min = Integer.MAX_VALUE;
        isSelected = new boolean[N+1];
        perm(0,0);
        System.out.println(min);
    }

    private static void perm(int cnt, int sum){
        if(sum > min) return;
        if(cnt == N){
            min = Math.min(min, sum);
            return;
        }

        for(int i = 1; i <= N; i++){
            if(isSelected[i]) continue;
            isSelected[i] = true;
            for(MedicineInfo m : medicineInfos[i]){
                originalPrice[m.idx] -= m.coin;
            }
            perm(cnt+1, originalPrice[i] <= 0 ? sum + 1 : sum + originalPrice[i]);
            isSelected[i] = false;
            for(MedicineInfo m : medicineInfos[i]){
                originalPrice[m.idx] += m.coin;
            }
        }
    }
}
