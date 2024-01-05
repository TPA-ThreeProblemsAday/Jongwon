import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* n이 1인 경우부터 4인 경우까지 경우의 수를 그려보면 2xn타일을 만들때 2x(n-1) 타일과 2x(n-2)타일이 포함되는 것을 확인할 수 있다.(점화식)
 * 이 문제에서 값을 출력할때 10007을 나누면 오버플로우가 발생하여 틀리게 된다.
 * 모듈러 연산을 통해 저장 테이블에 10007을 나눠서 값을 저장하면 통과된다.
 * 항상 값이 클 거 같다 싶으면 최대값을 입력하여 오버플로우가 발생하는지 확인하자!
*/
public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());

		long[] memo=new long[n+1];
		memo[1]=1;
		if(n>=2) memo[2]=2;
		if(n>=3) memo[3]=3;
		
		for(int i=4;i<=n;i++) {
			memo[i]=(memo[i-1]+memo[i-2]) % 10007;
		}
		System.out.println(memo[n]);

	}
}