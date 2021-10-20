public class MazeSolverQueue extends MazeSolver {

    MyQueue<Square> queue;

    public MazeSolverQueue(Maze maze){
        super(maze);
    }

    @Override
    public void makeEmpty(){
        queue = new MyQueue<Square>();
    }

    @Override
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    @Override
    public void add(Square s){
        queue.offer(s);
    }

    @Override
    public Square next(){
        return queue.poll();
    }
}
