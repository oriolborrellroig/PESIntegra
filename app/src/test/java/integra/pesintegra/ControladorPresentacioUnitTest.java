package integra.pesintegra;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;


import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;

import static org.junit.Assert.*;

public class ControladorPresentacioUnitTest {

    private ControladorPresentacio cp = new ControladorPresentacio();
    private LatLng coord = new LatLng(0, 0);


    //comprovaCreaPostActivitat Package

    @Test(expected = Exception.class)
    public void comprovaCreaPostActivitatPostCampBuit() throws Exception {
        cp.creaPostActivitat("Prova", "Partit de futbol","15/05/2020", "15/05/2020", "", "", coord, "CA");
    }


    @Test(expected = Exception.class)
    public void comprovaCreaPostActivitatPostDataInvalida() throws Exception {
        cp.creaPostActivitat("Prova", "Partit de futbol","15/05/2015", "15/05/2015", "13:05", "adfadf", coord, "CA");
    }


    @Test
    public void comprovaCreaPostActivitatPostCreatCampsOK() throws Exception {
        Post_Activitat a = cp.creaPostActivitat("Prova", "Partit de futbol","15/05/2019", "15/05/2019", "13:05", "Carrer del Temple", coord, "CA");
        Post_Activitat ap = new Post_Activitat("Prova", "Partit de futbol","15/05/2019", "15/05/2019", "13:05", "Carrer del Temple", 0, 0, "CA");
        boolean comprovaCampsIgualsIDdiferent = (a.getTitol().equals("Prova") && a.getDescripcio().equals("Partit de futbol") && a.getDataIni().equals("15/05/2019" )
                && a.getDataFi().equals("15/05/2019") && a.getHora().equals("13:05") && a.getLocalitzacio().equals("Carrer del Temple") && !a.getId().equals(ap.getId()));
        assertTrue(comprovaCampsIgualsIDdiferent);
    }


    //comprovaCreaPostHabitatge Package


    @Test(expected = Exception.class)
    public void comprovaCreaPostHabitatgePostCampBuit() throws Exception {
        cp.creaPostHabitatge("Prova", "Partit de futbol","15/05/2020", "15/05/2020", "", "", coord,"CA");
    }


    @Test(expected = Exception.class)
    public void comprovaCreaPostHabitatgePostDataInvalida() throws Exception {
        cp.creaPostHabitatge("Prova", "Partit de futbol","15/05/2015", "15/05/2015", "13:05", "adfadf", coord, "CA");
    }


    @Test
    public void comprovaCreaPostHabitatgePostCreatCampsOK() throws Exception {
        Post_Habitatge a = cp.creaPostHabitatge("Prova", "Partit de futbol","15/05/2019", "15/05/2019", "13:05", "Carrer del Temple", coord, "CA");
        Post_Habitatge ap = new Post_Habitatge("Prova", "Partit de futbol","15/05/2019", "15/05/2019", "13:05", "Carrer del Temple", 0, 0, "CA");
        boolean comprovaCampsIgualsIDdiferent = (a.getTitol().equals("Prova") && a.getDescripcio().equals("Partit de futbol") && a.getDataIni().equals("15/05/2019" )
                && a.getDataFi().equals("15/05/2019") && a.getHora().equals("13:05") && a.getLocalitzacio().equals("Carrer del Temple") && !a.getId().equals(ap.getId()));
        assertTrue(comprovaCampsIgualsIDdiferent);
    }


    //comprovaCreaPostFeina Package

    @Test(expected = Exception.class)
    public void comprovaCreaPostFeinaPostCampBuit() throws Exception {
        cp.creaPostFeina("Prova", "Partit de futbol","15/05/2020", "15/05/2020", "", "", coord, "CA");
    }

    @Test(expected = Exception.class)
    public void comprovaCreaPostFeinaPostDataInvalida() throws Exception {
        cp.creaPostFeina("Prova", "Partit de futbol","15/05/2015", "15/05/2015", "13:05", "adfadf", coord, "CA");
    }

    @Test
    public void comprovaCreaPostFeinaPostCreatCampsOK() throws Exception {
        Post_Feina a = cp.creaPostFeina("Prova", "Partit de futbol","15/05/2019", "15/05/2019", "13:05", "Carrer del Temple", coord, "CA");
        Post_Feina ap = new Post_Feina("Prova", "Partit de futbol","15/05/2019", "15/05/2019", "13:05", "Carrer del Temple", 0, 0, "CA");
        boolean comprovaCampsIgualsIDdiferent = (a.getTitol().equals("Prova") && a.getDescripcio().equals("Partit de futbol") && a.getDataIni().equals("15/05/2019" )
                && a.getDataFi().equals("15/05/2019") && a.getHora().equals("13:05") && a.getLocalitzacio().equals("Carrer del Temple") && !a.getId().equals(ap.getId()));
        assertTrue(comprovaCampsIgualsIDdiferent);
    }

}
