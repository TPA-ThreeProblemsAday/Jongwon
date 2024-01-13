import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int[] memo=new int[N];
		memo[0]=arr[0];
		for(int i=1;i<N;i++) {
			memo[i]=Math.max(memo[i-1]+arr[i], arr[i]);
		}
		
		Arrays.sort(memo);		
		System.out.println(memo[N-1]);
	}
}
