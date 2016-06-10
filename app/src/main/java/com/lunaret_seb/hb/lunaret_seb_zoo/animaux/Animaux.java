package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by hb on 07/06/2016.
 */
public class Animaux implements Parcelable{

    private String name;
    private String specie;
    private Date dateOfBorn;

    public Animaux(String name) {
        this.name = name;
    }

    public Animaux(String name, String specie, Date dateOfBorn) {
        this.name = name;
        this.specie = specie;
        this.dateOfBorn = dateOfBorn;
    }

    protected Animaux(Parcel in) {
        name = in.readString();
        specie = in.readString();
    }

    public static final Creator<Animaux> CREATOR = new Creator<Animaux>() {
        @Override
        public Animaux createFromParcel(Parcel in) {
            return new Animaux(in);
        }

        @Override
        public Animaux[] newArray(int size) {
            return new Animaux[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(Date dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return   name+ " ("+specie+")";
    }

    public int getAge(){
        int age = (2016 - (dateOfBorn.getYear()));
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.describeContents());
        dest.writeString(name);
        dest.writeString(specie);
        dest.writeInt(getAge());
    }
    /*     @Override
    public String toString() {
        return "Animaux{" +
                "name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", dateOfBorn=" + dateOfBorn.toString() +
                '}';
    } */
}
