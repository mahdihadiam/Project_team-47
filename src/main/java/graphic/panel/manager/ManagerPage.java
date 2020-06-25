package graphic.panel.manager;

import graphic.GraphicView;
import graphic.PageController;
import graphic.panel.AccountPage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.send.receive.ClientMessage;
import model.send.receive.ServerMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerPage extends PageController {

    public static Scene getScene() {
        return getScene("/fxml/panel/manager/ManagerPage.fxml");
    }

    @FXML
    private void logOut() {
        ClientMessage request = new ClientMessage("logout");
        ServerMessage answer = send(request);
        if (answer.getType().equals("Successful"))
            GraphicView.getInstance().goToFirstPage();
    }

    @FXML
    private void accountInfo() {
        GraphicView.getInstance().changeScene(AccountPage.getScene());
    }


    @Override
    public void clearPage() {

    }

    @Override
    public void update() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void manageDiscounts() {
        // TODO: 6/25/2020  
    }

    @FXML
    private void manageCategories() {
        // TODO: 6/25/2020  
    }

    @FXML
    private void manageUsers() {
        // TODO: 6/25/2020  
    }

    @FXML
    private void productsPage() {
        // TODO: 6/25/2020  
    }

    @FXML
    private void offsPage() {
        // TODO: 6/25/2020  
    }

    @FXML
    private void manageRequests() {
        // TODO: 6/25/2020  
    }
    
}
