package com.model;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import com.sun.xml.internal.txw2.TxwException;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.*;
import java.util.*;

public class UserService {
    private String USERS;



    public UserService(String USERS) {
        this.USERS = USERS;//设置用户目录
    }

    public boolean isUserExisted(String username) {
        return isInvalidUsername(username);
    }

    public boolean isInvalidUsername(String username){//用户名称是否不合法
        for(String file:new File(USERS).list()) {
            if(file.equals(username)){
                return true;
            }
        }
        return false;
    }

    public void createUserData (String email,String username,String password)throws IOException{//创建用户目录与基本资料
        File userhome=new File((USERS+"/"+username));
        userhome.mkdir();
        BufferedWriter writer=new BufferedWriter(new FileWriter(userhome+"/profile"));
        writer.write(email+"\t"+password);
        writer.close();
    }

    public boolean checkLogin(String username,String password)throws IOException{
        if(username!=null&&password!=null){
            for(String file:new File(USERS).list()){
                if(file.equals(username)){
                    BufferedReader reader=new BufferedReader(new FileReader(USERS+"/"+file+"/profile"));
                    String passwd=reader.readLine().split("\t")[1];
                    if(passwd.equals(password)){
                        return true;
                    }
                }
            }
        }
        return  false;
    }
    //用以过滤.txt文件名
    private class TxtFilenameFilter implements FilenameFilter{
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

    private TxtFilenameFilter filenameFilter=new TxtFilenameFilter();

    //treeMap 排序用，希望日期最近的在上面显示
    private class DateComparator implements Comparator<Date>{
        @Override
        public int compare(Date o1, Date o2) {
            return -o1.compareTo(o2);
        }
    }

    private DateComparator comparator=new DateComparator();

    public List<Blah> getBlahs(Blah blah)throws IOException{
        File border =new File(USERS+"/"+blah.getUsername());
        String[] txts=border.list(filenameFilter);

        Map<Date,String> messages=new TreeMap<Date,String>(comparator);
        for(String txt:txts){
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(USERS+"/"+blah.getUsername()+"/"+txt),"UTF-8"));
            String text=null;
            StringBuilder builder=new StringBuilder();
            while((text=reader.readLine())!=null){
                builder.append(text);
            }
            Date date=new Date(Long.parseLong(txt.substring(0,txt.indexOf(".txt"))));//文件名为发送时间
            messages.put(date,builder.toString());
            reader.close();
        }

        List<Blah>blahs=new ArrayList<Blah>();

        for(Date date:messages.keySet()){
            String txt=messages.get(date);
            blahs.add(new Blah(blah.getUsername(),txt,date));
        }
        return blahs;
    }

    public void addBlah(Blah blah)throws IOException{
        String file=USERS+"/"+blah.getUsername()+"/"+new Date().getTime()+".txt";
        BufferedWriter writer=new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),"UTF-8"));
        writer.write(blah.getTxt());
        writer.close();
    }

    public void deleteBlah(Blah blah) {
        File file = new File(USERS + "/" + blah.getUsername() + "/" + blah.getDate().getTime() + ".txt");
        if(file.exists()) {
            file.delete();
        }
    }
}

