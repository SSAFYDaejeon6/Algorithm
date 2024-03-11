package BaekJoon;
import java.util.Scanner;

public class BOJ_N2477_참외밭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		final int line = 6;
		int[] dir = new int[line];   // ���� �Է� �迭
		int[] lens = new int[line];  // ���� �Է� �迭
		int res = N;
		
		for(int i=0;i<line;i++) {
			dir[i] = sc.nextInt();
			lens[i] = sc.nextInt();
		}
		
		// 2�� ��ȸ���� ��, �ΰ��� ������ 2�� �ݺ��Ǵ� ��찡 ���� �簢��
		for(int i=0;i<line*2;i++) {
			int j1 = i%line, j2 = (i+1)%line, j3 = (i+2)%line, j4 = (i+3)%line;
			if(dir[j1] == dir[j3] && dir[j2] == dir[j4]) {   //���� �簢���� ���� ã�� ���
				res *= (lens[j1] + lens[j3])*(lens[j2] + lens[j4]) - (lens[j2]*lens[j3]);
				//��ü �簢������ ���� �簢���� ���� ���� + ���� ������ �� ���� ����
				break;
			}
		}
		System.out.println(res);
	}
}
