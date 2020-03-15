package com.nextone.pojo;
//author:xuyuanfeng

import java.util.Date;

public class Project
{

    private String teacher;
    private String localtion;
    private String name;
    private String descritpion;
    private Date createdTime;
    private boolean id;
    private boolean status;

     public String getTeacher()
     {
     return teacher; 
     }
     public void setTeacher(String teacher)
     {
      this.teacher=teacher;
      }
     public String getLocaltion()
     {
     return localtion; 
     }
     public void setLocaltion(String localtion)
     {
      this.localtion=localtion;
      }
     public String getName()
     {
     return name; 
     }
     public void setName(String name)
     {
      this.name=name;
      }
     public String getDescritpion()
     {
     return descritpion; 
     }
     public void setDescritpion(String descritpion)
     {
      this.descritpion=descritpion;
      }
     public Date getCreatedTime()
     {
     return createdTime; 
     }
     public void setCreatedTime(Date createdTime)
     {
      this.createdTime=createdTime;
      }
     public boolean getId()
     {
     return id; 
     }
     public void setId(boolean id)
     {
      this.id=id;
      }
     public boolean getStatus()
     {
     return status; 
     }
     public void setStatus(boolean status)
     {
      this.status=status;
      }



}