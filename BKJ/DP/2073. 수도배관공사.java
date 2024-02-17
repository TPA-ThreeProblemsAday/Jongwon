import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int D=Integer.parseInt(st.nextToken()); // 설치할 거리
		int P=Integer.parseInt(st.nextToken()); // 구입한 파이프 개수
		
		// dp[x]=y -> 파이프의 거리가 x일때 최적화된 수도관의 용량y 
		int[] dp=new int[D+1];
		dp[0]=Integer.MAX_VALUE;
		
		// i: 고려할 파이프
		for(int i=0; i<P; i++) {
			st=new StringTokenizer(in.readLine()," ");
			int length=Integer.parseInt(st.nextToken());	// 파이프 길이
			int capacity=Integer.parseInt(st.nextToken());	// 파이프 용량
			
			// j: 고려할 거리
			for(int j=D; j>=length; j--) {
				dp[j]=Math.max(dp[j], Math.min(dp[j-length], capacity));
			}
		}

		// 출력
		System.out.println(dp[D]);
	}
}