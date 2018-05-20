package integra.pesintegra.Logic.Clases;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Post implements Serializable {

    private String id;
    private String titol;
    private String descripcio;
    private String dataini;
    private String datafi;
    private String hora;
    private String localitzacio;
    private String uri;
    private String owner;
    private char tipus;
    private Bitmap imatge;
    private double lat;
    private double lng;
    private String idioma;
    private ArrayList<String> interessos;

    private boolean hidden;

    private int puntuacio;
    private int nombreVots;


    public Post(char tipus){
        setId();
        setShow();
        this.tipus = tipus;
    }

    Post(String titol, String descripcio, String dataini, String datafi, String hora, String localitzacio, String owner, char tipus, double lat, double lng, String idioma){
        this.titol = titol;
        this.descripcio = descripcio;
        this.dataini = dataini;
        this.datafi = datafi;
        this.hora = hora;
        this.localitzacio = localitzacio;
        this.owner = owner;
        this.tipus = tipus;
        this.lat = lat;
        this.lng = lng;
        this.idioma = idioma;
        this.puntuacio = 0;
        this.nombreVots = 0;
        this.interessos = new ArrayList<>();
        setId();
        setShow();
    }

    public String getTitol(){
        return this.titol;
    }

    public void setTitol(String titol){
        this.titol = titol;
    }

    public String getDescripcio(){
        return this.descripcio;
    }

    public void setDescripcio(String descripcio){
        this.descripcio = descripcio;
    }

    public String getDataIni(){
        return this.dataini;
    }

    public void setTDataIni(String dataini){
        this.dataini = dataini;
    }

    public String getDataFi(){
        return this.datafi;
    }

    public void setDataFi(String datafi){
        this.datafi = datafi;
    }

    public String getHora(){
        return this.hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getLocalitzacio(){
        return this.localitzacio;
    }

    public void setLocalitzacio(String localitzacio){
        this.localitzacio = localitzacio;
    }

    public String getUri(){
        return this.uri;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    private void setId() {
        this.id = this.tipus + '_' + UUID.randomUUID().toString();
    }

    public char getTipus(){
        return this.tipus;
    }

    public void setImatge(Bitmap imatge){
        this.imatge = imatge;
    }

    public Bitmap getImatge() {
        return imatge;
    }

    public void setHidden (){this.hidden=true;}

    private void setShow(){this.hidden=false;}

    public boolean isShowed(){return !this.hidden;}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public List<String> getInteressos(){
        return interessos;
    }

    public String getIdioma() { return idioma; }

    public int getPuntuacio() {
        return puntuacio;
    }

    public int getNombreVots() {
        return nombreVots;
    }
}
