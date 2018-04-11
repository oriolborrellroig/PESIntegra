package integra.pesintegra.Controllers;

import java.util.Calendar;

public abstract class AbstractBaseController {


    public void comprovaCampNoBuid(String s) throws Exception {
        if (s.equals("")) {
            throw new Exception("Hi ha algun camp buit");
        }
    }

    public void comprovaDataValida(String d) throws Exception {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (Integer.parseInt(d.substring(6, 10)) < year ) {
            throw new Exception("La data és anterior, no és vàlida. Any incorrecte.");
        }
        else if (Integer.parseInt(d.substring(6, 10)) == year ) {
            if (Integer.parseInt(d.substring(3, 5)) < month + 1) {
                throw new Exception("La data és anterior, no és vàlida. Mes incorrecte.");
            }
            else if (Integer.parseInt(d.substring(3, 5)) == month + 1 ) {
                if (Integer.parseInt(d.substring(0, 2)) < day ) {
                    throw new Exception("La data és anterior, no és vàlida. Dia incorrecte.");
                }
            }
        }
    }


    /*public void comprovaHoraValida(String h){

    }*/


    //Per si cal es pot cambiar. (agafa num / lletres pero no signes extranys)

    public void comprovaPasswordValida(String pw) throws Exception{

        char[] array = pw.toCharArray();
        for (char c : array) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                throw new Exception("Els caràcters no són vàlids");
            }
        }
    }


    public String dataActual () {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String data = day + "/" + month + 1 + "/" + year;
        return data;
    }

}
