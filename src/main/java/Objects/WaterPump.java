package Objects;

public class WaterPump {
    private boolean status;
    private String name;
    private GardenSection gardenSection;


    public WaterPump(String name) {
        this.name = name;
    }

    public void setSection(GardenSection gardenSection) {
        this.gardenSection = gardenSection;
    }

    public void changeStatus() {
        if(status) {
            status = false;
        }
        else{
            status = true;
        }
    }

    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }



}
