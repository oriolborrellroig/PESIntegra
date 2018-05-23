package integra.pesintegra.Controllers;

public class ControladorDominiSearchUserActivity {
    private ControladorPresentacioSearchUserActivity cp;

    public ControladorDominiSearchUserActivity(ControladorPresentacioSearchUserActivity cp){
        this.cp = cp;
    }

    public void getSearchUser(String user) {
        //Aqui va la crida a BD

        cp.setId("null_60cfecd6-955a-408b-be76-394df0ea18be");
    }
}
