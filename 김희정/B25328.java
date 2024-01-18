import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class B25328 {
    static int k;
    static char[] combi;
    static Map<String, Integer> map = new TreeMap<>();
    static void DFS(int L, int s, String target){
        if(L==k){
            String sc = "";
            for (char c : combi) {
                sc += c;
            }
            map.put(sc, map.getOrDefault(sc,0) + 1);
        }else{
            for(int i = s; i < target.length(); i++){
                combi[L] = target.charAt(i);
                DFS(L+1, i+1, target);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String X = br.readLine();
        String Y = br.readLine();
        String Z = br.readLine();
        k = Integer.parseInt(br.readLine());
        combi = new char[k];
        DFS(0,0,X);
        DFS(0,0,Y);
        DFS(0,0,Z);

        int cnt = 0;
        for(String c : map.keySet()){
            if(map.get(c) >= 2){
                System.out.println(c);
                cnt++;
            }
        }

        if (cnt == 0) {
            System.out.println(-1);
        }

    }
}
