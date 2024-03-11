import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // ������ ũ��
		int[] arr=new int[N+1]; // ������ ���� �迭
		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=1;i<=N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		// DP ���̺�
		// dp[i][j]=�Ӹ���� ����, i=�������� ��ȣ, j=������ ��ȣ
		int[][] dp=new int[N+1][N+1];
		
		// ������ ���̰� 1�� ��� �ʱ�ȭ
		for(int i=1;i<=N;i++) {
			dp[i][i]=1;
		}

		// ������ ���̰� 2�� ��� �ʱ�ȭ
        for(int i=1; i<=N-1; i++) {
        	if(arr[i]==arr[i + 1]) {
        		dp[i][i+1]= 1;
        	}
        }

        // j: ������ ��ȣ
        for(int j=1; j<=N; j++){ 
        	// i: �������� ��ȣ
            for(int i=1; i<j; i++){
            	// ���۰� ���� ���� ����, �߰��� �ִ� ������ �Ӹ������ ���
            	if(arr[i]==arr[j] && dp[i+1][j-1]==1) {
            		dp[i][j]=1;
            	}
            }
        }
		
		int M=Integer.parseInt(in.readLine()); // ������ ����
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
		
		System.out.println(sb);	// ���
	}
}
