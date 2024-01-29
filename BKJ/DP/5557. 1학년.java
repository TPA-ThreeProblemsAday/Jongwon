import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DP 경우의 수 문제
public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); 

		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		long[][] dp=new long[N][21]; // dp[i][j]= i를 고려하여 j를 만들 수 있는 경우의 수
		dp[0][arr[0]]=1; // 첫번 째 숫자만으로 만들 수 있는 경우의 수는 1
		
		for(int i=1;i<N-1;i++) {
			// 중간에 나오는 수가 0 이상 20이하여야 하므로
			for(int j=0;j<=20;j++) {
				int plus=j+arr[i];
				int minus=j-arr[i];
				// 만들어 질 수 있는 모든 경우 갱신
				if(0<=plus && plus<=20) {
					dp[i][j]+=dp[i-1][plus];
				}
				if(0<=minus && minus<=20) {
					dp[i][j]+=dp[i-1][minus];
				}
			}
		}		
		// '=' 앞의 수 까지 고려하여 '=' 뒤에 수를 만들 수 있는 경우의 수
		System.out.println(dp[N-2][arr[N-1]]);
	}
}