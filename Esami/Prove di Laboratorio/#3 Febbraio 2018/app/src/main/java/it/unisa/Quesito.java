package it.unisa;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Quesito implements Parcelable {

    public Quesito(int domandaCorrente, int domandeEsatte, int domandeErrate, String ultimoColore,
                   Colore[] colori) {
        this.domandaCorrente = domandaCorrente;
        this.domandeEsatte = domandeEsatte;
        this.domandeErrate = domandeErrate;
        this.ultimoColore = ultimoColore;
        this.colori = colori;
    }

    protected Quesito(Parcel in) {
        domandaCorrente = in.readInt();
        domandeEsatte = in.readInt();
        domandeErrate = in.readInt();
        ultimoColore = in.readString();
        colori = in.createTypedArray(Colore.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(domandaCorrente);
        dest.writeInt(domandeEsatte);
        dest.writeInt(domandeErrate);
        dest.writeString(ultimoColore);
        dest.writeTypedArray(colori, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quesito> CREATOR = new Creator<Quesito>() {
        @Override
        public Quesito createFromParcel(Parcel in) {
            return new Quesito(in);
        }

        @Override
        public Quesito[] newArray(int size) {
            return new Quesito[size];
        }
    };

    public int getDomandaCorrente() {
        return domandaCorrente;
    }

    public void setDomandaCorrente(int domandaCorrente) {
        this.domandaCorrente = domandaCorrente;
    }

    public int getDomandeEsatte() {
        return domandeEsatte;
    }

    public void setDomandeEsatte(int domandeEsatte) {
        this.domandeEsatte = domandeEsatte;
    }

    public int getDomandeErrate() {
        return domandeErrate;
    }

    public void setDomandeErrate(int domandeErrate) {
        this.domandeErrate = domandeErrate;
    }

    public String getUltimoColore() {
        return ultimoColore;
    }

    public void setUltimoColore(String ultimoColore) {
        this.ultimoColore = ultimoColore;
    }

    public Colore[] getColori() {
        return colori;
    }

    public void setColori(Colore[] colori) { this.colori = colori; }

    @Override
    public String toString() {
        return "Quesito{" +
                "domandaCorrente=" + domandaCorrente +
                ", domandeEsatte=" + domandeEsatte +
                ", domandeErrate=" + domandeErrate +
                ", ultimoColore=" + ultimoColore +
                ", colori=" + Arrays.toString(colori) +
                '}';
    }

    public Colore getRandomColor() {
        ArrayList <Colore> coloriNonScelti = new ArrayList <Colore> ();
        for(Colore c : colori) {
            if (c.isScelto() == false && !c.getHexCode().equals(ultimoColore))  {
                coloriNonScelti.add(c);
            }
        }

        int size = coloriNonScelti.size();
        Random r = new Random();
        int random = r.nextInt(size);
        Colore scelto = coloriNonScelti.get(random);
        scelto.setScelto(true);
        return scelto;

    }

    public void clearSelection() {
        for(Colore c : colori)
            c.setScelto(false);
    }

    private int domandaCorrente, domandeEsatte, domandeErrate;
    private String ultimoColore;
    private Colore[] colori;

}
