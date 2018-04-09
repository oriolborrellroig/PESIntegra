package integra.pesintegra.Logic.Clases;

import android.net.Uri;

import java.util.UUID;

public abstract class Post {

    private String id, titol, descripcio, dataini, datafi, hora, direccio;
    private Uri uri;
    private char tipus;

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
}
