/**BOJ_16120_PPAP 24420KB 184ms
 * https://www.acmicpc.net/problem/16120
 * IDEA
 *	PPAP를 만났다고 치자.
 *	PPAPPAP 였을 수는 없음
 *	PPPAPAP 는 가능
 *		P"PPAP"AP -> P"P"AP
 *	"PPAP"PAP 경우도 가능
 *		"PPAP"PAP -> "P"PAP
 *
 *코드가 너무 조잡하다. 다시 풀어봐야겠다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16120_PPAP {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		char[] rawStr = br.readLine().toCharArray();
		char[] buffer = new char[rawStr.length];
		char[] pattern = {'P','P','A','P'};
		int lastIndex = 3;//last index of the pattern to be matched
		int[] ff = {0, 1, 0, 1};//failure function
		int m = 0;//cursor in parttern
		int b = 0;//cursor for current
		int rawLength = rawStr.length;
		for(int n = 0; n < rawLength;) {
			if(rawStr[n] == pattern[m]) {
				if(m == lastIndex) {//found match
						b -= lastIndex; //buffer[b] = 무조건 P
						m = (b > 0 && buffer[b - 1] == 'P') ? 1 : 0;// ? PPAP 의 두 번째 P 를 가리킴 : 첫 번째 P를 가리킴
						m++;
				}else {
					buffer[b] = rawStr[n];
					m++;
				}
			}else {
				if(m != 0) {
					m = ff[m - 1];//땡기기
					continue;//다시 이자리에서 매칭 시작
				}else {
					buffer[b] = rawStr[n];//첫 패턴부터 안 맞는 경우임. 그냥 써넣고 갈길 간다.
				}
			}
			n++;
			b++;

		}
		if(b != 1 || buffer[0] != 'P') System.out.println("NP");
		else System.out.println("PPAP");
	}
}
