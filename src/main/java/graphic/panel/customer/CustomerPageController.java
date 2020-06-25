package graphic.panel.customer;

import graphic.GraphicView;
import graphic.PageController;
import graphic.mainMenu.MainMenuController;
import graphic.panel.customer.CustomerPurchaseHistory.CustomerPurchaseHistoryController;
import graphic.panel.customer.CustomerPurchaseHistory.CustomerPurchaseHistoryPage;
import graphic.productsMenu.ProductsMenuPage;
import graphic.registerAndLoginMenu.registerAndLogin.RegisterAndLoginPage;
import javafx.scene.input.MouseEvent;
import model.send.receive.ClientMessage;
import model.send.receive.LogInfo;
import model.send.receive.ServerMessage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerPageController extends PageController {
    private static PageController controller;

    public static PageController getInstance() {
        if (controller == null) {
            controller = new CustomerPageController();
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

    public void showPersonalInfo(MouseEvent mouseEvent) {
    }

    public void goToShoppingCart(MouseEvent mouseEvent) {
    }

    public void showPurchaseHistory(MouseEvent mouseEvent) {
        //getting purchase history
        ClientMessage request = new ClientMessage("view orders");
        //todo

        ServerMessage answer = send(request);

        if(answer.getType().equals("Successful")){
            ArrayList<LogInfo> logInfoArrayList = answer.getLogInfoArrayList();
            ((CustomerPurchaseHistoryPage)CustomerPurchaseHistoryPage.getInstance()).setLogInfoArrayList(logInfoArrayList);
            GraphicView.getInstance().changeScene(CustomerPurchaseHistoryPage.getInstance());
        } else {
            //todo amir
            System.out.println("oops");
        }


        //going purchase history
        GraphicView.getInstance().changeScene(CustomerPurchaseHistoryPage.getInstance());

    }

    public void showDiscountCodes(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
        GraphicView.getInstance().back();
    }
}
