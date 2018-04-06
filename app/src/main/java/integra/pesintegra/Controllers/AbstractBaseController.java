package integra.pesintegra.Controllers;

import java.util.Calendar;

public abstract class AbstractBaseController {

    //control d excepcions a fer...

    public void comprovaCampNoBuid(String s) throws Exception {
        if (s == "") {
            throw new Exception("Hi ha algun camp buit");
        }
    }

    public void comprovaDataNoAnterior(String d) throws Exception {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (false) { //s'ha de cambiar...
            throw new Exception("La data és anterior, no és vàlida");
        }
    }
   public void comprovaHoraValida(String h){

    }


    //Per si cal es pot cambiar. (agafa num / lletres pero no signes extranys)
    public void comprovaPasswordValida(String pw) throws Exception{

        char[] array = pw.toCharArray();
        for (char c : array) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                throw new Exception("Els caràcters no són vàlids");
            }
        }
    }

}
