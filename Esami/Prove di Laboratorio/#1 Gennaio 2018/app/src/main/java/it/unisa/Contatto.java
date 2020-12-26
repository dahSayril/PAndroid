package it.unisa;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Contatto implements Parcelable {

    public Contatto(String nome, String telefono, Drawable immagine) {
        this.nome = (nome == null) ? "" : nome;
        this.telefono = (telefono == null) ? "" : telefono;
        this.immagine = immagine;
    }

    protected Contatto(Parcel in) {
        nome = in.readString();
        telefono = in.readString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Drawable getImmagine() {
        return immagine;
    }

    public void setImmagine(Drawable immagine) {
        this.immagine = immagine;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(telefono);
    }

    public static final Creator<Contatto> CREATOR = new Creator<Contatto>() {
        @Override
        public Contatto createFromParcel(Parcel in) {
            return new Contatto(in);
        }

        @Override
        public Contatto[] newArray(int size) {
            return new Contatto[size];
        }
    };

    private String nome, telefono;
    private Drawable immagine;

}
