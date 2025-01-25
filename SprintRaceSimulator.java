public class SprintRaceSimulator {
    static class Athlete {
        private String name;
        private double pr100m;
        private double pr200m;
        private double pr400m;

        public Athlete() { // demonstrates my ability to overload
            this.name = "Rex"; // (Star Wars reference)
            this.pr100m = 5;
            this.pr200m = 10;
            this.pr400m = 20;
        }

        public Athlete(String name, double pr100m, double pr200m, double pr400m) {
            this.name = name;
            this.pr100m = pr100m;
            this.pr200m = pr200m;
            this.pr400m = pr400m;
        }

        public double getRandomizedTime(String event) { // Randomize time within +-5% of personal record
            double baseTime = switch (event) {
                case "100m" -> pr100m;
                case "200m" -> pr200m;
                case "400m" -> pr400m;
                default -> throw new IllegalArgumentException("Invalid event"); // if event input != 100/200/400
            };
            return baseTime * (0.95 + (Math.random() * 0.1)); 
        }

        public String getName() {
            return name;
        }
    }

    static class RaceResult { // makes obj race result: athlete + time 
        Athlete athlete;
        double time;

        public RaceResult(Athlete athlete, double time) {
            this.athlete = athlete;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Athlete[] athletes = {
            new Athlete(),
            new Athlete("Usain Bolt", 9.58, 19.19, 45.28),
            new Athlete("Michael Johnson", 10.20, 19.32, 43.18),
            new Athlete("Wayde van Niekerk", 9.94, 19.84, 43.03),
            new Athlete("Yohan Blake", 9.69, 19.26, 46.40)
        };

        String[] events = {"100m", "200m", "400m"};
        
        for (String event : events) {
            System.out.println("\n=== " + event + " Race ===");
            RaceResult[] results = simulateRace(athletes, event);
            insertionSort(results);
            printResults(results);
        }
    }

    private static RaceResult[] simulateRace(Athlete[] athletes, String event) { //returns array of race results
        RaceResult[] results = new RaceResult[athletes.length];
        for (int i = 0; i < athletes.length; i++) {
            double time = athletes[i].getRandomizedTime(event);
            results[i] = new RaceResult(athletes[i], time);
        }
        return results;
    }

    private static void insertionSort(RaceResult[] results) {
        for (int i = 1; i < results.length; i++) {
            RaceResult current = results[i];
            double currentTime = current.time;
            int j = i - 1;

            while (j >= 0 && results[j].time > currentTime) {
                results[j + 1] = results[j];
                j--;
            }
            results[j + 1] = current;
        }
    }

    private static void printResults(RaceResult[] results) {
        for (int i = 0; i < results.length; i++) {
            RaceResult result = results[i];
            System.out.println((i + 1) + ". " + result.athlete.getName() + ": " + result.time + " seconds");
        }
    }
}
