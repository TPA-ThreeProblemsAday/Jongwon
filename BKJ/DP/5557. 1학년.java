import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DP ����� �� ����
public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); 

		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		long[][] dp=new long[N][21]; // dp[i][j]= i�� ����Ͽ� j�� ���� �� �ִ� ����� ��
		dp[0][arr[0]]=1; // ù�� ° ���ڸ����� ���� �� �ִ� ����� ���� 1
		
		for(int i=1;i<N-1;i++) {
			// �߰��� ������ ���� 0 �̻� 20���Ͽ��� �ϹǷ�
			for(int j=0;j<=20;j++) {
				int plus=j+arr[i];
				int minus=j-arr[i];
				// ����� �� �� �ִ� ��� ��� ����
				if(0<=plus && plus<=20) {
					dp[i][j]+=dp[i-1][plus];
				}
				if(0<=minus && minus<=20) {
					dp[i][j]+=dp[i-1][minus];
				}
			}
		}		
		// '=' ���� �� ���� ����Ͽ� '=' �ڿ� ���� ���� �� �ִ� ����� ��
		System.out.println(dp[N-2][arr[N-1]]);
	}
}