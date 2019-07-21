package ${package};
//author:xuyuanfeng
import  java.util.Date;
public class ${className}
{

   <#list columnMap?keys as key>
    private ${columnMap["${key}"]} ${key};
   </#list>

  <#list columnMap?keys as key>
     public ${columnMap["${key}"]} get${key?cap_first}()
     {
     return ${key}; 
     }
     public void set${key?cap_first}(${columnMap["${key}"]} ${key})
     {
      this.${key}=${key};
      }
  </#list>



}