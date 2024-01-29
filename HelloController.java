package com.example.melphrasuggui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class HelloController {
    @FXML
    private ListView<String> phraseListView;

    @FXML
    private TextField currentPhraseTextField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button playButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;

    public void initialize() {
        phraseListView.setItems(HelloApplication.getPhrasesFromFile());
        phraseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if (!phraseListView.getItems().isEmpty()) {
            messageLabel.setText("Phrases loaded from file");
            phraseListView.getSelectionModel().selectFirst();
            currentPhraseTextField.setText(phraseListView.getSelectionModel().getSelectedItem());
        } else {
            messageLabel.setText("File is empty");
            currentPhraseTextField.setText("");
        }
    }

    @FXML
    protected void onListViewClick() {
        currentPhraseTextField.setText(phraseListView.getSelectionModel().getSelectedItem());
        playButton.setDisable(false);
        saveButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    @FXML
    protected void onTextFieldAction() {
        if (currentPhraseTextField.getText().isEmpty()) {
            playButton.setDisable(true);
            saveButton.setDisable(true);
            deleteButton.setDisable(true);
        } else {
            playButton.setDisable(false);
            saveButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @FXML
    protected void onGenerateButtonClick() {
        messageLabel.setText("Generating new melodic phrase...");
        int numOfMeasures = 2;
        if (currentPhraseTextField.getText().equals("4") || currentPhraseTextField.getText().toLowerCase().equals("four")) {
            numOfMeasures = 4;
        }
        currentPhraseTextField.setText("");
        currentPhraseTextField.setText(HelloApplication.generatePhrase(numOfMeasures));
        playButton.setDisable(false);
        saveButton.setDisable(false);
        deleteButton.setDisable(false);
        messageLabel.setText("New phrase generated!");
    }

    @FXML
    protected void onPlayButtonClick() {
        messageLabel.setText("Playing currently selected phrase...");
        HelloApplication.playPhrase(currentPhraseTextField.getText());
    }

    @FXML
    protected void onSaveButtonClick() {
        messageLabel.setText("Saving currently selected phrase...");
        HelloApplication.addPhrase(currentPhraseTextField.getText());
        messageLabel.setText("Phrase saved!");
    }

    @FXML
    protected void onDeleteButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete melodic phrase");
        alert.setHeaderText("Delete phrase: " + currentPhraseTextField.getText());
        alert.setContentText("Are you sure? Press OK to confirm, or Cancel to back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (HelloApplication.deletePhrase(currentPhraseTextField.getText())) {
                messageLabel.setText("Phrase " + currentPhraseTextField.getText() + " deleted!");
            } else {
                messageLabel.setText("Phrase " + currentPhraseTextField.getText() + " not found!");
            }
            currentPhraseTextField.clear();
            playButton.setDisable(true);
            saveButton.setDisable(true);
            deleteButton.setDisable(true);
        } else {
            messageLabel.setText("Phrase NOT deleted");
        }
    }

    @FXML
    protected void onQuitButtonClick() {
        HelloApplication.writeListToFile();
        messageLabel.setText("Phrase list saved to file");
        Platform.exit();
    }
}