import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 26452kb, 240ms
 */
public class BJ_S2_16953_AtoB {
    static int n, end;

    //큐에 저장하기 위한 클래스
    //숫자가 10억까지인데 1을 추가하면 100억까지 되므로 롱타입으로 구성
    //롱타입의 숫자는
    static class Node {
        long num;
        int depth;
        Node(long num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    //간단하게 너비우선탐색으로 찾은 값이 나오면 즉시 종료
    static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(n, 1));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            long curNum = node.num;
            int depth = node.depth;
            if(node.num == end) return depth;

            if(curNum*2 <= end) queue.offer(new Node(curNum*2, depth+1));

            long plusOne = Long.parseLong(curNum+"1");
            if(plusOne <= end) queue.offer(new Node(plusOne, depth+1));
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        end = sc.nextInt();

        int result = bfs();
        System.out.println(result);
    }
}
