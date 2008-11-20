package pl.dmcs.firma.pojo;

import java.util.*;
public class Dzial {

  private int ID;
  private String nazwa;
  private String lokalizacja;
  private List <Pracownik> listaPracownikow;
  //Set properties
  public void setID(int _id) {
    ID = _id;
  }
  
  public void setNazwa(String _nazwa) {
    nazwa = _nazwa;
  }
  
  public void setLokalizacja(String _lokalizacja) {
    lokalizacja = _lokalizacja;
  }

  public void setListaPracownikow(List <Pracownik> _listaPracownikow) {
    listaPracownikow = _listaPracownikow;
  }
    
  //Get properties
  public int getID() {
    return ID;
  }
  
  public String getNazwa() {
    return nazwa;
  }
  
  public String getLokalizacja() {
    return lokalizacja;
  }
  
  public List <Pracownik> getListaPracownikow() {
    return listaPracownikow;
  }
}
