package tinkerton.gui;

import tinkerton.core.Tinkerton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tinkerton tinkerton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/DaTinkerton.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Tinkerton", dukeImage),
                DialogBox.getDukeDialog("What can I do for you?", dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tinkerton instance */
    public void setTinkerton(Tinkerton t) {
        tinkerton = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tinkerton.getResponse(input);
        if (response == "Bye. Hope to see you again soon!") {
            handleExit();
            return;
        }
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        String[] parts = response.split("<SPLIT>");
        for (String part : parts) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(part.trim(), dukeImage));
        }
        userInput.clear();
    }

    private void handleExit() {
        Platform.exit();
    }
}
