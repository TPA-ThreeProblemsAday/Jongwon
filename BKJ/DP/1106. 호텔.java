import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int C=Integer.parseInt(st.nextToken()); // 늘리려는 고객의 수
		int N=Integer.parseInt(st.nextToken()); // 홍보할 수 있는 도시의 개수, N<=20
		
		int[] costs=new int[N]; // 각 도시의 홍보 비용, cost <= 100
		int[] customers=new int[N]; // 홍보 비용으로 얻을 수 있는 고객의 수, customer <=100
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			costs[i]=Integer.parseInt(st.nextToken());
			customers[i]=Integer.parseInt(st.nextToken());
		}
		
		// 각 도시에서 얻을 수 있는 고객 수는 100명 이하이고
		// 적어도 C명을 늘려야하는데, 더 많은 고객을 데려왔을 때의 비용이 더 작을 수도 있으므로 -> c+100
		int[] dp=new int[C+100];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0]=0;
		
		// i: 선택한 도시
		for(int i=0;i<N;i++) {
			// j: 사람수
			for(int j=customers[i];j<C+100;j++) {
				if(dp[j-customers[i]] != Integer.MAX_VALUE)
					dp[j]=Math.min(dp[j], dp[j-customers[i]]+costs[i]);
			}
		}

		int answer=Integer.MAX_VALUE;
		// 최소 C명을 확보해야 하므로
		for(int i=C;i<C+100;i++) {
			answer=Math.min(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
