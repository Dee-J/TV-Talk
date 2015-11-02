package app.tv_talk;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private String fileName;
    private String path;
    private File file;

    BufferedReader reader;
    BufferedWriter writer;

    FileIO()
    {
        file = null;
        fileName = Environment.getExternalStorageDirectory()+"/test/";
        path = Environment.getExternalStorageDirectory()+"/test/";
    }
    FileIO(String fileName)
    {
        this.fileName = Environment.getExternalStorageDirectory()+"/test/"+fileName;
        path = Environment.getExternalStorageDirectory()+"/test/";
        file = null;
    }

    public ArrayList<String> ReadFile()
    {
        ArrayList<String> result = new ArrayList<String>();
        if(this.fileName == null)
            return result;

        result.clear();
        try{
            reader = new BufferedReader((new FileReader(file)));
            String s;
            try {
                while ((s = reader.readLine()) != null) {
                    result.add(s);
                }
                reader.close();
            }
            catch (IOException e) {
                return result;
            }
        }
        catch (FileNotFoundException e) {
            CreateFile();
        }
        return result;
    }

    public boolean CreateFile(){
        boolean result = true;
        File dir = new File(this.path);
        if(!dir.exists())
            dir.mkdir();

        file = new File(this.fileName);
        if(file == null)
            result = false;
        else if(file.exists())
            result = false;
        else {
            try {
                file.createNewFile();
                result = true;
            } catch (IOException e) {
                result = false;
            }
        }
        return result;
    }
    public boolean WriteFile(String str)
    {
        boolean result = true;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(str);
            writer.newLine();
            writer.close();
        }
        catch (IOException e){
            result = false;
        }
        return result;
    }

    public String getName()
    {
        return this.fileName;
    }
}
