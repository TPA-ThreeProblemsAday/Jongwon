import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*  재귀 호출 순서는 3으로 나누는 연산, 2로 나누는 연산, 1로 빼는 연산 순서로 호출해야 시간초과가 안뜬다.
	dp(N-1)을 가장 왼쪽에 놓으면 모든 -1 계산을 호출하므로 시간초과가 발생한다.
   	이전에 상향식으로 풀이했을 때는 시간이 148ms가 나왔는데, 여기서 하향식 재귀 함수로 풀이했을 때는 시간이 212ms가 나왔다.
*/
public class Main {

	private static int[] memo;
	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		memo=new int[N+1];
		Arrays.fill(memo, -1);
		memo[1]=0;
		if(N>=2) memo[2]=1;
		if(N>=3) memo[3]=1;
		System.out.println(dp(N));
	}
	
	private static int dp(int N) {
		if(memo[N]==-1) {
			if (N % 6 == 0) {
				memo[N] = Math.min(dp(N/3), Math.min(dp(N/2), dp(N -1))) + 1;
			}
			else if(N%3==0) {
				memo[N]=Math.min(dp(N/3),dp(N-1))+1;
			}
			else if(N%2==0) {				
				memo[N]=Math.min(dp(N/2),dp(N-1))+1;
			}
			else {
				memo[N]=dp(N-1)+1;
			}
		}
		return memo[N];
	}
}