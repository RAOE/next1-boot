package ${package};
import java.util.List;
import ${modelPath}.${modelName};

//author:xuyuanfeng
public interface ${className}
{
  
   public void addSubmit(${modelName} modelName);
   public void update(${modelName} modelName);
   public List<${modelName}> queryAll();
   public void delete(${modelName} id);

}