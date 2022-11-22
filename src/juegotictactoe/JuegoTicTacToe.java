package juegotictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author PERSONAL
 */
public class JuegoTicTacToe extends Application {

    private InfoJuego infoJuego;
    private TableroJuego tablero;

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, ConstantesUI.APP_WIDTH, ConstantesUI.APP_HEIGHT);
            initLayout(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Método para inicializar los lienzos o interfaces gráficas
     * @param root recibe el lienzo principal - root
     * @return void
     */
    private void initLayout(BorderPane root) {
        initInfoJuego(root);
        initTablero(root);
    }

    /**
     * Método para inicializar la interfaz gráfica de la información del juego
     * @param root recibe el lienzo principal - root
     * @return void
     */
    private void initInfoJuego(BorderPane root) {
        infoJuego = new InfoJuego();
        infoJuego.setStartButtonOnAction((startNewGame()));
        root.getChildren().add(infoJuego.getStackPane());
    }

    /**
     * Método para inicializar un nuevo juego
     * @return Evento que inicializa un nuevo juego
     */
    private EventHandler<ActionEvent> startNewGame() {
        return (ActionEvent e) -> {
            infoJuego.hideStartButton();
            infoJuego.updateMessage("Turno para X");
            tablero.startNewGame();
        };
    }

    /**
     * Método para inicializar la interfaz gráfica del tablero
     * @param root recibe el lienzo principal - root
     * @return void
     */
    private void initTablero(BorderPane root) {
        tablero = new TableroJuego(infoJuego);
        root.getChildren().add(tablero.getStackPane());
    }

}
