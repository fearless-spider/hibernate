package pl.dmcs.firma.pojo;
import java.util.*;
import pl.dmcs.firma.pojo.*;

public class Projekt {
  private int ID;
  private String nazwa;
  private Date dataRozpoczecia;
  private Date dataKonca;
  
  //Set properties
  public void setID(int _id) {
    ID = _id;
  }
  
  public void setNazwa(String _nazwa) {
    nazwa = _nazwa;
  }
  
  public void setDataRozpoczecia(Date _dataRozpoczecia) {
    dataRozpoczecia = _dataRozpoczecia;
  }
  
  public void setDataKonca(Date _dataKonca) {
    dataKonca = _dataKonca;
  }
        
  //GetProperties
  public int getID() {
    return ID;
  }
  
  public String getNazwa() {
    return nazwa;
  }
  
  public Date getDataRozpoczecia() {
    return dataRozpoczecia;
  }
  
  public Date getDataKonca() {
    return dataKonca;
  }
    
}
