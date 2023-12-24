import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws IOException {
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 int n=Integer.parseInt(in.readLine());
		 StringTokenizer st=new StringTokenizer(in.readLine()," ");

		 int[] arr=new int[n];
		 int min=Integer.MAX_VALUE;
		 for(int i=0;i<n;i++) {
			 arr[i]=Integer.parseInt(st.nextToken());
			 if(arr[i]<min) min=arr[i];
		 }
		 
		 System.out.println(1);
		 for(int i=2;i<=min;i++) {
			 boolean bo=true;
			for(int j=0;j<n;j++) {
				if(arr[j] % i != 0) {
					bo=false;
					break;
				}
			}
			if(bo) System.out.println(i);
		 }
	}
}