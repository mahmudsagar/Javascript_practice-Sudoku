package sudoku.persistence;

import sudoku.computaonLogic.GameLogic;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;

import java.io.*;

public class localStorageImpl implements IStorage {

    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gamedata.txt"
    );

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.close();
        }catch (IOException e){
            throw new IOException("unable to access Game Data");
        }
    }

    @Override
    public SudokuGame getGameDAta() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try{
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        }catch (ClassNotFoundException e){
            throw new IOException("File not found");
        }
    }
}
