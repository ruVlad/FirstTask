package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.dao.impl.InfoDAOImpl;
import sample.model.DBThread;
import sample.model.Model;

import java.io.File;
import java.io.IOException;


public class Controller {
    private Model model = new Model();
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private String path;

    @FXML
    TextField chooseTextField = new TextField();

    @FXML
    TextField unionTextField = new TextField();

    @FXML
    Label unionLabel = new Label();

    @FXML
    Label counterLabel = new Label();

    @FXML
    ProgressBar progressBar = new ProgressBar();

    @FXML
    Label successLabel = new Label();

    @FXML
    TextField urlTextField = new TextField();

    @FXML
    TextField userTextField = new TextField();

    @FXML
    TextField passwordTextField = new TextField();

    public void choose(){
        File dir = directoryChooser.showDialog(new Stage());
        if (dir != null) chooseTextField.setText(dir.getAbsolutePath());
        else {
            chooseTextField.setText(null);
        }
    }

    public void generation(){
        successLabel.setText("");
        path = chooseTextField.getText();
        model.generation(path);
        successLabel.setText("Files were successfully generated");
    }

    public void union() throws IOException {
        unionLabel.setText(model.union(unionTextField.getText(),path + "\\out.txt"));
    }

    public void save() throws  IOException{
        DBThread dbThread = new DBThread(path + "\\out.txt", new InfoDAOImpl(urlTextField.getText(),userTextField.getText(),passwordTextField.getText()));
        new Thread(dbThread).start();

        progressBar.progressProperty().bind(dbThread.progressProperty());
        counterLabel.textProperty().bind(dbThread.messageProperty());

    }

}
