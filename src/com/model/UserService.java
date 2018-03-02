package com.model;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import com.sun.xml.internal.txw2.TxwException;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.*;
import java.util.*;

public class UserService {
   private AccountDAO accountDAO;
   private  BlahDAO blahDAO;
    private LinkedList<Blah> newest=new LinkedList<Blah>();


    public UserService(AccountDAO userDAO, BlahDAO blahDAO) {
        this.accountDAO = userDAO;
        this.blahDAO = blahDAO;
    }

    public UserService(String USERS, AccountDAO userDAO, BlahDAO blahDAO) {
        this(userDAO, blahDAO);
    }

    public void add(Account account){
        accountDAO.addAccount(account);
    }


    public boolean isUserExisted(Account account) {
        return accountDAO.isUserExisted(account);
    }



    public boolean checkLogin(Account account)throws IOException{
       if(account.getName()!=null&&account.getPassword()!=null){
           Account storedAcct=accountDAO.getAccount(account);//从数据库读取正确的account
           if(storedAcct!=null&&storedAcct.getPassword().equals(account.getPassword())){
               return true;
           }
       }
       return false;
    }


    //treeMap 排序用，希望日期最近的在上面显示
    private class DateComparator implements Comparator<Blah>{
        @Override
        public int compare(Blah o1, Blah o2) {
            return -o1.getDate().compareTo(o2.getDate());
        }
    }

    private DateComparator comparator=new DateComparator();

    public List<Blah> getBlahs(Blah blah)throws IOException{
        List<Blah> blahs=blahDAO.getBlah(blah);
        Collections.sort(blahs,comparator);
        return blahs;
    }


    public void addBlah(Blah blah)throws IOException{
        blahDAO.addBlah(blah);
        newest.add(blah);
        if(newest.size()>20){
            newest.removeLast();
        }
    }

    public void deleteBlah(Blah blah) {
        blahDAO.deleteBlah(blah);
        //newest.remove(blah);
    }

    public LinkedList<Blah> getNewest() {
        return newest;
    }
}

