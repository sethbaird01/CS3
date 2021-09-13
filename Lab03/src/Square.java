public class Square {

    final static String[] types = {"_", "#", "S", "E"}; //empty, wall, start, end
    final static String[] states = {"o", ".", "x"}; //work list, explored, final path

    private int row;
    private int col;
    private int type;
    private int status;

    public Square(int row, int col, int type){
        this.row = row;
        this.col = col;
        this.type = type;
        this.status = -1;
    }

    public int getRow() { return this.row;}
    public int getCol() { return this.col;}
    public int getType() { return this.type;}
    public int getStatus() { return this.status;}

    public Square setStatus(int in){
        if(this.type != 0){
            System.out.println("Square not empty! Can't set status.");
        }else {
            this.status = in;
        }
        return this;
    }

    @Override
    public String toString(){
        if(this.status >= 0){//status has been set, return this instead.
            return Square.states[this.status];
        }//status has not been set, only return type
        return Square.types[this.type];
    }

    public boolean equals(Object obj){
        if(this.row == ((Square) obj).getRow() && this.col == ((Square) obj).getCol()){
            return true;
        }
        return false;
    }

}
