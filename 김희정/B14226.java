import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
[BOJ] 14226 이모티콘
 */
public class B14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[1001][1001]; //이모티콘, 클립보드
        q.add(new int[]{1, 0, 0}); //이모티콘, 클립보드, 시간
        visited[1][0] = true;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int emoji = curr[0];
            int clip = curr[1];
            int time = curr[2];
            if(emoji == S){
                System.out.println(time);
                return;
            }
            //클립보드 저장
            q.add(new int[]{emoji, emoji, time+1});

            //붙여넣기
            if(clip != 0 && emoji + clip <= S && !visited[emoji + clip][clip]){
                q.add(new int[]{emoji + clip, clip, time+1});
                visited[emoji+clip][clip] = true;
            }

            //화면 삭제
            if(emoji > 0 && !visited[emoji-1][clip]){
                q.add(new int[]{emoji-1, clip, time+1});
                visited[emoji-1][clip] = true;
            }

        }

    }
}
