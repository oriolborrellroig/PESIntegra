package integra.pesintegra.Controllers;

import android.content.Context;

import com.google.gson.JsonObject;

import integra.pesintegra.Presentation.InformationActivity;

public class ControladorPresentacioInfoUtil extends ControladorPresentacio  {

    private InformationActivity activity;
    private ControladorDominiInfoUtil Cdomini;

    public ControladorPresentacioInfoUtil (InformationActivity callActivity, Context cont) {
        this.activity = callActivity;
        this.Cdomini = new ControladorDominiInfoUtil (this);
    }


    public void getInfo () {
        Cdomini.getInfo();


    }

    public void posaInformacio(JsonObject body) {
        activity.posaInformacio(body);
    }
}
