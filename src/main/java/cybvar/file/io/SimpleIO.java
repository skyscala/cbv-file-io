/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.file.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author zlhso
 */
public class SimpleIO {
    
    
    
    private SimpleIO(){}
    
    
    public static void encodeFile(File src,File des)throws IOException{
        byte[] bytes;
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(src))){
            bytes=bis.readAllBytes();
        }
        byte[] decodedBytes=Base64.encodeBase64URLSafeString(bytes).getBytes();
        try(BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(des))){
            bos.write(decodedBytes);
        }     
    }
    
    public static void decodeFile(File src,File des)throws IOException{
       
        byte[] bytes;
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(src))){
            bytes=bis.readAllBytes();
        }
        
        byte[] decodedBytes=Base64.decodeBase64(bytes);
        try(BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(des))){
            bos.write(decodedBytes);
        }
        
    }
}
