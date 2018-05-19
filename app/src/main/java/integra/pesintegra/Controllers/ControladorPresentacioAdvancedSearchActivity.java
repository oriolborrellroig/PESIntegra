package integra.pesintegra.Controllers;

import android.content.Context;

public class ControladorPresentacioAdvancedSearchActivity {
    private Context context;
    private ControladorDominiAdvancedSearchActivity cd;

    public ControladorPresentacioAdvancedSearchActivity(Context context){
        this.context = context;
        cd = new ControladorDominiAdvancedSearchActivity(this.context);
    }

    public void comprovaCamps(String dateIni, String dateFi, String user) throws Exception {
        cd.comprovaCamps(dateIni, dateFi, user);
    }

}
