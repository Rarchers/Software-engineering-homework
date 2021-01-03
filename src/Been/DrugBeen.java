package Been;

public class DrugBeen {
    public String DrugName;
    public String InTime;
    public String OutTime;
    public int Num;

    public DrugBeen() {
    }

    public DrugBeen(String drugName, String inTime, String outTime, int num) {
        DrugName = drugName;
        InTime = inTime;
        OutTime = outTime;
        Num = num;
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setDrugName(String drugName) {
        DrugName = drugName;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }


}
