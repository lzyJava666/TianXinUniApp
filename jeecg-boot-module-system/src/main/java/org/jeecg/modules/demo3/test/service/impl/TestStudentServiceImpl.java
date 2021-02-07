package org.jeecg.modules.demo3.test.service.impl;

import org.jeecg.modules.demo3.test.entity.TestStudent;
import org.jeecg.modules.demo3.test.mapper.TestStudentMapper;
import org.jeecg.modules.demo3.test.service.ITestStudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: test_student
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Service
public class TestStudentServiceImpl extends ServiceImpl<TestStudentMapper, TestStudent> implements ITestStudentService {
	
	@Autowired
	private TestStudentMapper testStudentMapper;
	
	@Override
	public List<TestStudent> selectByMainId(String mainId) {
		return testStudentMapper.selectByMainId(mainId);
	}
}
