package no.hist.tdat.javabeans;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vimCnett on 09.01.14.
 * NB!!! Mangler variabel for øvinger som er gjort
 */
public class Bruker {
    private static final String RANDOM_TEGN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
    @NotBlank
    private String mail;
    private Integer rettighet;
    private String fornavn;
    private String etternavn;
    @NotBlank
    private String passord;
    private String gammeltPassord;
    private String bekreftPassord;
    private String nyttPassord;

    private int aktiv;
    private ArrayList<Emner> emner;

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn, int aktiv) {
        this.mail = mail;
        this.rettighet = rettighet;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = genererPassord();
        this.aktiv = aktiv;
        emner = new ArrayList<Emner>();
    }

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn) {
        this.mail = mail;
        this.rettighet = rettighet;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = genererPassord();
        this.aktiv = 1;
        emner = new ArrayList<Emner>();
    }

    public Bruker() {

    }

    /**
     * Konstruktør for innlogging
     *
     * @param mail
     * @param passord
     */
    public Bruker(String mail, String passord) {
        this.mail = mail;
        this.passord = krypterPassord(passord);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getRettighet() {
        return rettighet;
    }

    public void setRettighet(Integer rettighet) {
        this.rettighet = rettighet;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getPassord() {
        return passord;
    }

    /**
     * Bruker hjelpemetoden krypterPassord til å sette passord til bruker
     *
     * @param passord
     */
    public void setPassord(String passord) {
        this.passord = krypterPassord(passord);
    }


    public int getAktiv() {
        return aktiv;
    }

    public void setAktiv(int aktiv) {
        this.aktiv = aktiv;
    }

    public ArrayList<Emner> getEmner() {
        return emner;
    }

    public void setEmner(ArrayList<Emner> emner) {
        this.emner = emner;
    }

    /**
     * Hjelpemetode til genererPassord, generer random int verdier
     *
     * @return et tall mellom 0 og RANDOM_TEGN.length()
     */
    public int randomIndex() {
        int min = 0;
        int max = RANDOM_TEGN.length();
        return random.nextInt((max - min) + 1) + min;

    }

    /**
     * Generer et random passord på lengde 6 tegn
     *
     * @return kryptert random passord på lengde 6
     */
    public String genererPassord() {
        String passord = "";
        for (int i = 0; i < 6; i++) {
            passord += RANDOM_TEGN.charAt(randomIndex());
        }
        return passord;
    }


    public void addEmne() {
        //TODO legg til et emne en bruker er medlem av. Her skal ikke tilgangsrettigheter være
    }

    /**
     * Tar inn en kryptert string, lager en ny string med lengde 32
     *
     * @param krypt1 et kryptert passord med 4*passord.length lengde
     * @return Kryptert passord med lengde 32
     */
    private String krypterPassord2(String krypt1) {
        int length = krypt1.length();
        int index = 0;
        String dobbelKrypt = "";
        for (int i = 0; i < 32; i++) {
            index = i;
            index %= length;
            dobbelKrypt += krypt1.charAt(index);
        }
        return dobbelKrypt;
    }

    /**
     * Tar inn tre variabler, det gamle, nye og bekrefta det nye.
     *
     * @param gPassord passord, nytt PW & bekreft nytt PW
     * @return Boolean, passordet endret eller ikkje
     * @author vimCnett
     */
    public boolean endrePassord(String gPassord, String nPassord, String bPassord) {
        if ((gPassord.equals(this.passord)) && (nPassord.equals(bPassord))) {
            setPassord(nPassord);
            return true;
        }
        return false;
    }

    public String getBekreftPassord() {
        return bekreftPassord;
    }

    public void setBekreftPassord(String bekreftPassord) {
        this.bekreftPassord = bekreftPassord;
    }

    public String getGammeltPassord() {
        return gammeltPassord;
    }

    public void setGammeltPassord(String gammeltPassord) {
        this.gammeltPassord = gammeltPassord;
    }

    public String getNyttPassord() {
        return nyttPassord;
    }

    public void setNyttPassord(String nyttPassord) {
        this.nyttPassord = nyttPassord;
    }


    /**
     * Tar inn en string fra brukeren og krypterer passordet.
     *
     * @param pw passord skrevet inn av bruker
     * @return String som kryptert passord med varierende lengde ( 4*pw.length )
     * @author vimCnett
     * @see "The Java Programming Language"
     */
    private String krypterPassord(String pw) {
        String alphaString = ("abcdefghijklmnopqrstuvwxyz").toUpperCase();
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyz" + alphaString + "123567890").toCharArray();
        int length = alphabet.length;
        String kryptertPassord = "";
        int verdi = 0;
        int verdi2 = 0;
        char part1;
        char part2;
        char part3;
        char part4;
        for (int i = 0; i < pw.length(); i++) {
            verdi = (int) pw.charAt(i);
            verdi *= verdi;

            verdi2 = i * verdi * verdi2;

            part1 = (char) alphabet[(verdi % length)];
            part2 = (char) alphabet[((i * 103) % length)];
            part3 = (char) alphabet[(int) ((i * verdi2 * 708) % length)];
            part4 = (char) alphabet[(((1337 * verdi2 + verdi) % 713) % length)];

            kryptertPassord += part1;
            kryptertPassord += part2;
            kryptertPassord += part3;
            kryptertPassord += part4;
        }
        return krypterPassord2(kryptertPassord);
    }


}
