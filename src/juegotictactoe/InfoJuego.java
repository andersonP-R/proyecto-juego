package juegotictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author PERSONAL
 */
public class InfoJuego {

    private StackPane pane;
    private Label message;
    private Button startGameButton;

    public InfoJuego() {
        pane = new StackPane();
        pane.setMinSize(ConstantesUI.APP_WIDTH, ConstantesUI.INFO_JUEGO_HEIGHT);
        pane.setTranslateX(ConstantesUI.APP_WIDTH / 2);
        pane.setTranslateY(ConstantesUI.INFO_JUEGO_HEIGHT / 2);

        message = new Label("Tic-Tac-Toe Math");
        message.setMinSize(ConstantesUI.APP_WIDTH, ConstantesUI.INFO_JUEGO_HEIGHT);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        pane.getChildren().add(message);

        startGameButton = new Button("Nuevo Juego");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(20);
        pane.getChildren().add(startGameButton);
    }

    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String message) {
        this.message.setText(message);
    }

    public void showStartButton() {
        startGameButton.setVisible(true);
    }

    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    public void setStartButtonOnAction(EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }
}
