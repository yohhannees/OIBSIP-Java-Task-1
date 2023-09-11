public class Reservation {
    private String pnr;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String date;
    private String from;
    private String to;

    public Reservation(String trainNumber, String trainName, String classType, String date, String from, String to, String s) {
        this.pnr = "PNR12345"; // Generated PNR number
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    // Getters for reservation details
    public String getPnr() {
        return pnr;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClassType() {
        return classType;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
