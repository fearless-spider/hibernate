package pl.dmcs.firma.util;
import pl.dmcs.firma.pojo.Pracownik;
import pl.dmcs.firma.pojo.Dzial;
import pl.dmcs.firma.pojo.Projekt;
import java.util.Date;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

public class test {
  public static void main(String [] args) {
    Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    while(true) {
      Session session = HibernateUtil.currentSession();
      Transaction tx = session.beginTransaction();
      test test = new test();
      System.out.println("Menu: \n 1:Pokaz liste projektow\n2:Pokaz liste dzialow\n3:Pokaz liste pracownikow\n4:Dodaj projekt\n5:Dodaj dzial\n6:Dodaj pracownika\n7:Dodaj pracownika do dzialu\n8:Edytuj projekt\n9: Edytuj dzial \n10:Edytuj pracownika\n0:Wyjscie\n");
      switch(scanner.nextInt()) {
        case 0:
          tx.commit();
          HibernateUtil.closeSession();
          System.exit(1);
        break;
        case 1:
          test.getProjektList(session);
          tx.commit();
        break;
        case 2:
          test.getDzialList(session);
          tx.commit();
        break;
        case 3:
          test.getPracownikList(session);
          tx.commit();
        break;
        case 4:
          test.addProjekt(session, scanner);
          tx.commit();
        break;
        case 5:
          test.addDzial(session, scanner);
          tx.commit();
        break;
        case 6:
          test.addPracownik(session, scanner);
          tx.commit();
        break;
        case 7:
          test.movePracownikToDzial(session, scanner);
          tx.commit();
        break;
        case 8:
          test.editProjekt(session, scanner);
          tx.commit();
        break;
        case 9:
          test.editDzial(session, scanner);
          tx.commit();
        break;
        case 10:
          test.editPracownik(session, scanner);
          tx.commit();
        break;
      }
    }
  }
  
  private void getProjektList(Session session) {
    Query query = session.createQuery("from Projekt");
    for(Iterator it = query.iterate(); it.hasNext();) {
      Projekt projekt = (Projekt) it.next();
      System.out.println(projekt.getID() + " : " + projekt.getNazwa());
    }
  }
  
  private void getDzialList(Session session) {
    Query query = session.createQuery("from Dzial");
    for(Iterator it = query.iterate(); it.hasNext();) {
      Dzial dzial = (Dzial) it.next();
      System.out.println(dzial.getID() + " : " + dzial.getNazwa());
    }
  }
  
  private void getPracownikList(Session session) {
    Query query = session.createQuery("from Pracownik");
    for(Iterator it = query.iterate(); it.hasNext();) {
      Pracownik pracownik = (Pracownik) it.next();
      System.out.println(pracownik.getID() + " : " + pracownik.getImie() + " " + pracownik.getNazwisko());
    }
  }
  
  private void addProjekt(Session session, Scanner scanner) {
    String nazwa;
    Integer rok, miesiac, dzien;
    System.out.println("Podaj nazwe nowego projektu : ");
    nazwa = scanner.next();
    System.out.println("Podaj date rozpoczecia nowego projektu w liczb , rok : ");
    rok = scanner.nextInt();
    System.out.println("\nmiesiac :");
    miesiac = scanner.nextInt();
    System.out.println("\ndzien :");
    dzien = scanner.nextInt();
    Projekt projekt = new Projekt();
    projekt.setNazwa(nazwa);
    projekt.setDataRozpoczecia(new Date(rok+1900, miesiac, dzien));
    
    session.save(projekt);
  }
  
  private void addDzial(Session session, Scanner scanner) {
    String nazwa;
    String lokalizacja;
    System.out.println("Podaj nazwe nowego dzialu : ");
    nazwa = scanner.next();
    System.out.println("Podaj lokalizacje dzialu : ");
    lokalizacja = scanner.next();
    Dzial dzial = new Dzial();
    dzial.setNazwa(nazwa);
    dzial.setLokalizacja(lokalizacja);
    
    session.save(dzial);
  }
  
