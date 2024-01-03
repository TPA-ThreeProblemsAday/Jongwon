import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(in.readLine());
        
        int[] arr=new int[N];
        StringTokenizer st=new StringTokenizer(in.readLine()," ");
        for(int i=0;i<N;i++) {
        	arr[i]=Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);

        double answer=arr[N-1];
        for(int i=N-2; i>=0; i--) {
        	answer += arr[i]/2.0;
        }
        System.out.println(answer);
    }
}