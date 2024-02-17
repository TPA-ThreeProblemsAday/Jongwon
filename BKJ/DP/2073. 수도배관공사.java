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
		
		int D=Integer.parseInt(st.nextToken()); // ��ġ�� �Ÿ�
		int P=Integer.parseInt(st.nextToken()); // ������ ������ ����
		
		// dp[x]=y -> �������� �Ÿ��� x�϶� ����ȭ�� �������� �뷮y 
		int[] dp=new int[D+1];
		dp[0]=Integer.MAX_VALUE;
		
		// i: ����� ������
		for(int i=0; i<P; i++) {
			st=new StringTokenizer(in.readLine()," ");
			int length=Integer.parseInt(st.nextToken());	// ������ ����
			int capacity=Integer.parseInt(st.nextToken());	// ������ �뷮
			
			// j: ����� �Ÿ�
			for(int j=D; j>=length; j--) {
				dp[j]=Math.max(dp[j], Math.min(dp[j-length], capacity));
			}
		}

		// ���
		System.out.println(dp[D]);
	}
}