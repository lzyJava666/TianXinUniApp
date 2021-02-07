package org.jeecg.modules.demo3.test.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: test_student
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@ApiModel(value="test_class对象", description="test_class")
@Data
@TableName("test_student")
public class TestStudent implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**名称*/
	@Excel(name = "名称", width = 15)
	@ApiModelProperty(value = "名称")
	private java.lang.String name;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**cid*/
	@ApiModelProperty(value = "cid")
	private java.lang.Integer cid;
}
