import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.SynchronousQueue;

// 소수는 1과 자기 자신으로만 나누어 지는 수이다.
// 1은 소수가 아니다.

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		StringTokenizer st=new StringTokenizer(in.readLine(), " ");
		int cnt=0;
		
		for(int i=0;i<N;i++) {
			int temp=Integer.parseInt(st.nextToken());
			if(temp==1) continue; // 1은 소수가 아니므로

			boolean bo=true;
			// 제곱근 까지만 확인해 계산하면 된다.
			for(int j=2;j<=Math.sqrt(temp);j++) {
				if(temp%j==0) { // 소수가 아닌 경우
					bo=false;
					break;
				}
			}
			if(bo) cnt++;
		}
			
		System.out.println(cnt);
	}
}