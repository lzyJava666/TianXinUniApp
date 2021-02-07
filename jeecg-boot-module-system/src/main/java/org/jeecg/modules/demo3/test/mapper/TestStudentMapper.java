package org.jeecg.modules.demo3.test.mapper;

import java.util.List;
import org.jeecg.modules.demo3.test.entity.TestStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: test_student
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
public interface TestStudentMapper extends BaseMapper<TestStudent> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TestStudent> selectByMainId(@Param("mainId") String mainId);
}
