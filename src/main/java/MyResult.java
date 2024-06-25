final class MyResult {
    private final char endOfRoad;
    private final int time;

    public MyResult(char endOfRoad, int time) {
        this.endOfRoad = endOfRoad;
        this.time = time;
    }

    public char getEndOfRoad() {
        return endOfRoad;
    }

    public int getTime() {
        return time;
    }
}
