
public class Board {
	
	private Piece[][] board = new Piece[8][8];
	private Piece[][] copiedBoard = new Piece[8][8];
	public Board(){
		
	}


	public void backupBoard(){   //kopyalanmis tahta
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				copiedBoard[i][j] = board[i][j];
			}
		}
	}
	
	public void restoreBoard(){  // tahtayi eski haline getir
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				board[i][j] = copiedBoard[i][j];
			}
		}
	}
	
	
	public boolean putPiece(Piece p, String pos){
		p.setPosition(pos);
		boolean control = false;
		int num1 = pos.charAt(0) - 96;
		int num2 = pos.charAt(1) - '0';
		
			board[8 - num2][num1-1] = p;
		
		
		return control;
	}
	

	public boolean isEmpty(String pos) {
		boolean control = false;
		int num1 = pos.charAt(0) - 96;
		int num2 = pos.charAt(1) - '0';
		if(board[8 - num2][num1-1] == null) {
			control = true;	
		}
		
		return control;
	}
	
	
	
	public Piece getPiece(String pos) {
		int num1 = pos.charAt(0) - 96;
		int num2 = pos.charAt(1) - '0';
		return board[8 - num2][num1-1];
	}
	
	public boolean check(String color){    // SAH 
	
		boolean controlBishop=false;
		boolean controlRook=false;
		boolean controlQueen=false;
		boolean controlKnight=false;
		boolean controlKing=false;
		boolean controlPawn=false;
		
		String kingPlace ="";
		for(char abs=97; abs<=104 ; abs++) {    //Sahin yerini bulmaya calisiyoruz
			for(int i=1;i<=8;i++) {
				String str1 ="" + abs + i;
				if(getPiece(str1) instanceof King && getPiece(str1).getColor() == color) {
					kingPlace = getPiece(str1).getPosition();   //Sah burada
					
				}
			}
				
		}
		
		for(char abs=97; abs<=104 ; abs++) { //1.       //Sah tehlikede mi
			for(int i=1;i<=8;i++) { //2.
				
				boolean control1 = false;
				String str2 ="" + abs + i;
				String [] arr;
				if(getPiece(str2) != null) {
					arr = getPiece(str2).getAllMoves();
					for(int j=0; j<arr.length;j++) {
						if(arr[j].equals(kingPlace)) {
							control1 = true;
						}
					}

					//King ile Bishop arasinda tas var mi
					if(control1 && getPiece(str2) != null && getPiece(str2) instanceof Bishop && getPiece(str2).getColor() != color) {  //Bishop 
						controlBishop = true;
						String bishopPos =  getPiece(str2).getPosition();
						char x1 = bishopPos.charAt(0) ;
						char y1 = bishopPos.charAt(1);
						char x2 = kingPlace.charAt(0);
						char y2 = kingPlace.charAt(1);
						String hareket="";

						if((x2>x1) && (y2>y1)) {
							--x2;
							--y2;
							for(char aps=x2,ord=y2; aps>x1  ; aps--,ord--) {
								hareket = "" + (char)aps + (char)ord ;
								if(getPiece(hareket) != null) {
									controlBishop=false;
									break;
								}
									
							}
						}
						
						if((x2>x1) && (y2<y1)) {
							--x2;
							++y2;
							for(char aps=x2,ord=y2; aps>x1  ; aps--,ord++) {
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlBishop=false;
									break;
								}
								
							}
						}
						
						if((x2<x1) && (y2<y1)) {
							++x2;
							++y2;
							for(char aps=x2,ord=y2; aps<x1  ; aps++,ord++) {
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlBishop=false;
									break;
								}
							}
						}
						
						if((x2<x1) && (y2>y1)) {
							++x2;
							--y2;
							for(char aps=x2,ord=y2; aps<x1  ; aps++,ord--) {								
								hareket = "" + (char)aps + (char)ord ;
								if(getPiece(hareket) != null) {
									controlBishop=false;
									break;
								}
							}
						}
							
					}//bishop end
					//King ile Rook arasinda tas var mi
					if(control1 && getPiece(str2) != null && getPiece(str2) instanceof Rook && getPiece(str2).getColor() != color) { //rook
						controlRook = true;
						String rookPos =  getPiece(str2).getPosition();
						char x1 = rookPos.charAt(0) ;
						char y1 = rookPos.charAt(1);
						char x2 = kingPlace.charAt(0);
						char y2 = kingPlace.charAt(1);
						String hareket="";
						
						if((x1==x2) && (y2>y1)) {
							if(y2-y1==1)
								return true;
							--y2;
							for(char aps=x2,ord=y2; ord>y1  ; ord--) {
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null && !(getPiece(hareket) instanceof King)) {
									controlRook=false;
									break;
								}
								
							}
							
						}
						if((x1==x2) && (y1>y2)) {
							if(y1-y2==1)
								return true;
							++y2;
							for(char aps=x2,ord=y2; ord<y1  ; ord++) {
								if(y1-y2==1)
									return true;
								
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null && !(getPiece(hareket) instanceof King)) {
									controlRook=false;
									break;
								}
								
							}
							
						}
						
						if((x2>x1) && (y1==y2)) {
							if(x2-x1==1) {
								return true;
								
							}
							++x1;
							for(char aps=x1,ord=y2; aps<x2  ; aps++) {
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null ) {
									controlRook=false;
									break;
								}
								
								
							}
							
						}
						
						if((x2<x1) && (y1==y2)) {
							if(x1-x2==1)
								return true;
							++x2;
							for(char aps=x2,ord=y2; aps<x1  ; aps++) {							
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null && !(getPiece(hareket) instanceof King)) {
									controlRook=false;
									break;
								}
								
							}
							
						}
						
					}//rook end
					//King ile Queen arasinda tas var mi
					if(control1 && getPiece(str2) != null && getPiece(str2) instanceof Queen && getPiece(str2).getColor() != color) { //Quenn
						controlQueen = true;
						String queenPos =  getPiece(str2).getPosition();
						char x1 = queenPos.charAt(0) ;
						char y1 = queenPos.charAt(1);
						char x2 = kingPlace.charAt(0);
						char y2 = kingPlace.charAt(1);
						String hareket="";
						if((x1==x2) && (y2>y1)) {
							--y2;
							for(char aps=x2,ord=y2; ord>y1  ; ord--) {								
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
								
							}
							
						}
						
						
						if((x1==x2) && (y1>y2)) {
							++y2;
							for(char aps=x2,ord=y2; ord<y1  ; ord++) {	
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
								
							}
							
						}
						
						if((x2>x1) && (y1==y2)) {
							--x2;
							for(char aps=x2,ord=y2; aps>x1  ; aps--) {
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
								
							}
							
						}
						
						if((x2<x1) && (y1==y2)) {
							++x2;
							for(char aps=x2,ord=y2; aps<x1  ; aps++) {
								hareket = "" + (char)aps + (char)ord ;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
								
							}
							
						}
						
						if((x2>x1) && (y2>y1)) {
							--x2;
							--y2;
							for(char aps=x2,ord=y2; aps>x1  ; aps--,ord--) {
								hareket = "" + (char)aps + (char)ord ;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
								
								
								
							}
						}
						
						if((x2>x1) && (y2<y1)) {
							--x2;
							++y2;
							for(char aps=x2,ord=y2; aps>x1  ; aps--,ord++) {
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
								
							}
						}
						
						if((x2<x1) && (y2<y1)) {
							++x2;
							++y2;
							for(char aps=x2,ord=y2; aps<x1  ; aps++,ord++) {
								hareket = "" + (char)aps + (char)ord ;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
							}
						}
						
						if((x2<x1) && (y2>y1)) {
							++x2;
							--y2;
							for(char aps=x2,ord=y2; aps<x1  ; aps++,ord--) {								
								hareket = "" + (char)aps + (char)ord;
								if(getPiece(hareket) != null) {
									controlQueen=false;
									break;
								}
							}
						}
							
					}//Queen end
					//King ile Knight arasinda tas olmasi sorun olusturmuyor
					if(control1 && getPiece(str2) != null && getPiece(str2) instanceof Knight && getPiece(str2).getColor() != color) { //Knight
						controlKnight=true;
						break;
						
					}//Knight end
					
					if(control1 && getPiece(str2) != null && getPiece(str2) instanceof King && getPiece(str2).getColor() != color) { //King
						controlKing=true;
						break;
					}//King end
					
					if(control1 && getPiece(str2) != null && getPiece(str2) instanceof Pawn && getPiece(str2).getColor() != color) { //Pawn
						
						
						String pawnPos =  getPiece(str2).getPosition();
						char x1 = pawnPos.charAt(0) ;
						char y1 = pawnPos.charAt(1);
						char x2 = kingPlace.charAt(0);
						char y2 = kingPlace.charAt(1);
						if(getPiece(pawnPos).getColor()=="white")
							
							if((x1 - 1== x2) && (y1 + 1==y2)) {
								controlPawn=true;
								break;
							}
							if((x1 + 1== x2) && (y1 + 1==y2)) {
								System.out.println();
								controlPawn=true;
								break;
							}
						if(getPiece(pawnPos).getColor()=="black")
							if((x1 - 1== x2) && (y1 - 1==y2)) {
								controlPawn=true;
								break;
							}
							if((x1 + 1== x2) && (y1 - 1==y2)) {
								controlPawn=true;
								break;
							}
						
							
					}//Pawn end
					
					
					
				} 
			}	
		}
		return controlBishop || controlRook || controlQueen || controlKnight || controlKing || controlPawn;
	}
		
	
	
	public boolean checkMate(String color){
		backupBoard();
		restoreBoard();	
		if(check(color) == false) {return false; }
			for(char abs=97; abs<=104;abs++) {
				for(int i=1; i<=8; i++) {
					String nowPos=""+abs+i;
					String[]arr;
					if(getPiece(nowPos)!=null && getPiece(nowPos).getColor()==color) {
						arr = getPiece(nowPos).getAllMoves();
						for(int j=0;j<arr.length;j++) {  // Tum hareketleri teker teker uygulayip check yapiyoruz
							if(getPiece(arr[j]) != null && getPiece(arr[j]).getColor()==color) //hareket edilen yerde ayni renkte tas varsa atliyoruz
								continue;
							if(getPiece(arr[j]) == null && nowPos.charAt(0) != arr[j].charAt(0) && getPiece(nowPos) instanceof Pawn) //Piyonun hareket edecegi yerde tas yoksa atla
								continue;
							if(getPiece(arr[j]) != null && nowPos.charAt(1) == arr[j].charAt(1) && getPiece(nowPos) instanceof Pawn) //Piyon hareket ettigi yerdeki tasi alamazsa atla
								continue;
							
							int num1 = arr[j].charAt(0) - 96;
							int num2 = arr[j].charAt(1) - '0';
							int num3 = nowPos.charAt(0) - 96;
							int num4 = nowPos.charAt(1) - '0';
							
							
						
							putPiece(board[8 - num4][num3-1],arr[j]);
							board[8 - num4][num3-1] = null;
							
							if(check(color)) {  //  tehdit devam ediyor mu ?
								putPiece(board[8 - num2][num1-1],nowPos);
								board[8 - num2][num1-1] = null;
								restoreBoard();		
							}
							else {   // tehdit devam etmiyorsa false donuyoruz
								restoreBoard();
								return false;
							}
	
						}
					}
				}
			} 
		
		return true;
	}
	
}
