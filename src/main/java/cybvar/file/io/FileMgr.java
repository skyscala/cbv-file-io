/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.file.io;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author zlhso
 */
public class FileMgr {
    
    
    public static void encodeFile(File srcFile,File desFolder,String ext)throws IOException{    
        if(!desFolder.exists()){
            desFolder.mkdirs();
        }
        if(srcFile.isFile()){
            String desPath=desFolder.getAbsolutePath()+"/"+srcFile.getName()+"."+ext;
            SimpleIO.encodeFile(srcFile, new File(desPath));
        }else if(srcFile.isDirectory()){
            for(File file:srcFile.listFiles()){
                String desFolderPath=desFolder.getAbsolutePath()+"/"+srcFile.getName();
                encodeFile(file,new File(desFolderPath),ext);
            }
        }
    }
    
    public static void decodeFile(File srcFile,File desFolder,String ext)throws IOException{    
        if(!desFolder.exists()){
            desFolder.mkdirs();
        }
        if(srcFile.isFile()){
            String desPath=desFolder.getAbsolutePath()+"/"+srcFile.getName().replace("."+ext, "");
            SimpleIO.decodeFile(srcFile, new File(desPath));
        }else if(srcFile.isDirectory()){
            for(File file:srcFile.listFiles()){
                String desFolderPath=desFolder.getAbsolutePath()+"/"+srcFile.getName();
                decodeFile(file,new File(desFolderPath),ext);
            }
        }
    }
}
