public class Athlete {
    private String name;
    private double[][] events = new double[3][2];

    public Athlete() {
        
    }

    public Athlete(String n, double[][] e) {
        name = n;
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e[i].length; j++) {
                events[i][j] = e[i][j];
            }
        }
    }

    public double getEventTime(double e) {
        for (double[] event : events) {
            if (e == event[0]) { 
                return event[1];
            }
        }
        return -1; // if no value
    }
    public static void main(String[] args) {
        double[][] events = {{200.0, 19.19}, {100.0, 9.58}, {400.0, 43.35}};
        Athlete usainBolt = new Athlete("Usain Bolt", events);
    }
}