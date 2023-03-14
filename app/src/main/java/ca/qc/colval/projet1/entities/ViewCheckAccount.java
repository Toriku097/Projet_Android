package ca.qc.colval.projet1.entities;

public class ViewCheckAccount {
    private int  checkNum;
    private int checkID;

    public ViewCheckAccount(){}
    public ViewCheckAccount(int checkNum, int checkID) {
        this.checkNum = checkNum;
        this.checkID = checkID;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }
}
