import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [BOJ] 16120 PPAP
//	20280KB |	164ms
public class B16120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int pCnt = 0; //A를 만나기 전 p의 갯수
        for(int i = 0 ;i < input.length(); i++){
            char curr = input.charAt(i);
            if(curr == 'A'){
                if(i < input.length()-1 && pCnt >= 2 && input.charAt(i+1) == 'P'){
                    pCnt--;
                    i++;
                }else{
                    System.out.println("NP");
                    return;
                }
            }
            else pCnt++;

        }
        if(pCnt == 1){
            System.out.println("PPAP");
        }else{
            System.out.println("NP");
        }
    }
}
