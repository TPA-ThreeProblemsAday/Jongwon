import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// BigInteger 안쓰면 오버플로우 발생 -> long보다도 큰 값을 처리할 때 사용
//  nC0=nCn=1, nCr = n-1Cr + n-1Cr-1
public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		BigInteger[][] memo=new BigInteger[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			for(int j=0;j<=i;j++) {
				if(j==0 || i==j) {
					memo[i][j]=new BigInteger("1");
				}
				else {
					memo[i][j]=memo[i-1][j].add(memo[i-1][j-1]);
				}
			}
		}
		System.out.println(memo[n][m]);

	}
}
