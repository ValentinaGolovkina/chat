package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    public TextArea messagesTA;
    @FXML
    public TextField messageTF;

    public void onClickBthSend(ActionEvent actionEvent) {
        sendMessage();
    }

    public void onPressedEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    private void sendMessage(){
        String message = messageTF.getText();
        if(!message.equals("")){
            messagesTA.appendText(messageTF.getText() +"\n");
            messageTF.clear();
        }
    }
}