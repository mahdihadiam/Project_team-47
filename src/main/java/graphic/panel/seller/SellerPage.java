package graphic.panel.seller;

import graphic.GraphicView;
import graphic.PageController;
import graphic.TemplatePage;
import graphic.panel.AccountPage;
import graphic.panel.seller.log.LogsPage;
import graphic.panel.seller.offs.ManageOffsPage;
import graphic.panel.seller.products.ManageProductsPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import model.send.receive.ClientMessage;
import model.send.receive.ServerMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerPage extends PageController {
    private static PageController controller;
    @FXML private ImageView avatar;

    public static PageController getInstance() {
        return controller;
    }

    public static Scene getScene() {
        return getScene("/fxml/panel/seller/SellerPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = this;
        avatar.setImage(GraphicView.getInstance().getAvatar());
        update();
    }

    @FXML
    private void logOut() {
        ClientMessage request = new ClientMessage("logout");
        ServerMessage answer = send(request);
        if (answer.getType().equals("Successful")) {
            GraphicView.getInstance().setLoggedIn(false);
            GraphicView.getInstance().goToFirstPage();
        }
    }

    @FXML
    private void accountInfo() {
        GraphicView.getInstance().changeScene(AccountPage.getScene());
    }

    @FXML
    private void manageOffs() {
        GraphicView.getInstance().changeScene(ManageOffsPage.getScene());
    }

    @FXML
    private void manageProducts() {
        GraphicView.getInstance().changeScene(ManageProductsPage.getScene());
    }

    @FXML
    private void productsPage() throws IOException {
        GraphicView.getInstance().changeScene(TemplatePage.getScene());
        TemplatePage.getInstance().changePane(FXMLLoader.load(getClass().getResource("/fxml/products/Products.fxml")));
    }

    @FXML
    private void buyLogs() {
        GraphicView.getInstance().changeScene(LogsPage.getScene());
    }

    @Override
    public void clearPage() {
    }

    @Override
    public void update() {
    }

}
