package integra.pesintegra.Logic.Clases;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public abstract class Post {

    @SerializedName("id")
    private String id;
    @SerializedName("titol")
    private String titol;
    @SerializedName("descripcio")
    private String descripcio;
    @SerializedName("dataini")
    private String dataini;
    @SerializedName("datafi")
    private String datafi;
    @SerializedName("hora")
    private String hora;
    @SerializedName("direccio")
    private String direccio;
    private Uri uri;
    @SerializedName("tipus")
    private char tipus;
    private Bitmap imatge;

    public Post(char tipus){
        setId();
        this.tipus = tipus;
    }

    Post(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, char tipus){
        this.titol = titol;
        this.descripcio = descripcio;
        this.dataini = dataini;
        this.datafi = datafi;
        this.hora = hora;
        this.direccio = direccio;
        this.tipus = tipus;
        setId();
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

    public String getDireccio(){
        return this.direccio;
    }

    public void setDireccio(String direccio){
        this.direccio = direccio;
    }

    public Uri getUri(){
        return this.uri;
    }

    public void setUri(Uri uri){
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId() {
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
}
