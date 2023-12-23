import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이중 반복문으로 풀었을 때 시간 복잡도는 O(N^2)
// 투포인터로 풀었을 때 시간 복잡도 O(N) 이다.
public class Main {
	
	public static void main(String[] args) throws IOException {
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 int N=Integer.parseInt(in.readLine());
		 
		 int start=1, end=1;
		 int sum=1, answer=0;
		 
		 while(start<=end) {
			 if(sum==N) answer++;
			 if(sum<N) {
				 end++;
				 sum+=end;
			 }
			 else if(sum>=N) {
				 sum-=start;
				 start++;
			 }
		 }
		 System.out.println(answer);
		 
	}
}