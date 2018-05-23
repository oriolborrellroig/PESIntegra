package integra.pesintegra.Controllers;

import integra.pesintegra.Presentation.SearchUserActivity;

public class ControladorPresentacioSearchUserActivity {
    private ControladorDominiSearchUserActivity cd;
    private SearchUserActivity act;

    public ControladorPresentacioSearchUserActivity(SearchUserActivity act){
        this.cd = new ControladorDominiSearchUserActivity(this);
        this.act = act;
    }

    public void getSearchUser(String user) {
        cd.getSearchUser(user);
    }

    public void setId(String Id) {
        act.setId(Id);
    }
}
