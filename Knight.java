
import java.util.Arrays;
public class Knight extends Piece{

	public Knight(String color) {
		super(color);
	}
	
	
	public boolean canMove(String newPos){
		String [] arr = getAllMoves();
		boolean control = false;
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(newPos))
				control = true;
		}
		return control;
	}

	
	
	
	public String convert(int x, int y) {   // kordinati satranc tahtasina gore ceviren metot
		String newStr ="a";
		int c = 96 + y + 1;
		char chr =(char)c;
		newStr = "" + chr + (8-x);
		return newStr;
	}
	
	
	
	public boolean borderControl(int x, int y) {  //Hamlenin satranc tahtasi sinirlari icinde olup olmadigini donen metot
		boolean control = false;
		if(x <= 7 && y <= 7 && x >= 0 && y >= 0 )
			control = true;
		return control;
	}
	
	
	
	public String [] getAllMoves() {

		String[] strArr;
		String allMoves = "";
		String pos = this.getPosition();
		int num1 = pos.charAt(0) - 96;
		int num2 = pos.charAt(1) - '0';
		int row = 8 - num2;
		int col = num1 - 1;
		
		if(borderControl(row - 2, col - 1)) {
			allMoves +=convert(row  - 2, col - 1) + ",";
		}
		if(borderControl(row - 1, col - 2)) {
			allMoves +=convert(row  - 1, col - 2) + ",";	
		}
		if(borderControl(row + 1, col - 2)) {
			allMoves +=convert(row  + 1, col - 2) + ",";	
		}
		if(borderControl(row + 2, col - 1)) {
			allMoves +=convert(row  + 2, col - 1) + ",";	
		}
		if(borderControl(row - 2, col + 1)) {
			allMoves +=convert(row  - 2, col + 1) + ",";	
		}
		if(borderControl(row - 1, col + 2)) {
			allMoves +=convert(row  - 1, col + 2 ) + ",";
		}
		if(borderControl(row + 1, col + 2)) {
			allMoves +=convert(row  + 1, col + 2) + ",";	
		}
		if(borderControl(row + 2, col + 1)) {
			allMoves +=convert(row  + 2, col + 1) + ",";	
		}
		if(allMoves.length()!=0)
			allMoves = allMoves.substring(0,allMoves.length() - 1);
		strArr =allMoves.split(",");
		
		Arrays.sort(strArr);
		return strArr;

	}


}