  public void addPracownik(Session session, Scanner scanner) {
    String imie;
    String nazwisko;
    Integer rok, miesiac, dzien;
    String stanowisko;
    Integer numerDzialu;
    Integer numerProjektu;
    System.out.println("Podaj imie : ");
    imie = scanner.next();
    System.out.println("Podaj nazwisko : ");
    nazwisko = scanner.next();
    System.out.println("Podaj date urodzenia, rok :\n");
    rok = scanner.nextInt();
    System.out.println("miesiac : \n");
    miesiac = scanner.nextInt();
    System.out.println("dzien : \n");
    dzien = scanner.nextInt();
    System.out.println("Podaj stanowisko : ");
    stanowisko = scanner.next();
    getDzialList(session);
    System.out.println("Podaj numer dzialu : ");
    numerDzialu = scanner.nextInt();
    getProjektList(session);
    System.out.println("Podaj numer projektu : ");
    numerProjektu = scanner.nextInt();
    
    Dzial dzial = (Dzial) session.load(Dzial.class, numerDzialu);
    Projekt projekt = (Projekt) session.load(Projekt.class, numerProjektu);
    
    Pracownik pracownik = new Pracownik();
    pracownik.setImie(imie);
    pracownik.setNazwisko(nazwisko);
    pracownik.setDataUrodzenia(new Date(rok,miesiac, dzien));
    pracownik.setStanowisko(stanowisko);
    pracownik.setDzial(dzial);
    pracownik.setProjekt(projekt);
    
    session.save(pracownik);
  }
  
  private void movePracownikToDzial(Session session, Scanner scanner) {
    Integer numerPracownika;
    Integer numerDzialu;
    getPracownikList(session);
    System.out.println("Podaj numer pracownika : ");
    numerPracownika = scanner.nextInt();
    getDzialList(session);
    System.out.println("Podaj numer dzialu : ");
    numerDzialu = scanner.nextInt();
    
    Dzial dzial = (Dzial) session.load(Dzial.class, numerDzialu);
    Pracownik pracownik = (Pracownik) session.load(Pracownik.class, numerPracownika);
    pracownik.setDzial(dzial);
    
    session.update(pracownik);
  }
  
  private void editProjekt(Session session, Scanner scanner) {
    Integer numerProjektu;
    String nazwa;
    Integer rok, miesiac, dzien;
    getProjektList(session);
    System.out.println("Podaj numer projektu : ");
    numerProjektu = scanner.nextInt();
    System.out.println("Podaj nowa nazwe projektu : ");
    nazwa = scanner.next();
    System.out.println("Podaj date zakonczenia, rok :\n ");
    rok = scanner.nextInt();
    System.out.println("miesiac : \n");
    miesiac = scanner.nextInt();
    System.out.println("dzien : \n");
    dzien = scanner.nextInt();
        
    Projekt projekt = (Projekt) session.load(Projekt.class, numerProjektu);
    
    projekt.setNazwa(nazwa);
    projekt.setDataKonca(new Date(rok, miesiac, dzien));
    
    session.update(projekt);
  }
  
  private void editDzial(Session session, Scanner scanner) {
    Integer numerDzialu;
    String nazwa;
    String lokalizacja;
    
    getDzialList(session);
    System.out.println("Podaj numer dzialu : ");
    numerDzialu = scanner.nextInt();
    System.out.println("Podaj nowa nazwe dzialu : ");
    nazwa = scanner.next();
    System.out.println("Podaj nowa lokalizacje : ");
    lokalizacja = scanner.next();
    
    Dzial dzial = (Dzial) session.load(Dzial.class, numerDzialu);
    
    dzial.setNazwa(nazwa);
    dzial.setLokalizacja(lokalizacja);
    
    session.update(dzial);
  }

  private void editPracownik(Session session, Scanner scanner) {
    Integer numerPracownika;
    String imie;
    String nazwisko;
    String stanowisko;
    Integer rok, miesiac, dzien;
    
    getPracownikList(session);
    System.out.println("Podaj numer pracownika : ");
    numerPracownika = scanner.nextInt();
    System.out.println("Podaj nowe imie : ");
    imie = scanner.next();
    System.out.println("Podaj nowe nazwisko : ");
    nazwisko = scanner.next();
    System.out.println("Podaj nowe stanowisko : ");
    stanowisko = scanner.next();
    System.out.println("Podaj date urodzenia, rok : \n");
    rok = scanner.nextInt();
    System.out.println("miesiac : \n");
    miesiac = scanner.nextInt();
    System.out.println("dzien : \n");
    dzien = scanner.nextInt();
    
    Pracownik pracownik = (Pracownik) session.load(Pracownik.class, numerPracownika);
    
    pracownik.setImie(imie);
    pracownik.setNazwisko(nazwisko);
    pracownik.setStanowisko(stanowisko);
    pracownik.setDataUrodzenia(new Date(rok, miesiac, dzien));
    
    session.update(pracownik);
  }
}
