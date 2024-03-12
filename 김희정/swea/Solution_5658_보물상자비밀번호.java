package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;

//28,256 kb |   183 ms
public class Solution_5658_보물상자비밀번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            Set<String> numbers = new TreeSet<>(Collections.reverseOrder());
            for (int i = 0; i < N / 4; i++) {   //회전수
                for (int j = i; j < N - N/4 + i; j+=N/4) {
                    String num = s.substring(j, j + N / 4);
                    numbers.add(num);
                }
                String end = s.substring(N-N/4 + i) + s.substring(0,i);
                numbers.add(end);
            }
            //k번째로 큰 수
            List<String> stringList = numbers.stream().collect(Collectors.toList());

            sb.append('#').append(test).append(' ').append(Integer.parseInt(stringList.get(K-1), 16)).append('\n');
        }
        System.out.println(sb);
    }
}
