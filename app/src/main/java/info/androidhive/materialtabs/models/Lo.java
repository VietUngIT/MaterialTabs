package info.androidhive.materialtabs.models;

/**
 * Created by ThinhNK on 4/8/2018.
 */

public class Lo {
    private int id;
    private int blanks;
    private String kt;

    public Lo(int id, int blanks, String kt) {
        this.id = id;
        this.blanks = blanks;
        this.kt = kt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlanks() {
        return blanks;
    }

    public void setBlanks(int blanks) {
        this.blanks = blanks;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }
}
