package juegotictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author PERSONAL
 */
public class TableroJuego {

    private StackPane pane;
    private InfoJuego infoJuego;
    private Casilla[][] casilla = new Casilla[3][3];

    private char playerTurn = 'X';
    private boolean isEndOfGame = false;

    // Constructor
    public TableroJuego(InfoJuego infoJuego) {
        this.infoJuego = infoJuego;
        pane = new StackPane();
        pane.setMinSize(ConstantesUI.APP_WIDTH, ConstantesUI.TABLERO_HEIGHT);
        pane.setTranslateX(ConstantesUI.APP_WIDTH / 2);
        pane.setTranslateY((ConstantesUI.TABLERO_HEIGHT / 2) + ConstantesUI.INFO_JUEGO_HEIGHT);

        addAllTiles();
    }

    // Método para añadir nuestras casillas al tablero
    private void addAllTiles() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Casilla casillas = new Casilla();
                casillas.getStackPane().setTranslateX((col * 100) - 100);
                casillas.getStackPane().setTranslateY((row * 100) - 100);
                pane.getChildren().add(casillas.getStackPane());
                casilla[row][col] = casillas;

            }
        }
    }

    // Método para iniciar un nuevo juego
    public void startNewGame() {
        isEndOfGame = false;
        playerTurn = 'X';

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                casilla[row][col].setValue("");
            }
        }
    }

    // Método para cambiar de turno
    public void changePlayerTurn() {
        if (playerTurn == 'X') {
            playerTurn = 'O';
        } else {
            playerTurn = 'X';
        }

        infoJuego.updateMessage("Turno para " + playerTurn);
    }

    // Método para obtener el turno
    public String getPlayerTurn() {
        return String.valueOf(playerTurn);
    }

    // Método getter para el obtener el lienzo del Tablero
    public StackPane getStackPane() {
        return pane;
    }

    // Método para comprobar ganador
    public void checkForWinner() {
        checkRowsForWinner();
        checkColsForWinner();
        checkTopLeftToBottomRightForWinner();
        checkTopRightToBottomLeftForWinner();
        checkForStalemate();
    }

    // Método para comprobar filas
    private void checkRowsForWinner() {
        for (int row = 0; row < 3; row++) {
            if (casilla[row][0].getValue().equals(casilla[row][1].getValue())
                    && casilla[row][0].getValue().equals(casilla[row][2].getValue())
                    && !casilla[row][0].getValue().isEmpty()) {
                String winner = casilla[row][0].getValue();
                endGame(winner);
                return;
            }
        }
    }

    // Método para comprobar columnas
    private void checkColsForWinner() {
        if (!isEndOfGame) {
            for (int col = 0; col < 3; col++) {
                if (casilla[0][col].getValue().equals(casilla[1][col].getValue())
                        && casilla[0][col].getValue().equals(casilla[2][col].getValue())
                        && !casilla[0][col].getValue().isEmpty()) {
                    String winner = casilla[0][col].getValue();
                    endGame(winner);
                    return;
                }
            }
        }
    }

    // Método para comprobar trasnversales de esquina superior izquierda a esquina inferior derecha
    private void checkTopLeftToBottomRightForWinner() {
        if (!isEndOfGame) {
            if (casilla[0][0].getValue().equals(casilla[1][1].getValue())
                    && casilla[0][0].getValue().equals(casilla[2][2].getValue())
                    && !casilla[0][0].getValue().isEmpty()) {
                String winner = casilla[0][0].getValue();
                endGame(winner);
                return;
            }
        }
    }

    // Método para comprobar trasnversales de esquina superior derecha a esquina inferior izquierda
    private void checkTopRightToBottomLeftForWinner() {
        if (!isEndOfGame) {
            if (casilla[0][2].getValue().equals(casilla[1][1].getValue())
                    && casilla[0][2].getValue().equals(casilla[2][0].getValue())
                    && !casilla[0][2].getValue().isEmpty()) {
                String winner = casilla[0][2].getValue();
                endGame(winner);
                return;
            }
        }
    }

    // Método para comprobar empate
    private void checkForStalemate() {
        if (!isEndOfGame) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (casilla[row][col].getValue().isEmpty()) {
                        return;
                    }
                }
            }

            isEndOfGame = true;
            infoJuego.updateMessage("Empate..");
            infoJuego.showStartButton();
        }
    }

    // Método para mostrar el ganador
    private void endGame(String winner) {
        isEndOfGame = true;
        infoJuego.updateMessage("Ha ganado: " + winner);
        infoJuego.showStartButton();
    }

    // Clase Casilla
    private class Casilla {

        private StackPane pane;
        private Label label;

        public Casilla() {
            pane = new StackPane();
            pane.setMinSize(100, 100);

            Rectangle border = new Rectangle();
            border.setWidth(100);
            border.setHeight(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if (label.getText().isEmpty() && !isEndOfGame) {
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }

        // Método para obtener el lienzo de Casilla
        public StackPane getStackPane() {
            return pane;
        }

        // Método para obtener el valor de la casilla (X u O)
        public String getValue() {
            return label.getText();
        }

        // Método para asignar un valor a una casilla
        public void setValue(String value) {
            label.setText(value);
        }
    }
}
