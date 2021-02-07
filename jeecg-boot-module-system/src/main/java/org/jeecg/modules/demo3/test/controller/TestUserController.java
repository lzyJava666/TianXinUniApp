package org.jeecg.modules.demo3.test.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.test.entity.TestUser;
import org.jeecg.modules.demo3.test.service.ITestUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: test_user
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Api(tags="test_user")
@RestController
@RequestMapping("/test/testUser")
@Slf4j
public class TestUserController extends JeecgController<TestUser, ITestUserService> {
	@Autowired
	private ITestUserService testUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "test_user-分页列表查询")
	@ApiOperation(value="test_user-分页列表查询", notes="test_user-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestUser testUser,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestUser> queryWrapper = QueryGenerator.initQueryWrapper(testUser, req.getParameterMap());
		Page<TestUser> page = new Page<TestUser>(pageNo, pageSize);
		IPage<TestUser> pageList = testUserService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testUser
	 * @return
	 */
	@AutoLog(value = "test_user-添加")
	@ApiOperation(value="test_user-添加", notes="test_user-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestUser testUser) {
		testUserService.save(testUser);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testUser
	 * @return
	 */
	@AutoLog(value = "test_user-编辑")
	@ApiOperation(value="test_user-编辑", notes="test_user-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestUser testUser) {
		testUserService.updateById(testUser);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_user-通过id删除")
	@ApiOperation(value="test_user-通过id删除", notes="test_user-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testUserService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "test_user-批量删除")
	@ApiOperation(value="test_user-批量删除", notes="test_user-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testUserService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_user-通过id查询")
	@ApiOperation(value="test_user-通过id查询", notes="test_user-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestUser testUser = testUserService.getById(id);
		if(testUser==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(testUser);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testUser
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestUser testUser) {
        return super.exportXls(request, testUser, TestUser.class, "test_user");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TestUser.class);
    }

}
