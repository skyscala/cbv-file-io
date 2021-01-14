/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.file.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author zlhso
 */
public class SimpleIO {
    
    
    
    private SimpleIO(){}
    
    
    public static void encodeFile(File src,File des)throws IOException{
       
        int offset=0;
        List<Byte> list=new ArrayList<>();
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(src))){
            byte[] bytes=new byte[10240];
            while((offset=bis.read(bytes, 0, bytes.length))!=-1){
                for(int i=0;i<offset;i++){
                    list.add(bytes[i]);
                }
            }
        }
        byte[] bytesRead=new byte[list.size()];
        for(int i=0;i<bytesRead.length;i++){
            bytesRead[i]=list.get(i);
        }
        
        byte[] decodedBytes=Base64.encodeBase64URLSafeString(bytesRead).getBytes();
        try(BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(des))){
            bos.write(decodedBytes);
        }     
    }
    
    public static void decodeFile(File src,File des)throws IOException{
       
        int offset=0;
        List<Byte> list=new ArrayList<>();
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(src))){
            byte[] bytes=new byte[10240];
            while((offset=bis.read(bytes, 0, bytes.length))!=-1){
                for(int i=0;i<offset;i++){
                    list.add(bytes[i]);
                }
            }
        }
        byte[] bytesRead=new byte[list.size()];
        for(int i=0;i<bytesRead.length;i++){
            bytesRead[i]=list.get(i);
        }
        byte[] decodedBytes=Base64.decodeBase64(bytesRead);
        try(BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(des))){
            bos.write(decodedBytes);
        }
        
    }
}
