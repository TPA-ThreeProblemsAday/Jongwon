import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		String str=in.readLine();
		int answer=0;
		for(int i=0;i<N;i++) {
			answer+=str.charAt(i)-'0';
		}
		System.out.println(answer);
	}
}
