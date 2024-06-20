import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.ArrayList;

public class Percolation {
    private ArrayList<Integer>[][] grid;
    private int N;
    private ArrayList<ArrayList<Integer>> virtual_top;
    private ArrayList<ArrayList<Integer>> virtual_bottom;

    public Percolation(int N) {
        this.N = N;
        this.grid = new ArrayList[N][N];
        this.virtual_top = new ArrayList<>();
        this.virtual_bottom = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    public ArrayList<Integer> hasOpenNeighbor(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir: directions) {
            int n_row = row + dir[0];
            int n_col = col + dir[1];
            if (n_row >= 0 && n_row < N && n_col >= 0 && n_col < N){
                if (!grid[n_row][n_col].isEmpty()) {
                    ArrayList<Integer> neighbor = new ArrayList<>();
                    neighbor.add(n_row);
                    neighbor.add(n_col);
                    return neighbor;
                }
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<Integer> find(int row, int col) {
        int p_row = row;
        int p_col = col;
        while (!grid[p_row][p_col].isEmpty()) {
            p_row = grid[p_row][p_col].get(0);
            p_col = grid[p_row][p_col].get(1);
        }
        ArrayList<Integer> root = new ArrayList<>();
        root.add(p_row);
        root.add(p_col);
        return root;
    }

    public void open(int row, int col) {
        if (!hasOpenNeighbor(row, col).isEmpty()){
            grid[row][col] = hasOpenNeighbor(row, col);
        }
        ArrayList<Integer> new_root = new ArrayList<>();
        new_root.add(-1);
        new_root.add(-1);
        grid[row][col] = new_root;
    }

    public boolean isOpen(int row, int col) {
        if (grid[row][col].isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isFull(int row, int col) {
        for (ArrayList<Integer> top: virtual_top) {
            if (find(row, col) == find(top.get(0), top.get(1))) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!grid[i][j].isEmpty()) {
                    num += 1;
                }
            }
        }
        return num;
    }

    public boolean percolates() {
        for (ArrayList<Integer> top: virtual_top) {
            for (ArrayList<Integer> bottom: virtual_bottom) {
                if (find(top.get(0), top.get(1)) == find(bottom.get(0), bottom.get(1))) {
                    return true;
                }
            }
        }
        return false;
    }
}
