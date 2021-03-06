package graphic.panel.manager;

import graphic.MainFX;
import graphic.PageController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.send.receive.ClientMessage;
import model.send.receive.DiscountCodeInfo;
import model.send.receive.ServerMessage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ManageDiscountPage extends PageController {
    private static PageController controller;
    @FXML
    private VBox codesVBox;
    @FXML
    private TextField discountId;
    @FXML
    private TextField editField;
    @FXML
    private TextField editValue;
    @FXML
    private Text editError;

    public static Scene getScene() {
        return getScene("/fxml/panel/manager/ManageDiscountPage.fxml");
    }

    public static PageController getController() {
        return controller;
    }

    @Override
    public void clearPage() {
        editField.setText("");
        editValue.setText("");
        discountId.setText("");
        editError.setVisible(false);
    }

    @Override
    public void update() {
        clearPage();
        ClientMessage request = new ClientMessage("view discount codes manager");
        ServerMessage answer = send(request);
        initializeVBox(answer.getDiscountCodeInfoArrayList());
    }

    private void initializeVBox(ArrayList<DiscountCodeInfo> codes) {
        codesVBox.getChildren().clear();
        if (codes.size() == 0) {
            Label empty = new Label("There isn't any code");
            empty.setFont(new Font(20));
            codesVBox.getChildren().add(empty);
            return;
        }
        for (DiscountCodeInfo code : codes) {
            Label codeLabel = new Label(code.getCode());
            codeLabel.setFont(new Font(15));
            codeLabel.setOnMouseClicked(event -> handleCodeLabelClick(code, event));
            codesVBox.getChildren().add(codeLabel);
        }
    }

    private void handleCodeLabelClick(DiscountCodeInfo code, MouseEvent event) {
        MainFX.getInstance().click();
        if (event.getButton() == MouseButton.PRIMARY) {
            codePage(code);
        } else if (event.getButton() == MouseButton.SECONDARY) {
            deleteCode(code.getCode());
        }
    }

    private void deleteCode(String code) {
        MainFX.getInstance().click();
        ClientMessage request = new ClientMessage("remove discount code manager");
        HashMap<String, String> reqInfo = new HashMap<>();
        reqInfo.put("code", code);
        request.setHashMap(reqInfo);
        send(request);
        update();
    }

    private void codePage(DiscountCodeInfo code) {
        MainFX.getInstance().changeScene(DiscountPage.getScene(code));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = this;
        update();
    }

    @FXML
    private void back() {
        MainFX.getInstance().click();
        MainFX.getInstance().back();
    }

    @FXML
    private void addDiscount() {
        MainFX.getInstance().click();
        update();
        MainFX.getInstance().changeScene(AddDiscountPage.getScene());
    }

    @FXML
    private void editDiscount() {
        MainFX.getInstance().click();
        if (discountId.getText().isEmpty()) {
            setEditError("Discount id shouldn't be empty!!");
        } else if (editField.getText().isEmpty()) {
            setEditError("Edit field shouldn't be empty!!");
        } else if (editValue.getText().isEmpty()) {
            setEditError("Edit error shouldn't be empty!!");
        } else {
            ClientMessage request = new ClientMessage("edit discount code manager");
            HashMap<String, String> reqInfo = new HashMap<>();
            reqInfo.put("code", discountId.getText());
            reqInfo.put("field", editField.getText());
            reqInfo.put("new value", editValue.getText());
            request.setHashMap(reqInfo);
            processAnswer(send(request));
        }
    }

    private void processAnswer(ServerMessage answer) {
        if (answer.getType().equalsIgnoreCase("Error")) {
            setEditError(answer.getErrorMessage());
        } else {
            update();
        }
    }

    private void setEditError(String error) {
        editError.setText(error);
        editError.setVisible(false);
    }
}
