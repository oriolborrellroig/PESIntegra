package integra.pesintegra.Controllers;

import java.util.Calendar;
import java.util.regex.Pattern;

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
                if (Integer.parseInt(d.substring(0, 2)) <= day ) {
                    throw new Exception("La data és anterior, no és vàlida. Dia incorrecte.");
                }
            }
        }
    }

    public Boolean comprovaDataExpired(String d) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int dayExpire = Integer.parseInt(d.substring(0, 2)) + 7;
        int monthExpire;
        int yearExpire;
            if (dayExpire > 31) {
                dayExpire = dayExpire - 31;
                monthExpire = Integer.parseInt(d.substring(3, 5)) + 1;
                if (monthExpire > 12) {
                    monthExpire = monthExpire - 12;
                    yearExpire = Integer.parseInt(d.substring(6, 10)) + 1;
                }
                else {
                    yearExpire = Integer.parseInt(d.substring(6, 10));
                }
            }
            else {
                monthExpire = Integer.parseInt(d.substring(3, 5));
                yearExpire = Integer.parseInt(d.substring(6, 10));
            }
        if (yearExpire < year ) {
            return Boolean.TRUE;
        }
        else if (yearExpire == year ) {
            if (monthExpire < month + 1) {
                return Boolean.TRUE;
            }
            else if (monthExpire == month + 1 ) {
                if (dayExpire <= day ) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }


    public static void valid_mail(String email) throws Exception
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            throw new Exception("Format mail incorrecte");
        if (!pat.matcher(email).matches())
            throw new Exception("Format mail incorrecte");
    }

    public void comprova_contrasenya_coincident(String pass1, String pass2) throws Exception {
        if (!pass1.equals(pass2))
            throw new Exception("Les passwords no coincideixen");
    }



    public void data_naix_correcte(String dataN) throws Exception{

        String[] parts = dataN.split("/");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        int dia_i = Integer.parseInt(part1);
        int mes_i = Integer.parseInt(part2);
        int any_i = Integer.parseInt(part3);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        boolean result = false;
        if (any_i > year) throw new Exception("Data naixement incorrecte");
        else if (any_i == year){
            if (mes_i > month) throw new Exception("Data naixement incorrecte");
            else if (mes_i == month){
                if (dia_i > day) throw new Exception("Data naixement incorrecte");
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
