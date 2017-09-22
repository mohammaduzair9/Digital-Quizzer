/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author UZAIR
 */
public class quizFileStream {

    public void writeToFile(List<Quiz> list, String file,int newFile) {
        ObjectOutputStream outStream = null;
        try {
            File myFile = new File(file);
            if(myFile.exists() && newFile==0){
                outStream = new ObjectOutputStream(new FileOutputStream(file, true)) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }
            else{
                outStream = new ObjectOutputStream(new FileOutputStream(file));
            }
            for (Quiz q : list) {
                outStream.writeObject(q);
            }
        } 
        catch (IOException ioException) {
            System.err.println("Error opening file.");
        } 
        catch (NoSuchElementException noSuchElementException) {
            System.err.println("Invalid input.");
        } 
        finally {
            try {
                if (outStream != null)
                    outStream.close();
            } 
            catch (IOException ioException) {
                System.err.println("Error closing file.");
            }
        }
    }

    public List<Quiz> readFromFile(String file) {
        List<Quiz> list = new ArrayList<>();
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Quiz q = (Quiz) inputStream.readObject();
                list.add(q);
            }
        } 
        catch (EOFException eofException) {
            return list;
        } 
        catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Object creation failed.");
        } 
        catch (IOException ioException) {
            System.err.println("Error opening file.");
        } 
        finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                } 
            catch (IOException ioException) {
                System.err.println("Error closing file.");
            }
        }
        return list;
    }

  

    
}
