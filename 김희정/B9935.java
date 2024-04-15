import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// [BOJ] 9935 문자열 폭발
// 87692KB |	524ms발
public class B9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String bomb = br.readLine();
        int sLength = s.length();
        int bombSize = bomb.length();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < sLength; i++){
            boolean isBomb = true;
            stack.push(s.charAt(i));

            if(stack.size() >= bombSize) {
                // stack의 마지막에 추가된 문자가 bomb의 마지막 문자일 경우라고 생각
                for (int j = 0; j < bombSize; j++) {
                    if (stack.get(stack.size() - bombSize + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }
                if(isBomb){
                    for(int j = 0; j < bombSize; j++){
                        stack.pop();
                    }
                }
            }
        }


        //스택에 남아있는 문자열 합치기
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < stack.size(); i++){
            sb.append(stack.get(i));
        }

//        if(sb.isEmpty()){
        // StringBuilder의 isEmpty()는 java 15부터 추가됨.
        if(sb.length() == 0){
            sb.append("FRULA");
        }
        System.out.println(sb);
    }
}
