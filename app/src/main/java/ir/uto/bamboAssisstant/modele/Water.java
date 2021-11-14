package ir.uto.bamboAssisstant.modele;

public class Water {
    private int shallService;
    private int dayWater;
    private String date="";

    public int getShallService() {
        return shallService;
    }

    public void setShallService(int shallService) {
        this.shallService = shallService;
    }

    public int getDayWater() {
        return dayWater;
    }

    public void setDayWater(int dayWater) {
        this.dayWater = dayWater;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
