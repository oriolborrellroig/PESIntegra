package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.R;


class ControladorDominiAdvancedSearchActivity {

    private Context context;

    ControladorDominiAdvancedSearchActivity(Context context){
        this.context = context;
    }

    public void comprovaCamps(String dateIni, String dateFi, String user) throws Exception {
        comprovaDates(dateIni, dateFi);
    }



    //Throw Exception si dateFi < dateIni
    private void comprovaDates(String dateIni, String dateFi) throws Exception{
        int yIni = Integer.parseInt(dateIni.substring(6, 10));
        int yFi = Integer.parseInt(dateFi.substring(6, 10));
        int mIni = Integer.parseInt(dateIni.substring(3, 5));
        int mFi = Integer.parseInt(dateFi.substring(3, 5));
        int dIni = Integer.parseInt(dateIni.substring(0, 2));
        int dFi = Integer.parseInt(dateFi.substring(0, 2));

        if (yFi < yIni) throw new Exception(context.getResources().getString(R.string.invalid_date_year));
        else if (yFi == yIni){
            if (mFi < mIni) throw new Exception(context.getResources().getString(R.string.invalid_date_month));
            else if (mFi == mIni){
                if (dFi < dIni) throw new Exception(context.getResources().getString(R.string.invalid_date_day));
            }
        }
    }
}
