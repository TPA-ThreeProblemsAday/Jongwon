import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int C=Integer.parseInt(st.nextToken()); // �ø����� ���� ��
		int N=Integer.parseInt(st.nextToken()); // ȫ���� �� �ִ� ������ ����, N<=20
		
		int[] costs=new int[N]; // �� ������ ȫ�� ���, cost <= 100
		int[] customers=new int[N]; // ȫ�� ������� ���� �� �ִ� ���� ��, customer <=100
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			costs[i]=Integer.parseInt(st.nextToken());
			customers[i]=Integer.parseInt(st.nextToken());
		}
		
		// �� ���ÿ��� ���� �� �ִ� �� ���� 100�� �����̰�
		// ��� C���� �÷����ϴµ�, �� ���� ���� �������� ���� ����� �� ���� ���� �����Ƿ� -> c+100
		int[] dp=new int[C+100];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0]=0;
		
		// i: ������ ����
		for(int i=0;i<N;i++) {
			// j: �����
			for(int j=customers[i];j<C+100;j++) {
				if(dp[j-customers[i]] != Integer.MAX_VALUE)
					dp[j]=Math.min(dp[j], dp[j-customers[i]]+costs[i]);
			}
		}

		int answer=Integer.MAX_VALUE;
		// �ּ� C���� Ȯ���ؾ� �ϹǷ�
		for(int i=C;i<C+100;i++) {
			answer=Math.min(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
