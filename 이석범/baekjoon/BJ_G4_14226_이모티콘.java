package test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author SEOK BEOM LEE
 *	17500kb 124ms
 */
public class BJ_G4_14226_�̸�Ƽ�� {

	static int n;

	//�ߺ��Ͽ� �� ��� �޸� �ʰ��� ��
	static boolean[][] visited = new boolean[2001][2001];

	static class Node {
		int current;
		int copy;

		Node(int current, int copy) {
			this.current = current;
			this.copy = copy;
		}

		@Override
		public String toString() {
			return "Node [current=" + current + ", copy=" + copy + "]";
		}
		
	}


	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(1, 0));
		visited[1][0] = true;

		int time = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();

			while(--size>=0) {
				Node node = queue.poll();
				int current = node.current;
				int copy = node.copy;

				if(current==n) {
					System.out.println(time);
					return;
				}
				//Ŭ�� ���� ����
				if(!visited[current][current]) {
					queue.offer(new Node(current, current));
					visited[current][current] = true;
				}
				//�ٿ��ֱ�
				//�ִ� 2000�������� ���� �� ����
				if(copy!=0 && (current+copy<=2000) && !visited[current+copy][copy]) {
					queue.offer(new Node(current+copy, copy));
					visited[current+copy][copy] = true;
				}
				//�̸�Ƽ�� ����
				if(current!=0 && !visited[current-1][copy]) {
					queue.offer(new Node(current-1, copy));
					visited[current-1][copy] = true;
				}

			}

			time++;
		}


	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		BFS();

	}
}
