package org.jeecg.modules.demo3.test.service;

import org.jeecg.modules.demo3.test.entity.TestStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: test_student
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
public interface ITestStudentService extends IService<TestStudent> {

	public List<TestStudent> selectByMainId(String mainId);
}
