package info.androidhive.materialtabs.models;

/**
 * Created by ThinhNK on 4/8/2018.
 */

public class Container {
    private String code;
    private int idLo;
    private String kt;

    public Container(String code, int idLo, String kt) {
        this.code = code;
        this.idLo = idLo;
        this.kt = kt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdLo() {
        return idLo;
    }

    public void setIdLo(int idLo) {
        this.idLo = idLo;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }
}
