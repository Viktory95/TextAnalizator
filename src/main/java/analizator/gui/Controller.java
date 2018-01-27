package analizator.gui;

import analizator.analizators.LineAnalizator;
import analizator.fileworkers.LocalFileWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {

    @FXML
    TextArea resultArea;

    @FXML
    TextField fileInputPath;

    @FXML
    TextField fileOutputPath;

    @FXML
    Button analyzeButton;

    @FXML
    Button pathInButton;

    @FXML
    Button pathOutButton;

    public void onKeyType(KeyEvent event) {

    }

    public void setPref() {

    }

    public void onRead() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        fileInputPath.setText(file.getPath());
    }

    public void onWrite() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open Directory");
        File file = chooser.showDialog(new Stage());
        fileOutputPath.setText(file.getPath());
    }

    public void onAnalize() {
        if (!"".equals(fileInputPath.getText()) || !"".equals(fileOutputPath.getText())) {

            LineAnalizator analizator = new LineAnalizator();
            try {
                Files.lines(Paths.get(fileInputPath.getText())).forEach(line -> analizator.parseWords(line));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            LocalFileWriter.writeAnalizeData(fileOutputPath.getText(), analizator.getWordsList(), analizator.calculateMaxWord());

            resultArea.setText(analizator.calculateMaxWord());
        } else {
            resultArea.setText("Выберите файл для анализа и путь к файлу с результатом.");
        }
    }


}