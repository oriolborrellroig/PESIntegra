package integra.pesintegra.Controllers;

import android.content.Context;

public class ControladorPresentacioAdvancedSearchActivity {
    private ControladorDominiAdvancedSearchActivity cd;

    public ControladorPresentacioAdvancedSearchActivity(Context context){
        cd = new ControladorDominiAdvancedSearchActivity(context);
    }

    public void comprovaCamps(String dateIni, String dateFi, String user) throws Exception {
        cd.comprovaCamps(dateIni, dateFi, user);
    }

}
