import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] memo;	
	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		
		memo=new int[n+1];
		memo[1]=1;
		if(n>=2) memo[2]=3;
		if(n>=3) memo[3]=5;
		
		System.out.println(dp1(n));
		System.out.println(dp2(n));
	}
	
	// 상향식 구현
	private static int dp1(int n) {
		for(int i=4;i<=n;i++) {
			memo[i]=(memo[i-1] + 2*memo[i-2]) % 10007;
		}
		return memo[n];
	}
	
	// 하향식 구현
	private static int dp2(int n) {
		if(memo[n]!=0) {
			return memo[n];
		}
		else {
			return memo[n] = (dp2(n-1) + 2*dp2(n-2)) % 10007;
		}
	}
}