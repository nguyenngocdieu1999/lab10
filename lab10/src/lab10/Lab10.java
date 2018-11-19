/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lab10 {
    
    private List<String> allFunctions = new ArrayList();
    List<String> getAllFunctions(File path){
        BufferedReader br = null;
        FileReader fr = null;
        String nameFunc  = "";
        int bracketStart = 0, bracketEnd = 0;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String input = null;
            boolean run = true;
            while ((input = br.readLine()) != null) {
                if((input.contains("/**"))){
                    while (input.endsWith("*/")){
                        input = br.readLine();
                    }
                }
                else if((input.contains("/*"))){
                    while (input.endsWith("*/")){
                        input = br.readLine();
                    }
                }
                else if((input.contains("//"))){
                    input = br.readLine();
                }
                else if((input.contains("public static"))){
                    run = true;
                    nameFunc = input;

                    while (run){
                        input = br.readLine();
                        if((input.indexOf("{")) != -1)
                            bracketStart++;

                        if((input.indexOf("}")) != -1)
                            bracketEnd++;

                        nameFunc = nameFunc + "\n" + input;

                        if(bracketStart == bracketEnd){
                            bracketStart = 0;
                            bracketEnd = 0;
                            allFunctions.add(nameFunc);
                            nameFunc = "";
                            run = false;
                        }
                    }
                }
                nameFunc = nameFunc + "\n" + input;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFunctions;
    }

    
    public String findFunctionByName(String name){
        File file = new File("Utils.java");
        List<String> listFunctions = getAllFunctions(file);
        
        String output=null;
        String input = name.substring(0, name.indexOf("("));
        
        for(String str : listFunctions){
            if((str.contains(input)) ){
                output=str;
            }
        }
                 return output;
        }
        
}

    

