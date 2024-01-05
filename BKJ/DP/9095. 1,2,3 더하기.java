import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

	private static int[] memo;
	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());

		int[] memo=new int[11];
		memo[1]=1;
		memo[2]=2;
		memo[3]=4;
		memo[4]=7;
		for(int i=5;i<11;i++) {
			memo[i]=memo[i-1] + memo[i-2] + memo[i-3];
		}
	
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			sb.append(memo[Integer.parseInt(in.readLine())]+"\n");
		}
		System.out.println(sb.toString());
	
	}
}