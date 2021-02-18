
public class Complexity {
	
	
	public static void method0 (int n) {
		//n complexity
		int counter = 0;
		
		for (int i=0; i<n; i++) {
			//System.out.println("Operation " + counter);
			counter++;
		}
		System.out.println("Method0 Loops: " + counter);
	}

	
	public static void method1 (int n) {
		//n^2 complexity
		int counter = 0;
		
		for (int i=0; i<n; i++) {
			
			for (int j=0; j<n; j++) {
				counter++;
			}
		}
		System.out.println("Method1 Loops: " + counter);
	}
	
	
	public static void method2 (int n) {
		//n^3 complexity
		int counter = 0;
		
		for (int i=0; i<n; i++) {
			
			for (int j=0; j<n; j++) {

				for (int t=0; t<n; t++) {
					counter++;
				}
			}
		}
		System.out.println("Method2 Loops: " + counter);
	}
	
	
	public static void method3 (int n) {
		//log(n) complexity
		int counter = 0;
		
		for (int i=1; i<n+1; i *= 2) {
			counter++;
		}
		System.out.println("Method3 Loops: " + counter);
	}
	
	
	public static void method4 (int n) {
		//n log(n) complexity
		int counter = 0;
		
		for (int i=1; i<n+1; i++) {
			
			for (int j=1; j<n+1; j *= 2) {
				counter++;
			}
		}
		System.out.println("Method4 Loops: " + counter);
	}
	
	
	public static void method5 (int n) {
		//log(log(n) ) complexity
		int counter = 0;
		
		for (int i=2; i<n+2; i*=i) {
				counter++;
		}
		System.out.println("Method5 Loops: " + counter);
	}
	
	
	public static void method6 (int n) {              
		//2^n complexity todo
		
		if (n>0) {
			method6(n-1);
			method6(n-1);
		}
	}
	
	//test cases
	public static void main(String args[]) {
		int y = 0;
//		method0(y); //POINTS	(0,0) (1,1) (2,2) (3,3)  (4,4)  (5,5)
//		method1(y); //POINTS	(0,0) (1,1) (2,4) (3,9)  (4,16) (5,25)
//		method2(y); //POINTS	(0,0) (1,1) (2,8) (3,27) (4,64) (5,125)
//		method3(y); //POINTS	(0,0) (1,1) (2,2) (3,2)  (4,3)  (5,3) 
//		method4(y); //POINTS	(0,0) (1,1) (2,4) (3,6)  (4,12) (5,15)
//		method5(y); //POINTS	(0,0) (1,1) (2,1) (3,2)  (4,2)  (5,2)
//		method6(y);
		
		
	}

}
