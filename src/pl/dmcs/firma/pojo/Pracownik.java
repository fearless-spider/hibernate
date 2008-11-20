package pl.dmcs.firma.pojo;

import java.util.*;

public class Pracownik {

private int ID;
private String imie;
private String nazwisko;
private Date dataUrodzenia;
private String stanowisko;
private int projekt_id;
private int dzial_id;

Projekt projekt;
Dzial dzial;
//Set properties

public void setID(int _id) {
  ID = _id;
}

public void setImie(String _imie) {
  imie = _imie;
}

public void setNazwisko(String _nazwisko) {
  nazwisko = _nazwisko;
}

public void setDataUrodzenia(Date _dataUrodzenia) {
  dataUrodzenia = _dataUrodzenia;
}

public void setStanowisko(String _stanowisko) {
  stanowisko = _stanowisko;
}

public void setProjekt(Projekt _projekt) {
  this.projekt = _projekt;
  //projekt_id = projekt.getID();
}

public void setDzial(Dzial _dzial) {
  this.dzial = _dzial;
  //dzial_id = dzial.getID();
}

//Get properties

public int getID() {
  return ID;
}

public String getImie() {
  return imie;
}

public String getNazwisko() {
  return nazwisko;
}

public Date getDataUrodzenia() {
  return dataUrodzenia;
}

public String getStanowisko() {
  return stanowisko;
}

public Projekt getProjekt() {
  return projekt;
}

public Dzial getDzial() {
  return dzial;
}

}