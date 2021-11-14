package ir.uto.bamboAssisstant.modele;

public class MyRoutine {
    private int id;
    private String RoutineName;
    private int hour;
    private int min;
    private String selectedTime;
 private int choosenTime;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoutineName() {
        return RoutineName;
    }

    public void setRoutineName(String routineName) {
        RoutineName = routineName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    public int getChoosenTime() {
        return choosenTime;
    }

    public void setChoosenTime(int choosenTime) {
        this.choosenTime = choosenTime;
    }
}
