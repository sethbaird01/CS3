public abstract class MazeSolver {
    private Maze maze;
    private int conclusion;
    public static String[] conclusions = {"Solved","Unsolvable"};

    public MazeSolver(Maze maze){
        this.maze = maze;
        this.makeEmpty();
        this.add(maze.getStart());
        this.conclusion = -1;
    }

    abstract void makeEmpty();

    abstract boolean isEmpty();

    abstract void add(Square s);

    abstract Square next();

    boolean isSolved() {
        if(conclusion > -1){
            //either solved or unsolvable
            return true;
        }
        return false;
    }

    public void step(){
        //solving "logic"
        for (Square s : maze.getNeighbors(this.next().setStatus(1))) {
        if(s.getType() == 3){//end found!
            this.conclusion = 0;
        }
        if(s.getType() == 0 && s.getStatus() != 1){//empty space
                s.setStatus(0);
                this.add(s);
            }
        }
    
        if(this.isEmpty()){
            this.conclusion = 1;
        }
    }

    String getPath(){
        if(conclusion < 0){
            return "Not yet solved";
        }
        return MazeSolver.conclusions[conclusion];
    }

}
