import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] 1062 G4 가르침
13084KB |	300ms

필수 : a,n,t,i,c
k < 5 : 읽을 수 있는 단어 갯수 = 0
1. k-5만큼의 알파벳을 뽑음(조합) => boolean배열로 관리
2. n개의 단어를 읽을 수 있는지 완탐으로 확인

 */
public class B1062 {
    static int N;
    static int K;
    static String[] word;
    static boolean[] alphabet;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K < 5){
            System.out.println(0);
            return;
        }
        alphabet = new boolean['z'+1];
        alphabet['a'] = true;
        alphabet['n'] = true;
        alphabet['t'] = true;
        alphabet['i'] = true;
        alphabet['c'] = true;

        word = new String[N];
        for(int i = 0; i < N; i++){
            word[i] = br.readLine();
        }
        answer = 0;
        //k-5개의 알파벳 뽑음
        comb(0, 'b');

        System.out.println(answer);

    }
    static void comb(int cnt, char start){
        if(cnt == K-5){
            int wordCnt = 0;
            //N개의 단어 중 읽을 수 있는 단어 갯수 구하기
            A:for (String w: word) {
                for(int i = 0; i < w.length(); i++){
                    char curr = w.charAt(i);
                    if(!alphabet[curr]) continue A;
                }
                wordCnt++;
            }
            answer = Math.max(wordCnt, answer);
            return;
        }


        for(char w = start; w <= 'z'; w++){
            if(alphabet[w]) continue;
            alphabet[w] = true;
            comb(cnt+1, (char)(w+1));
            alphabet[w] = false;
        }
    }
}
