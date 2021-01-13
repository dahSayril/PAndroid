package com.example.amendolacarmine16gennaio2018preappello;

import android.graphics.drawable.Drawable;

public class Contatto {
    private String nome,cognome, telefono;
    private Drawable profilePic;

    public Contatto(String tel, String nome, String cognome2, Drawable profile){
        telefono = tel;
        this.nome = nome;
        cognome= cognome2;
        profilePic = profile;
    }

    public String getNome(){
        return nome;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getCognome(){
        return cognome;
    }

    public Drawable getProfilePic(){
        return profilePic;
    }

}


