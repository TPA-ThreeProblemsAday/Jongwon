import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // 수열의 크기
		int[] arr=new int[N+1]; // 수열을 담을 배열
		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=1;i<=N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		// DP 테이블
		// dp[i][j]=팰린드롬 여부, i=시작점의 번호, j=끝점의 번호
		int[][] dp=new int[N+1][N+1];
		
		// 수열의 길이가 1인 경우 초기화
		for(int i=1;i<=N;i++) {
			dp[i][i]=1;
		}

		// 수열의 길이가 2인 경우 초기화
        	for(int i=1; i<=N-1; i++) {
        		if(arr[i]==arr[i + 1]) {
        			dp[i][i+1]= 1;
        		}
       		}

        	// j: 끝점의 번호
        	for(int j=1; j<=N; j++){ 
        		// i: 시작점의 번호
            		for(int i=1; i<j; i++){
            			// 시작과 끝의 값이 같고, 중간에 있는 수열이 팰린드롬인 경우
            			if(arr[i]==arr[j] && dp[i+1][j-1]==1) {
            				dp[i][j]=1;
            			}
            		}
       		}
		
		int M=Integer.parseInt(in.readLine()); // 질문의 개수
		int a,b;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(in.readLine()," ");
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
            		if(dp[a][b]==1) {
            			sb.append("1\n");
            		}
            		else {
            			sb.append("0\n");
            		}
		}
		
		System.out.println(sb);	// 출력
	}
}
