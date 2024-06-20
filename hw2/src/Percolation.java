import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF parent;
    private int[] openness;
    private int N;


    public Percolation(int N) {
        this.parent = new WeightedQuickUnionUF(N*N+2);
        this.N = N;
        this.openness = new int[N*N];
    }

    public int[] hasOpenNeighbor(int row, int col) {
        int index = row*N + col + 1;
        int top = index - N;
        int bottom = index + N;
        int left = index - 1;
        int right = index + 1;
        int[] neighbor = new int[4];
        if (top > 0 && openness[top-1] == 1) {
            neighbor[0] = top;
        }
        if (bottom <= N*N && openness[bottom-1] == 1) {
            neighbor[1] = bottom;
        }
        if (left % N != 0 && left > 0 && openness[left-1] == 1) {
            neighbor[2] = left;
        }
        if (right % N != 1 && right <= N*N && openness[right-1] == 1) {
            neighbor[3] = right;
        }
        return neighbor;
    }

    public void open(int row, int col) {
        int index =  row*N + col + 1;
        openness[index-1] = 1;
        int[] comparisonArray = {0, 0, 0, 0};
        if (!hasOpenNeighbor(row, col).equals(comparisonArray)) {
            for (int item: hasOpenNeighbor(row, col)) {
                if (item != 0) {
                    parent.union(index, item);
                }
                if (row == 0) {
                    parent.union(index, 0);
                }
                if (row == N) {
                    parent.union(index, N*N+1);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int index =  row*N + col + 1;
        if (openness[index-1] == 1) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        int index =  row*N + col + 1;
        return parent.connected(index, 0);
    }

    public int numberOfOpenSites() {
        int sum = 0;
        for (int i = 0; i < N*N; i++) {
            sum += openness[i];
        }
        return sum;
    }

    public boolean percolates() {
        return parent.connected(0, N*N+1);
    }
}
