package sample.model;

import javafx.concurrent.Task;
import sample.dao.impl.InfoDAOImpl;
import sample.entity.Info;

import javax.sound.midi.MidiDevice;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DBThread extends Task {

    private Path path;
    private InfoDAOImpl infoDAOImpl;

    public DBThread(String path,InfoDAOImpl infoDAOImpl){
        this.path = Paths.get(path);
        this.infoDAOImpl = infoDAOImpl;
    }

//    Поток, котрый парсит файл и записывает данные в БД
//  и параллельно передает информацию о количестве уже записаных строк
    public Void call() {
        Info info = new Info();

        int startIndex;
        int counter = 0;


        try{
            List<String> lines = Files.readAllLines(path);
            for (String line : lines){
                startIndex = 0;
                info.setDate(line.substring(startIndex,line.indexOf( "||")));
                startIndex = line.indexOf("||") + 2;
                info.setSymbolEng(line.substring(startIndex,line.indexOf("||",startIndex)));
                startIndex = line.indexOf("||",startIndex) + 2;
                info.setSymbolRus(line.substring(startIndex,line.indexOf("||",startIndex)));
                startIndex = line.indexOf("||",startIndex) + 2;
                info.setNumber(Integer.parseInt(line.substring(startIndex,line.indexOf("||",startIndex))));
                startIndex = line.indexOf("||",startIndex) + 2;
                info.setFractional(Double.parseDouble(line.substring(startIndex)));
                infoDAOImpl.insert(info);
                counter++;
                updateProgress(counter,lines.size());
                updateMessage(counter + "/" + lines.size());
            }
        }
        catch (IOException ex){
            System.out.println(ex.toString());
        }

        return null;

    }
}
