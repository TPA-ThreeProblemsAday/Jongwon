import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.SynchronousQueue;

// 문제 조건에서 팁과 사람의 수 N은 100,000보다 작거나 같은 자연수이므로 answer을 int형으로 선언하면 21억을 넘어갈 수 있다.
// answer을 long으로 선언해야 통과할 수 있다.
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		
		int[] arr=new int[n+1];
		for(int i=1;i<=n;i++) {
			arr[i]=Integer.parseInt(in.readLine());
		}
		Arrays.sort(arr);
		
		int cnt=1;
		long answer=0;
		for(int i=n;i>0;i--) {
			int temp=arr[i]-(cnt-1);
			if(temp<=0) {
				break;
			}
			answer+=temp;
			cnt++;
		}
		System.out.println(answer);
	}
}