import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb=new StringBuilder();
		while(true) {
			String str=in.readLine();
			if(str.equals("END")) {
				break;
			}
			for(int i=str.length()-1; i>=0; i--) {
				sb.append(str.charAt(i));
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
