package it.unisa;

import android.os.Parcel;
import android.os.Parcelable;

public class Colore implements Parcelable {

    public Colore(String colore, String hexCode) {
        this.colore = colore;
        this.hexCode = hexCode;
        scelto = false;
    }

    protected Colore(Parcel in) {
        colore = in.readString();
        hexCode = in.readString();
        scelto = in.readByte() != 0;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public boolean isScelto() {
        return scelto;
    }

    public void setScelto(boolean scelto) {
        this.scelto = scelto;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    @Override
    public String toString() {
        return "Colore{" +
                "colore='" + colore + '\'' +
                ", hexCode='" + hexCode + '\'' +
                ", scelto=" + scelto +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(colore);
        dest.writeString(hexCode);
        dest.writeByte((byte) (scelto ? 1 : 0));
    }

    public static final Creator<Colore> CREATOR = new Creator<Colore>() {
        @Override
        public Colore createFromParcel(Parcel in) {
            return new Colore(in);
        }

        @Override
        public Colore[] newArray(int size) {
            return new Colore[size];
        }
    };

    private String colore, hexCode;
    private boolean scelto;

}
