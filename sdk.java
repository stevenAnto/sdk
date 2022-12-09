import sudoku.Grid;

public class DriveSudoku2{
  final static int N = 9;
  final static int UNASSIGNED=0;
  public static void main(String []a){
    int sudokuTest0[][] = { 
      { 0, 0, 0, 0, 0, 0, 0, 5, 7 },
      { 0, 0, 0, 0, 0, 6, 0, 0, 0 },
      { 1, 0, 7, 0, 0, 0, 0, 9, 6 },
      { 6, 0, 0, 0, 0, 0, 4, 0, 0 },
      { 0, 0, 0, 0, 2, 0, 0, 0, 0 },
      { 0, 4, 3, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 8, 0, 1, 0, 0, 0, 9 },
      { 0, 9, 0, 2, 0, 7, 8, 0, 0 },
      { 0, 0, 5, 0, 8, 4, 0, 7, 0 }
    };

    int sudokuTest[][] = { 
      { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
      { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
      { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
      { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
      { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
      { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
      { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
    };

    Grid grid1 = new Grid(sudokuTest0);
    System.out.println("Propuesto");
    grid1.imprimir();
    solveSudoku(grid1,sudokuTest.length);
    grid1.imprimir();


  }
  public static boolean solveSudoku(Grid grid, int n ){
    System.out.println("Entro solveSudoku\n");
    int rowCol[] = new int[2];

    //Caso Base
    if(!FindUnassignedLocn(grid,rowCol,n)) return true;
    System.out.println("valor de row y col"+rowCol[0]+","+rowCol[1]);
    int row =rowCol[0];
    int col=rowCol[1];
    //Caso Recursivo
    for(int num=1;num<=N;num++){
      System.out.println("entro for");
      if(NoConflicts(grid,row,col,num)){
	System.out.println("entro for.1");
	grid.set(row,col,num);
	if(solveSudoku(grid,n))return true;
	else{
	  System.out.println("entro else");
	  grid.set(row,col,UNASSIGNED);
	}
      }
    }
    return false;
  }


  public static boolean FindUnassignedLocn(Grid grid,int [] rowCol,int n){
    for(int i=0;i<n;i++){
      for(int j=0;j<n;j++){
	if(grid.getI(i,j)==0){
	  System.out.println("entro FinUnass");
	  rowCol[0]=i;
	  rowCol[1]=j;
	  System.out.println("row"+rowCol[0]+"col"+rowCol[1]);
	  return true;
	}
      }
    }
    return false;
  }


  public static boolean NoConflicts(Grid g, int row, int col, int num){
    System.out.println("entro Noconflict con num"+num);
    //Verificamos filas
    for (int i=0;i <N;i++){
      if(g.getI(row,i)==num){
	System.out.println("Noconlfic1");
	return false;
      }
    }
    //Verificamos columnas
    for (int i=0;i <N;i++){
      if(g.getI(i,col)==num){
	System.out.println("Noconlfic2");
	return false;
      }
    }
    //Verificamos el bloque al que pertenece
    int startRow = row-row%3;
    int startCol = col-col%3;
    for(int i=0;i<3;i++)
      for(int j=0;j<3;j++){
	if(g.getI(i+startRow,j+startCol)==num){
	  System.out.println("Nonclic3");
	  return false;
	}
      }
    return true;
  }
}
