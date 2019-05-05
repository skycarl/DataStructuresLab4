/**
 * This class stores the results from all the sorts of a particular input file.
 *
 * @author Skyler Carlson
 * @since 2019-05-04
 */

public class SortPerformance {

    private String filename;
    private long quickSort1;
    private long quickSort2;
    private long quickSort3;
    private long quickSort4;
    private long heapSort;
    private long insertionSort;

    /**
     * Constructor for the a SortPerformance object.
     * @param filename      The filename for the associated sort.
     */
    public SortPerformance(String filename) {
        this.filename = filename;
    }

    /**
     * Getter method for the filename, containing the sort information.
     * @return      The filename of the sort.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter method for the filename.
     * @param filename      The filename, containing the sort information.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter method for the 1st quicksort variant.
     * @return      The time in which the 1st quicksort variant executed.
     */
    public long getQuickSort1() {
        return quickSort1;
    }

    /**
     * Setter method for the 1st quicksort variant.
     * @param quickSort1        The time in which the 1st quicksort variant executed.
     */
    public void setQuickSort1(long quickSort1) {
        this.quickSort1 = quickSort1;
    }

    /**
     * Getter method for the 2nd quicksort variant.
     * @return      The time in which the 2nd quicksort variant executed.
     */
    public long getQuickSort2() {
        return quickSort2;
    }

    /**
     * Setter method for the 2nd quicksort variant.
     * @param quickSort2        The time in which the 2nd quicksort variant executed.
     */
    public void setQuickSort2(long quickSort2) {
        this.quickSort2 = quickSort2;
    }

    /**
     * Getter method for the 3rd quicksort variant.
     * @return      The time in which the 3rd quicksort variant executed.
     */
    public long getQuickSort3() {
        return quickSort3;
    }

    /**
     * Setter method for the 3rd quicksort variant.
     * @param quickSort3        The time in which the 3rd quicksort variant executed.
     */
    public void setQuickSort3(long quickSort3) {
        this.quickSort3 = quickSort3;
    }

    /**
     * Getter method for the 4th quicksort variant.
     * @return      The time in which the 4th quicksort variant executed.
     */
    public long getQuickSort4() {
        return quickSort4;
    }

    /**
     * Setter method for the 4th quicksort variant.
     * @param quickSort4        The time in which the 4th quicksort variant executed.
     */
    public void setQuickSort4(long quickSort4) {
        this.quickSort4 = quickSort4;
    }

    /**
     * Getter method for the heap sort.
     * @return      The time in which the heap sort executed.
     */
    public long getHeapSort() {
        return heapSort;
    }

    /**
     * Setter method for the heap sort performance.
     * @param heapSort      The time in which the heap sort executed.
     */
    public void setHeapSort(long heapSort) {
        this.heapSort = heapSort;
    }

    /**
     * Getter method for the insertion sort.
     * @return      The time in which the insertion sort executed.
     */
    public long getInsertionSort() {
        return insertionSort;
    }

    /**
     * Setter method for the insertion sort.
     * @param insertionSort     The time in which the insertion sort executed.
     */
    public void setInsertionSort(long insertionSort) {
        this.insertionSort = insertionSort;
    }

    /**
     * ToString method for a SortPerformance object.
     * @return      A string representation of the SortPerformance object.
     */
    @Override public String toString() {
        return "SortPerformance{" +
                "filename='" + filename + '\'' +
                ", quickSort1=" + quickSort1 +
                ", quickSort2=" + quickSort2 +
                ", quickSort3=" + quickSort3 +
                ", quickSort4=" + quickSort4 +
                ", heapSort=" + heapSort +
                ", insertionSort=" + insertionSort +
                '}';
    }
}
