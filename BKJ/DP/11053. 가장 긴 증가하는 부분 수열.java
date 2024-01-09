import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// N<1000 이므로, O(N^2) 성능 까지 통과 가능 -> 2중 반복문
public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}

		// DP테이블에 들어가는 값은 인덱스 값으로 끝나는 최적해이다.
		int[] memo=new int[N];
		
		// i로 끝나는 부분 수열 계산
		for(int i=0;i<N;i++) {
			memo[i]=1;
			// 0부터 i까지 체크
			for(int j=0;j<i;j++) {
				if(arr[j]<arr[i] && memo[i]<=memo[j]) {
					memo[i]=memo[j]+1;
				}
			}
		}
		
		int max=0;
		for(int i=0;i<N;i++) {
			if(max<memo[i]) {
				max=memo[i];
			}
		}
		System.out.println(max);
	}
}
