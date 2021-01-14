/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.file.io;

import java.io.File;

/**
 *
 * @author zlhso
 */
public class Runner {
    
    
    public static void main(String... args){
        try{
            
            //FileMgr.decodeFile(new File("temp/encdes/src"), new File("temp/decdes"), "txt");
            //FileMgr.decodeFile(new File("temp/encdes/src"), new File("temp/decdes"), "txt");
            
            SimpleIO.encodeFile(new File("temp/CmdAgent.java"), new File("temp/CmdAgent.enc"));
            SimpleIO.decodeFile(new File("temp/CmdAgent.enc"), new File("temp/CmdAgent.dec"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
