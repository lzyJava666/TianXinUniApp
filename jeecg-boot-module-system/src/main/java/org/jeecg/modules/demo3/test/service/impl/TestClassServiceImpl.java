package org.jeecg.modules.demo3.test.service.impl;

import org.jeecg.modules.demo3.test.entity.TestClass;
import org.jeecg.modules.demo3.test.entity.TestStudent;
import org.jeecg.modules.demo3.test.mapper.TestStudentMapper;
import org.jeecg.modules.demo3.test.mapper.TestClassMapper;
import org.jeecg.modules.demo3.test.service.ITestClassService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: test_class
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Service
public class TestClassServiceImpl extends ServiceImpl<TestClassMapper, TestClass> implements ITestClassService {

	@Autowired
	private TestClassMapper testClassMapper;
	@Autowired
	private TestStudentMapper testStudentMapper;
	
	@Override
	@Transactional
	public void saveMain(TestClass testClass, List<TestStudent> testStudentList) {
		testClassMapper.insert(testClass);
		if(testStudentList!=null && testStudentList.size()>0) {
			for(TestStudent entity:testStudentList) {
				//外键设置
				entity.setCid(testClass.getId());
				testStudentMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(TestClass testClass,List<TestStudent> testStudentList) {
		testClassMapper.updateById(testClass);
		
		//1.先删除子表数据
		testStudentMapper.deleteByMainId(testClass.getId().toString());
		
		//2.子表数据重新插入
		if(testStudentList!=null && testStudentList.size()>0) {
			for(TestStudent entity:testStudentList) {
				//外键设置
				entity.setCid(testClass.getId());
				testStudentMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		testStudentMapper.deleteByMainId(id);
		testClassMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testStudentMapper.deleteByMainId(id.toString());
			testClassMapper.deleteById(id);
		}
	}
	
}
