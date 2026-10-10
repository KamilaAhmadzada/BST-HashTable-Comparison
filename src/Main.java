
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    private static final int[] SIZES = {100, 1000, 10000};
    private static final int REPEATS = 5;

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("benchmark-results.csv")) {
            writer.write("Elements,Structure,Insertion_ms,Search_ms,Deletion_ms\n");

            for (int n : SIZES) {
                int[] values = generateValues(n);

                benchmarkBST(n, values, writer);
                benchmarkHashTable(n, values, writer);
            }

            System.out.println("Benchmark completed.");
            System.out.println("Results saved to benchmark-results.csv");

        } catch (IOException e) {
            System.out.println("Error saving results: " + e.getMessage());
        }
    }

    private static int[] generateValues(int n) {
        int[] values = new int[n];
        Random random = new Random(42);

        for (int i = 0; i < n; i++) {
            values[i] = i * 2 + 1;
        }

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }

        return values;
    }

    private static void benchmarkBST(
            int n, int[] values, FileWriter writer) throws IOException {

        double insertTotal = 0;
        double searchTotal = 0;
        double deleteTotal = 0;

        for (int r = 0; r < REPEATS; r++) {
            BST tree = new BST();

            long start = System.nanoTime();
            for (int value : values) {
                tree.insert(value);
            }
            long end = System.nanoTime();
            insertTotal += end - start;

            start = System.nanoTime();
            for (int value : values) {
                if (!tree.search(value)) {
                    throw new IllegalStateException("BST search failed");
                }
            }
            end = System.nanoTime();
            searchTotal += end - start;

            start = System.nanoTime();
            for (int value : values) {
                tree.delete(value);
            }
            end = System.nanoTime();
            deleteTotal += end - start;
        }

        writeResult(writer, n, "BST",
                insertTotal, searchTotal, deleteTotal);
    }

    private static void benchmarkHashTable(
            int n, int[] values, FileWriter writer) throws IOException {

        double insertTotal = 0;
        double searchTotal = 0;
        double deleteTotal = 0;

        for (int r = 0; r < REPEATS; r++) {
            HashTable table = new HashTable();

            long start = System.nanoTime();
            for (int value : values) {
                table.insert(value);
            }
            long end = System.nanoTime();
            insertTotal += end - start;

            start = System.nanoTime();
            for (int value : values) {
                if (!table.search(value)) {
                    throw new IllegalStateException("Hash Table search failed");
                }
            }
            end = System.nanoTime();
            searchTotal += end - start;

            start = System.nanoTime();
            for (int value : values) {
                table.delete(value);
            }
            end = System.nanoTime();
            deleteTotal += end - start;
        }

        writeResult(writer, n, "Hash Table",
                insertTotal, searchTotal, deleteTotal);
    }

    private static void writeResult(
            FileWriter writer, int n, String name,
            double insertNs, double searchNs, double deleteNs)
            throws IOException {

        double divisor = REPEATS * 1_000_000.0;

        double insertMs = insertNs / divisor;
        double searchMs = searchNs / divisor;
        double deleteMs = deleteNs / divisor;

        writer.write(String.format(
                java.util.Locale.US,
                "%d,%s,%.6f,%.6f,%.6f%n",
                n, name, insertMs, searchMs, deleteMs));

        System.out.printf(
                "%d elements | %s | Insert: %.6f ms | Search: %.6f ms | Delete: %.6f ms%n",
                n, name, insertMs, searchMs, deleteMs);
    }
}
