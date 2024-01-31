import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_S3_25328_문자열_집합_조합하기 {

    static boolean[][] visited = new boolean[3][];
    static String[] strList = new String[3];
    static int num;
    static HashMap<String, Integer> map = new HashMap<>();


    //조합 nCr
    //파라미터로 문자열, 체크용 부울배열, r개 선택, depth 깊이
    public static void comb(String str, boolean[] visited, int r, int depth) {
        //r이 0 즉 r개를 선탣한 경우
        if(r == 0) {
            //string append로 붙일 수 있음
            StringBuilder sb = new StringBuilder("");
            //선택한 문자를 string에 추가
            for(int i=0; i<str.length();i++) {
                if (visited[i]) sb.append(str.charAt(i));
            }
            //string타입으로 변환
            String tmp = sb.toString();

            //hashmap으로 key,value값을 추가
            //map.getOrDefault는 키 값이 있으면 value값, 없으면 default값 반환 + 1
            map.put(tmp, map.getOrDefault(tmp, 0)+1);

            return;
        }

        //i는 depth부터 시작 방문 하지 않으면 방문 후 현재 위치 다음인 i+1부터 백트래킹
        for(int i=depth; i< str.length(); i++) {
            if(!visited[i]){
                visited[i] = true;
                comb(str, visited, r-1, i+1);
                visited[i] = false;
            }
        }
    }
//    char[]를 리스트에 집어 넣는 방식
//    static void print(String arr, boolean[] visited, int n) {
//        char[] tmpCharList = new char[num];
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            if (visited[i])
//                tmpCharList[count++] = arr.charAt(i);
//        }
//        tmpList.add(String.valueOf(tmpCharList));
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 3문자열 입력
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            strList[i] = st.nextToken();
            visited[i] = new boolean[strList[i].length()];
        }

        // n입력
        st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());

        // 조합하기 최대 2^17 13만
        for (int i = 0; i < 3; i++) {
            comb(strList[i], visited[i],  num, 0);
            // System.out.println(tmpList.size());
        }

        //리스트
        List<String> result = new ArrayList<>();
        //value값이 2이상만 리스트에 저장
        for (String s : map.keySet()) {
            if(map.get(s)>=2) result.add(s);
        }

        //리스트에 값이 없으면 -1
        if(result.isEmpty()) System.out.println(-1);
        //있으면 출력
        else {
            Collections.sort(result);
            for (String s : result) {
                System.out.println(s);
            }
        }
//
//        // n번 앞과 뒤에서 찾은 인덱스가 다르면 set에 9
//        for (String str : tmpList) {
//            if (tmpList.indexOf(str) != tmpList.lastIndexOf(str))
//                set.add(str);
//        }

//        for(int i=0; i<tmpList.size();i++) {
//            for(int j=0; j<i;j++) {
//                if(tmpList.get(i).equals(tmpList.get(j))) {
//                    set.add(tmpList.get(i));
//                }
//            }
//        }

//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        // 사이즈가 0이면 -1
//        if (set.size() == 0) {
//            bw.write("-1");
//            bw.flush();
//            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//            long secDiffTime = (afterTime - beforeTime) / 1000; // 두 시간에 차 계산
//            System.out.println("시간차이(m) : " + secDiffTime);
//            return;
//        }
//
//        // 정렬 후 출력 nlogn
//        List<String> result = new ArrayList<>(set);
//        Collections.sort(result);
//        for (String i : result) {
//            bw.write(i + "\n");
//            bw.flush();
//        }
//    }

}
}

