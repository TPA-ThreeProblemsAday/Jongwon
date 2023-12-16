import java.io.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	int n=Integer.parseInt(in.readLine());
    	
    	int change=1000-n;
    	int count=0;
    	
    	count+=change/500;
    	change%=500;
    	
    	count+=change/100;
    	change%=100;
    	
    	count+=change/50;
    	change%=50;
    	
    	count+=change/10;
    	change%=10;
    	
    	count+=change/5;
    	change%=5;
    	
    	count+=change/1;
    	System.out.println(count);
    	
    }
}