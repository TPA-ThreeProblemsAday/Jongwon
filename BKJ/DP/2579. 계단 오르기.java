import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		int[] arr=new int[N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i]=Integer.parseInt(in.readLine());
		}
		
		int[] memo=new int[N+1];
		memo[1]=arr[1];
		if(N>=2) memo[2]=arr[1]+arr[2];
		
		for(int i=3; i<=N; i++) {
			memo[i]=Math.max(memo[i-3] + arr[i-1], memo[i-2])+ arr[i];
		}
		System.out.println(memo[N]);
		
	}
}
