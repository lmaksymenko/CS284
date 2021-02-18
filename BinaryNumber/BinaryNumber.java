import java.util.Arrays;
import java.util.InputMismatchException;
import java.lang.Math;

//Leonid Maksymenko


public class BinaryNumber {
//variables
	private int data[];
	private int length;
	
//Constructors
	public BinaryNumber(int length) {
		//create binary number length length consisting of only zeros
		this.data = new int[length];
		this.length = length;
	}
	
	
	public BinaryNumber(String str)  throws InputMismatchException{
		//create a binary number given a string
		char[] chardata = str.toCharArray();
		this.length = chardata.length;
		this.data = new int[chardata.length];
				
		for(int i = 0; i <= length-1; i++) {
			data[i] = Character.getNumericValue(chardata[i]);
		}
			
		for(int i : data) {
			if(i != 1 && i != 0) {
				throw new InputMismatchException("Constructor given wrong value.");
			}
		}

	}
	
	
//getters
	public int getLength() {
		//returns length of data array
		return length;
	}
	
	
	public int getDigit(int index) {
		//returns the digit at the index
		
		if ( (0<= index) && (index <= length-1) ) {
			return data[index];
		}else {
			System.out.println("Index out of bounds.");
			return -1;
		}
	
	}
	
	
	public int[] getInnerArray() {
		//returns the whole number array
		return data;
	}
	
	
//methods
	public void bitShift(int direction, int amount) throws InputMismatchException{
		//shifts bits 'amount' spaces left(-1) or right(1)
		
		if(amount >= 0 && ((direction == 1 || direction == -1)) ) {
			int dataShift[] = new int [data.length];
			
			for(int i = 0; i <= length-1; i++) {
				
				if( (0 <= i+(amount*direction*-1)) && 
					(i+(amount*direction*-1) <= length-1) ) {
						
					dataShift[i] = data[i+(amount*direction*-1)];
				}
				
			}
			data = Arrays.copyOf(dataShift, length);
		
		}else {
			throw new InputMismatchException("bitShift input invalid");
		}
			
	}
	
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		//conducts an or check of the two binary numbers
		//shortens the numbers to their actual size in the case that they have excess zeros in front
		int[] bnum1 = BinaryNumber.shorten(bn1.getInnerArray());
		int[] bnum2 = BinaryNumber.shorten(bn2.getInnerArray());
		int[] bworResult = new int[bnum1.length];
		
		if(bnum1.length != bnum2.length) { 
			throw new InputMismatchException("Binary Numbers of Different Lengths in bwor.");
		}
		
		for(int i = 0; i <= bnum1.length-1; i++) {
			
			if((bnum1[i] == 1) || (bnum2[i] == 1)) {
				bworResult[i] = 1;
			}
			
		}
		
		return bworResult;
	}
	
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		//conducts an or check of the two binary numbers
		//shortens the numbers to their actual size in the case that they have excess zeros in front
		int[] bnum1 = BinaryNumber.shorten(bn1.getInnerArray());
		int[] bnum2 = BinaryNumber.shorten(bn2.getInnerArray());
		int[] bwandResult = new int[bnum1.length];
		
		if(bnum1.length != bnum2.length) { 
			throw new InputMismatchException("Binary Numbers of Different Lengths in bwor.");
		}
		
		for(int i = 0; i <= bnum1.length-1; i++) {
			
			if((bnum1[i] == 1) && (bnum2[i] == 1)) {
				bwandResult[i] = 1;
			}
			
		}
		
		return bwandResult;
	}
	
	
	public void add(BinaryNumber aBinaryNumber) {
		//
		data = BinaryNumber.shorten(data);
		int[] addNum = BinaryNumber.shorten(aBinaryNumber.getInnerArray());
		int[] longer = new int[0];
		int[] shorter = new int[0];
		
		if(data.length >= addNum.length) {
			longer = data;
			shorter = BinaryNumber.prepend(addNum, data.length-addNum.length);
		
		}else {
			longer = addNum;
			shorter = BinaryNumber.prepend(data, addNum.length-data.length);
		}
		
		int[] carry = new int[longer.length+1];
		
		for (int i= longer.length-1; i>=0; i--) {
			int value = longer[i] + shorter[i] + carry[i+1];
			
			if(value == 2) { 
				carry[i+1] = 0;
				carry[i] = 1;
			
			}else if(value == 3) {
				carry[i+1] = 1;
				carry[i] = 1;
			
			}else if(value == 1){
				carry[i+1] = 1;
			
			}else {
				carry[i+1] = 0;
			}
			
		}
	
		data = carry;
	
	}
	
	
//helpers
	public static int[] shorten(int[] array) {
		//shortens the given number to take off unnecessary zeros from the front
		int index = 0;
		
		for(int i = 0; i <= array.length-1; i++) {
			
			if(array[i] == 1) {
				index = i;
				break;
			}
			
		}
		
		int shortened[] = new int [array.length-index];
		
		for(int i = index; i <= array.length-1; i++) {
			shortened[i-index] = array[i];
		}
		
		//System.out.println("Shortened: " + Arrays.toString(shortened));
		return shortened;
		
	}
	
	
	public static int[] prepend(int[] array, int amount) {
		//this method adds amount zeros to the beginning of an array
		int [] prepended = new int[array.length+amount];
		
		for(int i = 0; i <= array.length-1; i++) {
			prepended[i+amount] = array[i];
		}
	
		return prepended;
	}
	
	
//To methods
	public String toString() {
		//returns the string of the array
		int[] shortened = BinaryNumber.shorten(data);
		int num = 0;
		
		for (int i = 0; i<=shortened.length-1 ; i++) {
			num += shortened[i];
			if (i<shortened.length-1) {
				num *= 10;
			}
			
			System.out.println(num);
		}
		
		
	
		return Integer.toString(num);
	}
	
	
	public int toDecimal() {
		//converts binary to decimal
		int sum = 0;
		
		for (int i = 0; i <= length-1; i++) {
			sum += data[i] * Math.pow(2, length-i-1);
		}
		
		return sum;
	}
	
//test cases
	
	public static void main(String[] args) {
//		//test vars
//		BinaryNumber num = new BinaryNumber("11111111");
//		BinaryNumber num2 = new BinaryNumber("1010101010");
//		
//		int[] arr = {0,0,1,0,1,1};
//		//get length
//		System.out.println(num.getLength());
//		
//		//get inner array
//		System.out.println(Arrays.toString((num.getInnerArray())));
//		
//		//get digit
//		System.out.println(num.getDigit(6));
//		System.out.println(num.getDigit(6));
//		
//		System.out.println("decimal: " + num.toDecimal());
//		
//		//tostring 
//		System.out.println("To String: " + num.toString());
//		
		//prepend test
		
		//System.out.println("Prepend: " + Arrays.toString(BinaryNumber.prepend(num.getInnerArray(),0)));
		//num.bitShift(-1, 7);
		
		//add test
		//num.add(num2);
//		System.out.println(num.toString());
//		BinaryNumber.shorten(arr);
//		System.out.println(num.toString());
		
//		//bwor test
//		BinaryNumber arr2 = new BinaryNumber("000000001001");
//		BinaryNumber arr3 = new BinaryNumber("0001101");
//		BinaryNumber.bwor(arr2, arr3);
//		//bwand test
//		BinaryNumber.bwand(arr2, arr3);
	}
	
}


