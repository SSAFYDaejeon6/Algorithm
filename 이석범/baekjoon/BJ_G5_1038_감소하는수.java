package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author SEOK BEOM LEE
 *12984kb 120ms
 */
public class BJ_G5_1038_�����ϴ¼� {
	
	static int N;
	
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		
		if(N<=10) System.out.println(N);
		//�̰� �� 1022?
		else {
			//0��° �ڸ��� i�� ���
			for(int i=0; i<10;i++) {
				back(i, 1);
			}
			Collections.sort(list);
			//ũ�⺸�� Ŭ��� -1
			if(list.size()<=N) System.out.println(-1);
			else System.out.println(list.get(N));
		}
	}
	
	//���� �ڸ���
	static void back(long num, int idx) {
		
		//�ڸ����� 10�� ������ 987654321 ���� ū �� ����
		if(idx>10) return;
		
		//����Ʈ �ֱ�
		list.add(num);
		
		//���� ���� 10�� ���ϰ� �������ڸ��� ���ڸ� �ֱ�
		for(int i=0; i<num % 10;i++) {
			back((num*10)+i, idx+1);
		}
	}
}
