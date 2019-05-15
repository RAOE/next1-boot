package ${package};
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ${modelPath}.${model};
import ${mapperPath}.${mapperName};


import ${servicePath}.${serviceName};
//author:xuyuanfeng
@Service
public class ${className} implements ${serviceName}
{
    @Autowired
    private ${mapperName} mapper;


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSubmit(${model} model) {
		mapper.insert(model);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(${model} model) {
		mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<${model}> queryAll() {
		return mapper.selectAll();
		}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(${model}  model) {
        mapper.delete(model);
	}





}