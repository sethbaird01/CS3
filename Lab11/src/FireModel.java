public class FireModel {

    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view) {
        myGrid = new FireCell[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    private void recursiveFire(int x, int y)  {// n current square (n, n), starts at (size, size)

        //comment out to remove animation
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}
        myView.updateView(myGrid);

        FireCell N_NORTH = null;
        FireCell N_SOUTH = null;
        FireCell N_EAST = null;
        FireCell N_WEST = null;

        if (y - 1 >= 0) {
            N_NORTH = myGrid[x][y - 1];
        }
        if (y + 1 < SIZE) {
            N_SOUTH = myGrid[x][y + 1];
        }
        if (x - 1 >= 0) {
            N_EAST = myGrid[x - 1][y];
        }
        if (x + 1 < SIZE) {
            N_WEST = myGrid[x + 1][y];
        }

        if (myGrid[x][y].getStatus().equals(FireCell.Status.FIRE)) {// current cell is on fire
            // settings other things on fire

            if (N_NORTH != null && N_NORTH.getStatus().equals(FireCell.Status.GREEN)) {
                N_NORTH.setStatus(FireCell.Status.FIRE);
                recursiveFire(x, y - 1);
            }

            if (N_SOUTH != null && N_SOUTH.getStatus().equals(FireCell.Status.GREEN)) {
                N_SOUTH.setStatus(FireCell.Status.FIRE);
                recursiveFire(x, y + 1);
            }

            if (N_EAST != null && N_EAST.getStatus().equals(FireCell.Status.GREEN)) {
                N_EAST.setStatus(FireCell.Status.FIRE);
                recursiveFire(x - 1, y);
            }

            if (N_WEST != null && N_WEST.getStatus().equals(FireCell.Status.GREEN)) {
                N_WEST.setStatus(FireCell.Status.FIRE);
                recursiveFire(x + 1, y);
            }

        } else if (myGrid[x][y].getStatus().equals(FireCell.Status.GREEN)) {// current cell is a tree
            // settings current cell on fire

            if (N_NORTH != null && N_NORTH.getStatus().equals(FireCell.Status.FIRE)) {
                myGrid[x][y].setStatus(FireCell.Status.FIRE);
                recursiveFire(x, y - 1);
            }

            if (N_SOUTH != null && N_SOUTH.getStatus().equals(FireCell.Status.FIRE)) {
                myGrid[x][y].setStatus(FireCell.Status.FIRE);
                recursiveFire(x, y + 1);
            }

            if (N_EAST != null && N_EAST.getStatus().equals(FireCell.Status.FIRE)) {
                myGrid[x][y].setStatus(FireCell.Status.FIRE);
                recursiveFire(x - 1, y);
            }

            if (N_WEST != null && N_WEST.getStatus().equals(FireCell.Status.FIRE)) {
                myGrid[x][y].setStatus(FireCell.Status.FIRE);
                recursiveFire(x + 1, y);
            }
        }

    }

    public void solve() {
        // sets bottom row to on fire
        for (int i = 0; i < SIZE; i++) {
            if (myGrid[SIZE - 1][i].getStatus().equals(FireCell.Status.GREEN)) {
                myGrid[SIZE - 1][i].setStatus(FireCell.Status.FIRE);
                recursiveFire(SIZE - 1, i);
            }
        }
        // check all of top row
        boolean safe = true;
        for (int i = 0; i < SIZE; i++) {
            if (myGrid[0][i].getStatus().equals(FireCell.Status.FIRE)) {
                safe = false;
            }
        }

        System.out.println(safe ? "Onett is safe" : "Onett is in danger");
        //safe case tested working
        //fire case tested working
        
        myView.updateView(myGrid);

    }

}
