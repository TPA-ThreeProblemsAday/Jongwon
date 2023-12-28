import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		
		long[] memo=new long[n+1]; // n이 90까지 가능하므로 long으로 선언해야함.
		memo[0]=0;
		memo[1]=1;
		
		for(int i=2;i<=n;i++) {
			memo[i]=memo[i-1]+memo[i-2];
		}
		
		System.out.println(memo[n]);
	}
}