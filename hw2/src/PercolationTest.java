public class PercolationTest {
    public static void main(String[] args) {
        // Test Case 1: Simple 1x1 grid
        Percolation perc1 = new Percolation(1);
        assert !perc1.percolates() : "Test Case 1 Failed: Grid should not percolate initially";
        perc1.open(0, 0);
        assert perc1.percolates() : "Test Case 1 Failed: Grid should percolate after opening the only site";

        // Test Case 2: 2x2 grid
        Percolation perc2 = new Percolation(2);
        assert !perc2.percolates() : "Test Case 2 Failed: Grid should not percolate initially";
        perc2.open(0, 0);
        assert !perc2.percolates() : "Test Case 2 Failed: Grid should not percolate with only one site open";
        perc2.open(1, 1);
        assert !perc2.percolates() : "Test Case 2 Failed: Grid should not percolate with two diagonal sites open";
        perc2.open(1, 0);
        assert perc2.percolates() : "Test Case 2 Failed: Grid should percolate after opening (1, 0)";

        // Test Case 3: 3x3 grid
        Percolation perc3 = new Percolation(3);
        assert !perc3.percolates() : "Test Case 3 Failed: Grid should not percolate initially";
        perc3.open(0, 0);
        perc3.open(1, 0);
        perc3.open(2, 0);
        assert perc3.percolates() : "Test Case 3 Failed: Grid should percolate after opening a vertical path";

        // Test Case 4: 3x3 grid with no percolation
        Percolation perc4 = new Percolation(3);
        perc4.open(0, 0);
        perc4.open(0, 1);
        perc4.open(1, 1);
        perc4.open(1, 2);
        perc4.open(2, 2);
        assert !perc4.percolates() : "Test Case 4 Failed: Grid should not percolate with disconnected path";

        // Test Case 4: 4x4 grid with no percolation
        Percolation perc5 = new Percolation(4);
        perc5.open(0, 0);
        perc5.open(1, 0);
        perc5.open(2, 0);
        perc5.open(2, 1);
        perc5.open(3, 0);
        assert perc5.percolates() : "Test Case 5 Failed: Grid should percolate after opening a vertical path";

        // Test Case 5: Check number of open sites
        Percolation perc6 = new Percolation(3);
        perc6.open(0, 0);
        perc6.open(1, 1);
        perc6.open(2, 2);
        assert perc5.numberOfOpenSites() == 3 : "Test Case 6 Failed: Number of open sites should be 3";

        System.out.println("All test cases passed!");
    }
}

