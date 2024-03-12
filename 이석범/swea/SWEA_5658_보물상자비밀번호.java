package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;



/**
 *16진수가 적혀진 N개의 숫자
 *k번째로 큰 숫자
 *N의 개수는 8이상 28이하의 숫자
 *각 숫자의 크기는 N/4
 *로테이트는 N/4번만 하면 됨 
 *입력
 *T
 *N K
 *1B3B3B81F75E
 *27,732 kb, 176 ms
 */
public class SWEA_5658_보물상자비밀번호 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        
        //테스트 케이스 
        for(int t=1;t<=T;t++) {            
            //List<Integer> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            //num번 만큼 1칸씩 이동후 자르기
            int num = N / 4;

            String input = br.readLine();
            for(int j=0; j<num;j++) {
                //1칸씩 이동하는 것을 0번째를 맨 뒤로 보내보기
                input = input.substring(1) +input.charAt(0);
                //num의 길이만큼 자르기
                for(int i=0; i<N;i+=num) {
                    String password = input.substring(i, i+num);
                    //System.out.println(password);
                    //중복 체크를 위해 set에 넣기
                    set.add(Integer.parseInt(password, 16));
                }
            }
            //268435455 최대 2억이므로 int형으로도 가능
//            System.out.println(Integer.parseInt("FFFFFFF", 16));
            
            //넣은 값들을 다시 리스트에 저장후 내림차순 정령
            List<Integer> list = new ArrayList<>();
            set.forEach((o1) -> {
                list.add(o1);
            });
            Collections.sort(list, (o1, o2) -> o2 - o1);
            
            sb.append("#").append(t).append(" ").append(list.get(K-1)).append("\n");
        }
        System.out.println(sb);
    }
}
