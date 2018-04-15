package info.androidhive.materialtabs.models;

/**
 * Created by ThinhNK on 4/8/2018.
 */

public class StorageInfo {
    private int location;
    private int numEmpty;

    public StorageInfo(int location, int numEmpty) {
        this.location = location;
        this.numEmpty = numEmpty;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getNumEmpty() {
        return numEmpty;
    }

    public void setNumEmpty(int numEmpty) {
        this.numEmpty = numEmpty;
    }
}
