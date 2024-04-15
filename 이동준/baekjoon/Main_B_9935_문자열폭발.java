import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Main_B_9935_문자열폭발 31340KB 224ms
아이디어
	byte [] previous로 해당 문자가 subString의 몇 번째 문자인지 기록한다.
	즉, 주어진 String의 커서 sCursor에 대해 sCursor == pattern[0] 이면, 대응되는 자리에0 기록. 그다음에도 매칭이면 1 기록. 그다음 매칭이면 2...기록.
	그러다가 중간에 패턴 매칭이 끊기면 -1 기록. 다시 pattern[0] 나올 때까지 -1 계속 기록. 
	패턴 길이 N에 대해 N - 1을 기록하는 순간, previous 위의 Cursor pCursor를 N 만큼 뒤로 이동. 그후 sCursor 1 이동켜서 pCursor 값과 비교해서 pCursor 값이 1 더 크면 해당 값 유지.
	아니면 -1로 기록. 
 * 
 */

public class Main_B_9935_문자열폭발 {
	static byte[] byteBuffer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] sequence = br.readLine().toCharArray();
		char[] outputSequence = new char[sequence.length];
		char[] pattern = br.readLine().toCharArray();
		byte tPIdx = (byte) (pattern.length - 1);
		byteBuffer = new byte[sequence.length];
		byte pCursor = 0;
		int bCursor = 0;
		for(int sCursor = 0; sCursor < sequence.length; sCursor++) {
			outputSequence[bCursor] = sequence[sCursor];
			if(pattern[pCursor] == sequence[sCursor]) byteBuffer[bCursor] = pCursor;
			else{
				pCursor = (pattern[0] == sequence[sCursor]) ? 0 : (byte) -1;
				byteBuffer[bCursor] = pCursor;
			}
			if(pCursor == tPIdx) {//문자열 뿌수기
				bCursor -= pattern.length;
				pCursor = (bCursor < 0) ? -1 : byteBuffer[bCursor];
			}
			pCursor++;
			bCursor++;
		}
		if(bCursor == 0) System.out.println("FRULA");
		else {
			char[] resultOne = new char[bCursor];
			System.arraycopy(outputSequence, 0, resultOne, 0, bCursor);
			StringBuilder sb = new StringBuilder();
			sb.append(resultOne);
			System.out.println(sb.toString());
		}
	}

}
