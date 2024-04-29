package main;

import java.util.*;
import java.io.*;

class boj16120{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack();

        // 스택에 담고 스택이 4이상이면 꺼내서 확인 ppap면 p로 변경
        for(int i=0;i<s.length();i++){
            stack.add(s.charAt(i));
            if(stack.size() >= 4){
                String st = "";
                for(int j=0;j<4;j++) {
                    st = stack.pop() + st;
                }
                if(st.equals("PPAP")) {
                    stack.add('P');
                } else {
                    for(int j=0;j<4;j++) stack.add(st.charAt(j));
                }
            }
        }

        if(stack.size()==1 && stack.peek()=='P') {
            System.out.println("PPAP");
        }else{
            System.out.println("NP");
        }
    }
}