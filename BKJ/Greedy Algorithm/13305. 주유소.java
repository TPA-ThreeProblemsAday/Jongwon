import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 	이 문제에서 도로의 길이와 주유소의 리터당 가격의 범위는 1에서 10억이지만 long으로 배열을 선언해야 한다.
 	이유는 두 값을 곱할때의 값이 int형 범위를 벗어나 오버플로우가 발생할 수 있기 때문이다.
 	answer += (long)temp_price * (long)dists[i]; 
 	위의 예시처럼 해도 답은 맞지만, 시간이 364ms -> 428ms이 되므로 long형 배열로 선언하는 것이 효율적이다.
*/
public class Main {
	
	public static void main(String[] args) throws IOException {
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 int N=Integer.parseInt(in.readLine()); // 도시의 개수
		 long[] dists=new long[N-1]; // 왼쪽부터 각 도로의 길이
		 long[] prices=new long[N];  // 왼쪽부터 각 주유소의 리터당 가격
		 
		 StringTokenizer st=new StringTokenizer(in.readLine()," ");
		 for(int i=0;i<N-1;i++) {
			 dists[i]=Integer.parseInt(st.nextToken());
		 }
		 
		 st=new StringTokenizer(in.readLine()," ");
		 for(int i=0;i<N;i++) {
			 prices[i]=Integer.parseInt(st.nextToken());
		 }
		 
		 long answer=0;
		 long temp_price=prices[0];
		 
		 for(int i=0;i<N-1;i++) {
			 // 현재 주유소 가격이 이전에 지나온 주유소들 가격보다 쌀 경우 -> temp_price 갱신
			 if(prices[i] < temp_price) {
				 temp_price=prices[i];
			 }
			 answer += temp_price * dists[i]; // dists, prices가 정수형 배열일 경우 앞에 (long)을 붙여서 형변환을 해줘야 오버플로우가 발생하지 않는다.
		 }
		 System.out.println(answer);
		 
	}
}