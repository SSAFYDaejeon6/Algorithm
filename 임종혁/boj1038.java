package main;
import java.io.*;
import java.util.*;

public class boj1038{
    private static List<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        if (n <=10){
            System.out.println(n);
        } else if (n> 1022) { // 2 10 (0,1,2,3,4,5,6,7,8,9)
           System.out.println("-1");
        }else{
            for(int i=1; i<10; i++){
                dfs(i,1);
            }
            Collections.sort(list);
            System.out.println(list.get(n));
        }

    }

    /// 1 1 0
    /// 2 20 21 210
    // 3 30 31 310 ...
    static void dfs(long num, int idx) {
        if (idx > 10) {
            return;
        }
        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            dfs((num * 10) + i, idx + 1);
        }
    }
}
