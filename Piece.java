
public class Piece {
	
	private String position;
	private String color;
	
	public Piece(String color) {
		this.color = color;
		
	}
	
	public boolean canMove(String newPosition){

		return true;
	}
	
	public void setPosition(String newPosition){
		this.position = newPosition;
		
		
	}
	public String getPosition() {
		
		return position;
	}
	
	public String[]  getAllMoves() {
		String [] arr1 = new String[1];
		arr1[0] = "s";
		
		return arr1;

	}
	
	public String[]  getMoves() {
		String [] arr1 = new String[1];
		arr1[0] = "s";
		
		return arr1;

	}
	 
	
	public String getColor(){
		
		return color;
	}   
		
}
