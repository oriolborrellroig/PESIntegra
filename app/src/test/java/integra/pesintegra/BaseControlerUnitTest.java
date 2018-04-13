package integra.pesintegra;

import org.junit.Test;

import java.util.Calendar;

import integra.pesintegra.Controllers.AbstractBaseController;
import integra.pesintegra.Controllers.ControladorPresentacio;


public class BaseControlerUnitTest {

    private AbstractBaseController abc = new ControladorPresentacio();
    final Calendar c = Calendar.getInstance();



    //comprovaCampNoBuit TestPackage

    @Test(expected = Exception.class)
    public void comprovaCampNoBuitLlensaExcepcio() throws Exception {
        abc.comprovaCampNoBuid("");
    }

    @Test
    public void comprovaCampNoBuitEsCorrecte() throws Exception {
        abc.comprovaCampNoBuid("El camp no és buit");
    }



    //comprovaDataValida TestPackage

    @Test(expected = Exception.class)
    public void comprovaAnyAnterior() throws Exception {
        abc.comprovaDataValida("22/06/1999");
    }

    @Test(expected = Exception.class)
    public void comprovaAnyIgualPeroMesAnterior() throws Exception {
        abc.comprovaDataValida("22/03/2018");
    }

    @Test(expected = Exception.class)
    public void comprovaAnyIgualMesIgualPeroDiaAnterior() throws Exception {
        abc.comprovaDataValida("12/04/2018");
    }

    @Test
    public void comprovaDataValidaAny() throws Exception {
        abc.comprovaDataValida("22/03/2019");
    }

    @Test
    public void comprovaDataValidaMes() throws Exception {
        abc.comprovaDataValida("01/12/2018");
    }

    @Test
    public void comprovaDataValidaDia() throws Exception {
        abc.comprovaDataValida("31/04/2018");
    }




}
