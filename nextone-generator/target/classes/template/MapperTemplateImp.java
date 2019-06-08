package ${package};
import java.util.List;
import ${modelPath}.${model};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ${myMapperPath}.MyMapper;
//author:xuyuanfeng
public interface  ${className} extends MyMapper<${model}>
{

	

}