import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 유클리드 호제법
public class Main {
	
	public static void main(String[] args) throws IOException {
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st=new StringTokenizer(in.readLine()," ");
		 int a=Integer.parseInt(st.nextToken());
		 int b=Integer.parseInt(st.nextToken());
		 
		 int gcd = getGcd(a,b); // 최대 공약수
		 int lcm = a*b/gcd;    // 최소 공배수
		 
		 System.out.println(gcd);
		 System.out.println(lcm);
	}
	
	private static int getGcd(int a, int b) {
		while(b>0) {
			int temp=a%b;
			a=b;
			b=temp;
			
		}
		return a;
	}
}