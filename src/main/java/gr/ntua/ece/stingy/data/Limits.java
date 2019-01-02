package gr.ntua.ece.stingy.data;

public class Limits {
	
    public static final int DEFAULT_START = 0;
    public static final int DEFAULT_COUNT = 20;
    public static final int TOTAL_NOT_FETCHED = -1;

    private long start;
    private long  count;
    private long total = TOTAL_NOT_FETCHED;

    public Limits() {
        this(DEFAULT_START, DEFAULT_COUNT);
    }
    
    public Limits(long start) {
        this(start, DEFAULT_COUNT);
    }

    public Limits(long start, long count) {
        this.start = start;
        this.count = count;
    }

    public long getStart() {
        return start;
    }

    public long getCount() {
        return count;
    }

    public long getTotal() {
        return total;
    }
    
    public void setStart(long start) {
        this.start = start;
    }
    
    public void setCount(long count) {
        this.count = count;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     * for testing reasons
     */
	@Override
	public String toString() {
		return "Limits [start=" + start + ", count=" + count + ", total=" + total + "]";
	}
    
    
    
}
