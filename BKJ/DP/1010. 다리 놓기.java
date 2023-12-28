import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이 문제는 조합 공식을 알아야 풀 수 있다. 
 * 아래 두가지 조합 성질을 점화식으로 활용하여 메모이제이션을 통해 DP로 풀이하면 된다.
 * nC0=nCn=1
 * nCr = n-1Cr + n-1Cr-1 (파스칼의 삼각형 공식)
 * N<=M 이므로, 동쪽이 n 서쪽이 r 
 */

public class Main {
	
	private static int[][] memo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		
		for(int i=0;i<T;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			// N<=M
			int N=Integer.parseInt(st.nextToken()); // 서쪽
			int M=Integer.parseInt(st.nextToken()); // 동쪽
			
			memo = new int[M+1][N+1];
			System.out.println(combination(M, N));
		}

	}
	private static int combination(int n, int r) {
		// 이미 계산한 값인 경우
		if(memo[n][r]!=0) return memo[n][r];
		
		// nC0=nCn=1 조건
		if(r==0 || n==r) return memo[n][r]=1;
		
		// nCr = n-1Cr + n-1Cr-1
		return memo[n][r]=combination(n-1, r)+combination(n-1, r-1);
		
	}
}