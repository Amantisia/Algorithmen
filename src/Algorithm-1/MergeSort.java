/**
 * MergeSort ist ein Sortieralgorithmus, der nach dem Prinzip "teile und herrsche" arbeitet.
 * Der MergeSort zerlegt den Ursprungsarray in zwei Arrays.
 * Er ruft dann für die kleineren Listen sich selber auf und fügt die sortierten, kleineren Arrays, wieder zusammen.
 * merge() fügt zwei Arrays wieder zusammen.
 * merge(l, m, r) nimmt an, dass die Arrays arr(l..m) und arr(m+1..r) sortiert sind und fügt diese wieder zusammen.
 * l = Links r = Rechts m = Mitte
 *
 * @author Can Heilgermann - 852313
 * @author Fabian Wendland - 813158
 */

public class MergeSort {

    private static int[] intArr = null;
    private static String[] names = new String[]{"Rand10_1", "Rand10_2", "Rand20_1", "Rand20_2", "Rand50_1", "Rand50_2", "Rand100_1", "Rand100_2"
            , "Rand200_1", "Rand200_2", "Rand500_1", "Rand500_2", "Rand1000_1", "Rand1000_2", "Rand10000_1", "Rand10000_2"
            , "Rand100000_1", "Rand100000_2"};

    /**
     * Treibermethode, die zuerst die Inhalte von DAT Dateien in deinen int Array konvertiert.
     * Danach wird ein Objekt des MergeSorts erstellt. Die Startzeit wird gespeichert, der Sortieralgorithmus ausgeführt und anschließend wird
     * die Endzeit gespeichert. Die Zeit wird ausgegeben. Wenn der Array korrekt sortiert wurde, werden die sortierten Daten anschließend
     * in deiner Textdatei gespeichert.
     *
     * @param args -
     */
    public static void main(String[] args) {
        for (String i : names) {
            intArr = FileIntArray.FileToIntArray(i);
            MergeSort ms = new MergeSort();

            long startTime = System.currentTimeMillis();
            long start = System.nanoTime();

            ms.sort(0, intArr.length - 1);

            long stopTime = System.currentTimeMillis();
            long end = System.nanoTime();

            long microseconds = (end - start) / 1000;
            long elapsedTime = stopTime - startTime;

            String Time = "MergeSort for " + intArr.length + " integer :  " + elapsedTime + " milliseconds " + microseconds + " microseconds";
            System.out.println(Time);
            System.out.println("Sorted:" + check());

            if (check()) {
                FileIntArray.IntArrayToFile("Sort" + intArr.length + "_" + i.charAt(i.length() - 1) + ".txt", Time, intArr);
            }
        }
    }


    /**
     * Die Sortierfunktion, die den Array arr[l..r] sortiert. Der linke Index l muss kleiner sein als der rechte Index r.
     * Die Mitte des Arrays wird ermittelt und in zwei kleinere Arrays geteilt.
     * Danach wird die linke Hälfte des Arrays sortiert.
     * Daraufhin wird die rechte Hälfte des Arrays sortiert
     * Abschließend werden die sortierten Arrays zusammengeführt.
     *
     * @param l - Der linke Index
     * @param r - Der rechte Index
     */
    private void sort(int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(l, m);
            sort(m + 1, r);
            merge(l, m, r);
        }
    }

    /**
     * Fügt zwei sortierte Arrays in ein Array zusammen.
     * Zuerst erstellen wir einen temporären Array temp, welcher die Länge des ursprünglichen Arrays hat.
     * Danach werden die Daten des linken Arrays in den temporären Array kopiert.
     * Daraufhin wird der rechte Array in den temporären Array kopiert.
     * Abschließend wird der temporäre Array mit dem originalen Array abgeglichen.
     *
     * @param l - Der linke Index
     * @param m - Der mittlere Index
     * @param r - Der rechte Index
     */
    private void merge(int l, int m, int r) {
        int[] arr = new int[intArr.length];
        int i, j;
        for (i = l; i <= m; i++) {
            arr[i] = intArr[i];
        }
        for (j = m + 1; j <= r; j++) {
            arr[r + m + 1 - j] = intArr[j];
        }
        i = l;
        j = r;
        for (int k = l; k <= r; k++) {
            if (arr[i] <= arr[j]) {
                intArr[k] = arr[i];
                i++;
            } else {
                intArr[k] = arr[j];
                j--;
            }
        }
    }

    /**
     * Automatisierte Überprüfung, ob die Zahlen korrekt sortiert sind.
     *
     * @return - boolean true/false
     */
    private static boolean check() {
        for (int i = 0; i < intArr.length - 2; i++) {
            for (int j = i + 1; j < intArr.length - 1; j++) {
                if (intArr[i] > intArr[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}