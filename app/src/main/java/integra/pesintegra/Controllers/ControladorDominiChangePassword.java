package integra.pesintegra.Controllers;


import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ControladorDominiChangePassword extends ControladorDomini {

    public void canviar_contrasenya(String current_pass, String new_pass){
        String cpass = hash_password(current_pass);
        String npass = hash_password(new_pass);
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall2 = service.updatePasswordUser(cpass, npass);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                    ControladorPresentacioChangePassword.answer(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
