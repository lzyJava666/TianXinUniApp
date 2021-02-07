package org.jeecg.modules.demo3.test.vo;

import java.util.List;
import org.jeecg.modules.demo3.test.entity.TestClass;
import org.jeecg.modules.demo3.test.entity.TestStudent;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: test_class
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Data
@ApiModel(value="test_classPage对象", description="test_class")
public class TestClassPage {

	/**id*/
	@ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**br*/
	@Excel(name = "br", width = 15)
	@ApiModelProperty(value = "br")
	private java.lang.String br;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;

	@ExcelCollection(name="test_student")
	@ApiModelProperty(value = "test_student")
	private List<TestStudent> testStudentList;

}
