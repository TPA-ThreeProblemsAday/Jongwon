import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		int[] arr=new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(arr);
		
		int answer=0;
		for(int i=0;i<N;i++) {
			int temp=arr[i]*(N-i);
			if(temp>answer) {
				answer=temp;
			}
		}
		System.out.println(answer);
	}
        
}