package pairmatching.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MdFileReader {
    public List<String> readMdFile(String fileName) {
        List<String> list = new ArrayList<String>();
        try{
            FileReader file = new FileReader(fileName);
            BufferedReader br = new BufferedReader(file);
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }
                // 입력 값 검증 추가 예정

                list.add(line);
            }
            br.close();

        }catch(IOException e){
            System.out.println("물품 목록을 불러올 수 없습니다.");
            e.printStackTrace();
        }
        return list;
    }
}
