package graphicView.registerAndLoginMenu.registerAndLogin;

import graphicView.GraphicView;
import graphicView.PageController;
import graphicView.registerAndLoginMenu.loginMenu.LoginMenuPage;
import graphicView.registerAndLoginMenu.registerMenu.RegisterMenuPage;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAndLoginController extends PageController {
    private static PageController controller;

    public static PageController getInstance() {
        if (controller == null) {
            controller = new RegisterAndLoginController();
        }
        return controller;
    }

    @Override
    public void clearPage() {

    }

    @Override
    public void update() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void register(MouseEvent mouseEvent) {
        GraphicView.getInstance().changeScene(RegisterMenuPage.getInstance());
    }

    public void login(MouseEvent mouseEvent) {
        GraphicView.getInstance().changeScene(LoginMenuPage.getInstance());

    }

    public void back(MouseEvent mouseEvent) {
        GraphicView.getInstance().back();
    }
}
