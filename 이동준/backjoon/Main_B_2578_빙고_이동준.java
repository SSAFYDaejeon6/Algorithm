/**Main_B_2578_빙고_이동준 13024KB 88ms
 * 
 *  bitmasking으로 처리한다
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_B_2578_빙고_이동준 {
	static BufferedReader br;
	static Map<Integer, int[]> rowMap;
	static Map<Integer, int[]> colMap;
	static Map<Integer, int[]> diagMap;
	final static int judgeBin = 0b11111;
	static int[] rows;
	static int[] columns;
	static int[] diags;
	static int count; //줄 생긴 수 count

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader("./b/2578.txt"));
		rowMap = new HashMap<Integer, int[]>(25);
		colMap = new HashMap<Integer, int[]>(25);
		diagMap = new HashMap<Integer, int[]>(9);
		rows = new int[5];
		columns = new int[5];
		diags = new int[2];
		count = 0;

		String[] tempStringArr;
		int temp = 0;
		for(int r = 0; r < 5; r++) {
			tempStringArr = br.readLine().split(" ");
			for(int c = 0; c < 5; c++) {
				temp = Integer.parseInt(tempStringArr[c]);
				rowMap.put(temp, new int[] {r, 0b10000 >> c});
				colMap.put(temp, new int[] {c, 0b10000 >> r});
				if(r == c) {// 좌상 -> 우하 대각선 처리
					diagMap.put(temp, new int[] {0, 0b10000 >> c});
				}
				if(r == 4 - c){// 좌하 -> 우상 대각선 처리
					diagMap.put(temp, new int[] {1,  0b10000 >> c});
				}
			}
		}
		int count2 = 0;
		int[] ta;
		for(int r = 0; r < 5; r++) {
			tempStringArr = br.readLine().split(" ");
			for(int call = 0; call < 5; call++) {
//				System.out.println(++count2);
				temp = Integer.parseInt(tempStringArr[call]);
				ta = rowMap.get(temp);
				rows[ta[0]] = rows[ta[0]] | ta[1];
				if(rows[ta[0]] == judgeBin) count++;
				ta = colMap.get(temp);
				columns[ta[0]] = columns[ta[0]] | ta[1];
				if(columns[ta[0]] == judgeBin) count++;
				ta = diagMap.get(temp);
				if(ta != null) {
					if(ta[1] == 0b00100) {// 가운데 요소에 대한 특수 처리
						for(int i = 0; i < 2; i++) {
							diags[i] = diags[i] | 0b00100;
							if(diags[i] == judgeBin) count++;
						}
					}else {
						diags[ta[0]] = diags[ta[0]] | ta[1];
						if(diags[ta[0]] == judgeBin) count++;
					}
				}
				if(count >= 3) {
					System.out.println(1 + 5 * r + call);
					System.exit(0);
				}
			}
		}
		System.out.println("no");
	}
}
