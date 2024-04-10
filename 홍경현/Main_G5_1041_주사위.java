package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//11544kb 76ms
public class Main_G5_1041_주사위 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long arr[] = new long[6];
		long res = 0;
		
		//1면만 보이는 경우 : 6면 중 최소값
		long sum1 = 51;
		for(int i=0; i<6; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum1 = Math.min(arr[i], sum1);
		}
		
		//N이 1일 때, arr 정렬 후 최대값 제외하고 더함
		if(N==1) {
			Arrays.sort(arr);
			for(int i=0; i<5; i++) res+=arr[i];
			System.out.println(res);
			return;
		}
		
		//2면이 보이는 경우 : 평행하지 않는 면의 합 중 최소값
		long sum2 = 101;
		for(int i=0; i<6; i++) {
			for(int j=i+1; j<6; j++) {
				//(0,5) (1,4) (2,3)이 평행함
				if(i+j==5) continue;
				long temp = arr[i]+arr[j];
				sum2 = Math.min(sum2, temp);
			}
		}
		
		//3면이 보이는 경우 : 각 평행한 구간마다 최소값의 합
		long sum3 = Math.min(arr[0], arr[5]) 
					+ Math.min(arr[1], arr[4])
					+ Math.min(arr[2], arr[3]);
		
		//3면이 보이는 경우 : 상위 끝 모서리 4개
		res = (4*sum3);
		//2면이 보이는 경우
		//1) 상위 면 모서리 중 끝 모서리를 제외한 테두리 (N-2)*4
		//2) 옆 4면의 첫 줄 제외한 테두리 (N-1)*4
		res += (N-2)*sum2*4 + (N-1)*sum2*4;
		//1면이 보이는 경우
		//1) 상위 모서리를 제외한 가운데 (N-1)(N-2)
		//2) 옆 4면에서 모서리를 제외한 가운데, 하단을 보이지 않으므로 밑줄도 포함 (N-1)(N-2)*4
		res += (N-2)*(N-2)*sum1 + (N-1)*(N-2)*4*sum1;
		
		System.out.println(res);
	}

}
