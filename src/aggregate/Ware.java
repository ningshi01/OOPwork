package aggregate;

public class Ware {
    private  String ware;

    public String getWare() {
        return ware;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    private  String wareId;

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public Ware(){}

    public Ware(String wareId, String ware) {
        this.ware = ware;
        this.wareId = wareId;
    }

    public static Ware ware0=new Ware();
}
