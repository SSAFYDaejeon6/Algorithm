package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * @author SSAFY
 *32172kb 264ms
 */
public class BJ_G5_2467_용액 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[] input = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int minNum = Integer.MAX_VALUE;

		int left = 0;
		int right = n-1;

		//투 포인터로 양 옆에서 가면서 보기
		int minLeft = 0;
		int minRight = n-1;
		while(left<right) {
//			System.out.println(left+" "+right);

			int leftNum = input[left];
			int rightNum = input[right];

			if(minNum>=Math.abs(leftNum+rightNum)) {
				minNum = Math.abs(leftNum+rightNum);
				minLeft = leftNum;
				minRight = rightNum;
				if(leftNum+rightNum<0) {
					left++;
				}
				else {
					right--;
				}
			}
			else {
				if(leftNum+rightNum<0) {
					left++;
				}
				else {
					right--;
				}
			}

		}
		System.out.print(minLeft+" "+minRight);
	}
}