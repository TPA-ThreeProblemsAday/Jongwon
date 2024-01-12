import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 시간 계산
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] time1=in.readLine().split(":");
		String[] time2=in.readLine().split(":");
		
		int hour=Integer.parseInt(time2[0])-Integer.parseInt(time1[0]);
		int min=Integer.parseInt(time2[1])-Integer.parseInt(time1[1]);
		int sec=Integer.parseInt(time2[2])-Integer.parseInt(time1[2]);
	
		if(sec<0) {
			min-=1;
			sec+=60;
		}
		if(min<0) {
			hour-=1;
			min+=60;
		}
		if(hour<0) {
			hour+=24;
		}

		if(hour==0 && min ==0 && sec==0) {
			System.out.println("24:00:00");
			return;
		}

		System.out.printf("%02d:%02d:%02d", hour, min, sec);
	}

}
