import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	int n=Integer.parseInt(in.readLine());
    	
    	// n이 5보다 작은 경우
    	if(n<5) {
    		// 짝수일 때
    		if(n%2==0) {
    			System.out.println(n/2);
    		}
    		// 홀수일 때
    		else {
    			System.out.println(-1);
    		}
    		return;
    	}
    	
    	int q = n/5;
    	int r = n%5;
    	// 5로 나눈 나머지가 홀수인 경우
    	if(r%2 != 0) {
    		// 5원의 개수를 하나 줄인 후 계산
    		System.out.println(q-1+(r+5)/2);
    	}
    	// 짝수인 경우
    	else {
    		System.out.println(q+r/2);
    	}
    }
}