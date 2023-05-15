package Redditclone.Practice.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FileUtil {
    public static  byte[] compressBytes(byte[] data){
        Deflater deflater=new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] buffer=new byte[4*1024];
        while (!deflater.finished()){
            int count=deflater.deflate(buffer);
            outputStream.write(buffer,0,count);

        }
        try{
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data){
        Inflater inflater=new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] buffer=new byte[4*1024];

        try{
            while (!inflater.finished()){
                int count=inflater.inflate(buffer);
                outputStream.write(buffer,0,count);

            }
            outputStream.close();
            System.out.println("working");
        }
        catch (IOException exception){}
        catch (DataFormatException e) {

        }
        System.out.println("completed");
        return outputStream.toByteArray();

    }
}
