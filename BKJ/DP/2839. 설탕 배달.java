import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이 문제는 DP or 그리디로 풀이 가능함. -> DP로 풀이
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(in.readLine());

        int[] memo=new int[N+1];
        
        if(N==3 || N==5) {
        	System.out.println(1);
        	return;
        }
        
        if(N==4 || N==7) {
        	System.out.println(-1);
        	return;
        }
        
        memo[3]=1; memo[5]=1;
        
        for(int i=6;i<=N;i++) {
        	// 5의 배수인 경우
        	if(i%5==0) {
        		memo[i]=memo[i-5]+1;
        	}
        	// 5의 배수는 아니지만 3의 배수인 경우
        	else if(i%3==0) {
        		memo[i]=memo[i-3]+1;
        	}
        	// 5,3의 배수가 아닌 경우
        	else {
        		// 4와 7을 제외하고는 모든 값을 만들 수 있으므로, memo[i-3], memo[i-5] 가 0인 경우를 판별하는 조건을 넣을 필요가 없다.
            	memo[i]=Math.min(memo[i-3], memo[i-5])+1;
        	}
        }
        
        System.out.println(memo[N]);
    }
}