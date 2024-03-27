package Algorithm.Study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*11608kb 80ms
 * [문제 해석]
	휴게소 N개
	휴게소 위치 : 고속도로 시작부터 얼만큼 떨어져 있는지
	M개를 더 세우려고 함
	
	이미 휴게소가 있는 곳에 또 세울 수 없음
	고속도로의 끝에 휴게소 세울 수 없음
	정수 위치에만 세울 수 있음
	
	고속도로 이용해 모든 휴게소 방문
	M개를 더 지어서 휴게소 없는 구간의 길이의 최댓값을 최소로
	반드시 M개!!!
	
	[입력]
	1. N M L
	2. 휴게소 위치
	
	[출력]
	휴게소 없는 구간의 최댓값의 최솟값
	
	[제한]
	0<=N<=50
	1<=M<=100
	100<=L<=1000
	1<=휴게소 위치<=L-1
	N+M < L

 */
public class Main_G4_1477_휴게소세우기 {
    static int N, M, L;
    static int arr[];
    static int result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        arr = new int[N+2];
        arr[N+1] = L;
        if(N>0){
        	st = new StringTokenizer(br.readLine());
        	for(int i=1; i<=N; i++) {
            	arr[i] = Integer.parseInt(st.nextToken());
            }
        }
        
        Arrays.sort(arr);
        
        int left = 1;
        int right = L-1;

        binarySearch(left, right);
        System.out.println(result);
    }
    
    //이분 탐색 - 휴게소 M개 짓기
	private static void binarySearch(int left, int right) {
		while(left<=right) {
        	int mid = (left+right)/2;
        	int sum = 0;

        	for(int i=1; i<N+2; i++) {
        		sum+=(arr[i]-arr[i-1]-1)/mid; //휴게소 설치
        	}
        	
        	if(sum>M) left = mid+1; //휴게소의 개수가 M보다 많으면 거리 증가
        	else right = mid-1; //M보다 적으면 거리 감소
        }
		
		result = left;
	}

}