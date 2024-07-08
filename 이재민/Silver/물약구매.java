package algo0708;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    완탐 기반 백트래킹
    17048KB | 284ms
 */

public class 물약구매 {
    static int N;
    static int[] lm;
    static List<Discount>[] dInfo;
    static int res = Integer.MAX_VALUE;
    static boolean[] visited;

    static class Discount {
        int x, price;
        public Discount(int x, int price) {
            this.x = x;
            this.price = price;
        }
    }

    static void dfs(int s, int money){
        if(money >= res) return;

        if(s == N+1) {
            res = Math.min(res, money);
            return ;
        }


        for(int i = 1; i<=N; i++){
            if(visited[i]) continue;

            visited[i] = true;
            for(int j=0; j<dInfo[i].size(); j++){
                lm[dInfo[i].get(j).x] -= dInfo[i].get(j).price;
            }

            dfs(s+1, money + (lm[i] <= 0 ? 1 : lm[i]));

            for(int j=0; j<dInfo[i].size(); j++){
                lm[dInfo[i].get(j).x] += dInfo[i].get(j).price;
            }

            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        lm = new int[N+1];
        visited = new boolean[N+1];
        dInfo = new List[N+1];

        for(int i=1; i<=N; i++){
            dInfo[i] = new ArrayList<>();
            lm[i] = Integer.parseInt(st.nextToken());
        }


        for(int i=1; i<=N; i++){
            int c = Integer.parseInt(br.readLine());
            for(int j=1; j<=c; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());
                dInfo[i].add(new Discount(x, price));
            }
        }

        dfs(1, 0);

        System.out.println(res);
    }
}