/**Main_B_1010_다리놓기_이동준 12880KB 104ms
 * 
 * Idea
 * 		조합 구하기임
 * 		n!
 * 		-
 * 		r! * (n - r!)
 * 		값이 무진장 커질 수 있기 때문에 BigInteger 사용
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_B_1010_다리놓기_이동준 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader("./b/1010.txt"));
		sb = new StringBuilder();
				
		int N = Integer.parseInt(br.readLine());
		String[] tsa;
		for(int i = 0; i < N; i++) {
			tsa = br.readLine().split(" ");
			sb.append(nCr(Integer.parseInt(tsa[1]), Integer.parseInt(tsa[0])));
			sb.append('\n');
		}
		br.close();
		System.out.println(sb.toString());
//		for(int i = 13; i < 31; i++) {
//			System.out.println(i + " " + fac(i));
//		}
//		System.out.println(Long.MAX_VALUE);
	}
	
	static BigInteger fac(int k) {
	    switch(k) {
	        case 0:
	            return BigInteger.ONE;
	        case 1:
	            return BigInteger.ONE;
	        case 2:
	            return BigInteger.valueOf(2);
	        case 3:
	            return BigInteger.valueOf(6);
	        case 4:
	            return BigInteger.valueOf(24);
	        case 5:
	            return BigInteger.valueOf(120);
	        case 6:
	            return BigInteger.valueOf(720);
	        case 7:
	            return BigInteger.valueOf(5040);
	        case 8:
	            return BigInteger.valueOf(40320);
	        case 9:
	            return BigInteger.valueOf(362880);
	        case 10:
	            return BigInteger.valueOf(3628800);
	        case 11:
	            return BigInteger.valueOf(39916800);
	        case 12:
	            return BigInteger.valueOf(479001600);
	        case 13:
	            return BigInteger.valueOf(6227020800L);   
	        case 14:
	            return BigInteger.valueOf(87178291200L);   
	        case 15:
	            return BigInteger.valueOf(1307674368000L);   
	        case 16:
	            return BigInteger.valueOf(20922789888000L);   
	        case 17:
	            return BigInteger.valueOf(355687428096000L);
	        case 18:
	            return BigInteger.valueOf(6402373705728000L);   
	        case 19:
	            return BigInteger.valueOf(121645100408832000L);   
	        case 20:
	            return BigInteger.valueOf(2432902008176640000L);
	        default:
	            return BigInteger.valueOf(k).multiply(fac(k - 1));
	    }
	}
	
	static BigInteger nCr(int n, int r) {
		return fac(n).divide(fac(n - r).multiply(fac(r)));
	}
	
}
