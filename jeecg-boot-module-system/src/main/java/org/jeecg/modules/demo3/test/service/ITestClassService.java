package org.jeecg.modules.demo3.test.service;

import org.jeecg.modules.demo3.test.entity.TestStudent;
import org.jeecg.modules.demo3.test.entity.TestClass;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: test_class
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
public interface ITestClassService extends IService<TestClass> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TestClass testClass,List<TestStudent> testStudentList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestClass testClass,List<TestStudent> testStudentList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
