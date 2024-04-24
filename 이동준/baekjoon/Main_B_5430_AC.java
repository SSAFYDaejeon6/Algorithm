/** Main_B_5430_AC 77832KB 568ms
 * T<100
 * p 길이 <= 10 만
 *  배열 길이 <= 10만
 *  IDEA
 *  	포인터 두개로 구현 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_5430_AC {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int eachCase = 1; eachCase <= T; eachCase++) {
			String p = br.readLine();
			br.readLine(); // n 무시
			String numStr = br.readLine();
			numStr = numStr.substring(1, numStr.length() - 1);
			String[] numArr = (numStr.length() == 0) ? new String[0] : numStr.split(",");
			int[] cursors;
			int curLength;
			cursors = new int[] {-1, numArr.length};
			curLength = cursors[1] - cursors[0] - 1; 
			int curCur = 0;
			
			int toGo = p.length();
			boolean error = false;
			sim : for(int i = 0; i < toGo; i++) {
				switch (p.charAt(i)) {
				case 'R'://Reverse
					curCur = (curCur + 1) % 2;
					break;
				default://'D' Delete
					if(Math.abs(cursors[0] - cursors[1]) == 1 ) {
						error = true;
						break sim;
					}
					if(curCur == 0) {//
						cursors[0]++;
					}else cursors[1]--;
					curLength--;
				}
			}
			if(error) {
				System.out.println("error");
			}else {
				StringBuilder sb = new StringBuilder();
				sb.append('[');
				cursors[0]++;
				cursors[1]--;
				int direction = (curCur == 0) ? 1 : -1;
				for(int i = 0; i < curLength;i++) {
					sb.append(numArr[cursors[curCur] + direction * i]).append(',');
				}
				if(curLength > 0) sb.deleteCharAt(sb.length() - 1);
				sb.append(']');
				System.out.println(sb.toString());
			}
		}
	}
}
