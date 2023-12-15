import java.io.*;

// AAAA와 BB 두개의 폴리오미노가 있기 때문에 홀수는 만들 수 없다.
// 4개, 2개 -> 큰 값이 작은 값의 배수이므로 그리디로 구현하면 됨.
public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	String str=in.readLine();
    	int size=str.length();
    	
    	int cnt=0;
    	StringBuilder sb=new StringBuilder();
    	
    	for(int i=0;i<size;i++) {
    		if(str.charAt(i)=='X') {
    			cnt++;
    			// 마지막 반복인 경우
    			if(i==size-1) {
        			if(cnt%2==1) {
        				System.out.println(-1);
        				return;
        			}
        			for(int j=0;j<cnt/4;j++) {
        				sb.append("AAAA");
        			}
        			if(cnt%4!=0) {
        				sb.append("BB");
        			}
    			}
    		}
    		else {
    			if(cnt==0) {
    				sb.append('.');
    				continue;
    			}
    			if(cnt%2==1) {
    				System.out.println(-1);
    				return;
    			}
    			for(int j=0;j<cnt/4;j++) {
    				sb.append("AAAA");
    			}
    			if(cnt%4!=0) {
    				sb.append("BB");
    			}
    			cnt=0;
    			sb.append('.');
    		}
    	}
    	
    	System.out.println(sb.toString());
    	
    }
}