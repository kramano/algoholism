package max.rindon;

import java.util.Scanner;
import java.io.PrintWriter;
import org.psjava.ds.set.DisjointSet;
import org.psjava.ds.set.DisjointSetFactory;
import org.psjava.ds.set.DisjointSetForest;

public class SumOf2Nums {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        out.println(a + b);
        DisjointSet<Integer> disjointSet = new DisjointSetForest<>();
        disjointSet.makeSet(1);
        disjointSet.makeSet(2);
        disjointSet.makeSet(3);

        disjointSet.union(2, 3);
        disjointSet.find(3);
    }
}
