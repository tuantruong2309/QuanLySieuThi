package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MINH TUAN
 */
public class KiemTraLoi {
      public boolean KT_SDT(String str)
      {
          if(str.matches("0[0-9]{9}"))
          {
              return true;
          }
          return false;
      }
      public boolean KT_SoLuong(String str)
      {
          if(str.matches("[0-9]{1,}"))
          {
              return true;
          }
          return false;
      }
      public boolean KT_ThoiGian(String tu, String den)
    {
        
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String check = f.format(today);
        
        Date kt;
          try {
              kt = f.parse(check);
              Date from = f.parse(tu);
              Date to = f.parse(den);
              if(!from.after(kt)&&!to.before(kt))
        {
            return true;
        }
        else
        {
            return false;
        }
          } catch (ParseException ex) {
              Logger.getLogger(KiemTraLoi.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;
        
    }
      public static void main(String[] args) {
          KiemTraLoi kt = new KiemTraLoi();
         
          System.out.println(kt.KT_SDT("09340457000"));
    }
}
