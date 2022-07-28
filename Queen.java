
import java.util.Arrays;

public class Queen  extends Piece{
	public Queen(String color) {
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
	
	
	
	public boolean borderControl(int x, int y) { //Hamlenin satranc tahtasi sinirlari icinde olup olmadigini donen metot
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
		for(int i=0; i<=7; i++) {
			if(i == row)
				continue;
			else if (borderControl(i, col)) {			
				allMoves +=convert(i, col) + ",";	
			}
		}
		for(int i=0; i<=7; i++) {
			if(i == col)
				continue;
			else if (borderControl(row, i)) {			
				allMoves +=convert(row, i) + ",";
			}
		}
		for(int i=row + 1, j=col+1; i<=7 && j<=7;i++,j++) {
			if (borderControl(i, j)) {			
				allMoves +=convert(i, j) + ",";	
			}
		}
		for(int i=row-1, j=col-1 ; i>=0 && j>=0;i--,j--) {
			if (borderControl(i, j)) {			
				allMoves +=convert(i, j) + ",";	
			}
		}
		for(int i=row-1, j=col+1; i>=0 && j<=7;i--,j++) {
			if (borderControl(i, j)) {			
				allMoves +=convert(i, j) + ",";	
			}
		}
		for(int i=row+1, j=col-1; i<=7 && j>=0;i++,j--) {
			if (borderControl(i, j)) {			
				allMoves +=convert(i, j) + ",";
	
			}
		}
	
		if(allMoves.length()!=0)
			allMoves = allMoves.substring(0,allMoves.length() - 1);
		strArr =allMoves.split(",");
		Arrays.sort(strArr);
		return strArr;

	}


}
