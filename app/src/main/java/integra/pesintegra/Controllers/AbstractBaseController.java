package integra.pesintegra.Controllers;

import com.google.android.gms.maps.model.LatLng;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.regex.Pattern;


import integra.pesintegra.R;

import static integra.pesintegra.Presentation.LoginActivity.resources;

public abstract class AbstractBaseController {


    public void comprovaCampNoBuid(String s) throws Exception {
        if (s == null || s.equals("")) {
            throw new Exception(resources.getString(R.string.ERRemptyFields));
        }
    }

    void comprovaCampNoNull(LatLng l) throws Exception {
        if (l == null) {
            throw new Exception(resources.getString(R.string.ERRnoValidLoc));
        }
    }


    public void comprovaDataValida(String d) throws Exception {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (Integer.parseInt(d.substring(6, 10)) < year ) {
            throw new Exception(resources.getString(R.string.ERRnotValidDate));
        }
        else if (Integer.parseInt(d.substring(6, 10)) == year ) {
            if (Integer.parseInt(d.substring(3, 5)) < month + 1) {
                throw new Exception(resources.getString(R.string.ERRnotValidDate));
            }
            else if (Integer.parseInt(d.substring(3, 5)) == month + 1 ) {
                if (Integer.parseInt(d.substring(0, 2)) <= day ) {
                    throw new Exception(resources.getString(R.string.ERRnotValidDate));
                }
            }
        }
    }


    public int comprova_participants_to_integer (String participants) throws Exception {

        if (!Pattern.matches("[a-zA-Z]+", participants)) {
            return Integer.parseInt(participants);
        }
        else {
            throw new Exception(resources.getString(R.string.ERRnotValidAttendants));
        }
    }


    static void valid_mail(String email) throws Exception
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            throw new Exception(resources.getString(R.string.ERRincorrectMail));
        if (!pat.matcher(email).matches())
            throw new Exception(resources.getString(R.string.ERRincorrectMail));
    }


    void comprova_contrasenya_coincident(String pass1, String pass2) throws Exception {
        if (!pass1.equals(pass2))
            throw new Exception(resources.getString(R.string.ERRpasswordNotMatch));
    }


    void data_naix_correcte(String dataN) throws Exception{

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
        if (any_i > year) throw new Exception(resources.getString(R.string.ERRnotValidDate));
        else if (any_i == year){
            if (mes_i > month) throw new Exception(resources.getString(R.string.ERRnotValidDate));
            else if (mes_i == month){
                if (dia_i > day) throw new Exception(resources.getString(R.string.ERRnotValidDate));
            }
        }
    }

    public String hash_password(String pas) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pas.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
