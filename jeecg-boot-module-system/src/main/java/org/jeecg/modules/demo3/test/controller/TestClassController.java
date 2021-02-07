package org.jeecg.modules.demo3.test.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.test.entity.TestStudent;
import org.jeecg.modules.demo3.test.entity.TestClass;
import org.jeecg.modules.demo3.test.vo.TestClassPage;
import org.jeecg.modules.demo3.test.service.ITestClassService;
import org.jeecg.modules.demo3.test.service.ITestStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: test_class
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Api(tags="test_class")
@RestController
@RequestMapping("/test/testClass")
@Slf4j
public class TestClassController {
	@Autowired
	private ITestClassService testClassService;
	@Autowired
	private ITestStudentService testStudentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testClass
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "test_class-分页列表查询")
	@ApiOperation(value="test_class-分页列表查询", notes="test_class-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestClass testClass,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestClass> queryWrapper = QueryGenerator.initQueryWrapper(testClass, req.getParameterMap());
		Page<TestClass> page = new Page<TestClass>(pageNo, pageSize);
		IPage<TestClass> pageList = testClassService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testClassPage
	 * @return
	 */
	@AutoLog(value = "test_class-添加")
	@ApiOperation(value="test_class-添加", notes="test_class-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestClassPage testClassPage) {
		TestClass testClass = new TestClass();
		BeanUtils.copyProperties(testClassPage, testClass);
		testClassService.saveMain(testClass, testClassPage.getTestStudentList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testClassPage
	 * @return
	 */
	@AutoLog(value = "test_class-编辑")
	@ApiOperation(value="test_class-编辑", notes="test_class-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestClassPage testClassPage) {
		TestClass testClass = new TestClass();
		BeanUtils.copyProperties(testClassPage, testClass);
		TestClass testClassEntity = testClassService.getById(testClass.getId());
		if(testClassEntity==null) {
			return Result.error("未找到对应数据");
		}
		testClassService.updateMain(testClass, testClassPage.getTestStudentList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_class-通过id删除")
	@ApiOperation(value="test_class-通过id删除", notes="test_class-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testClassService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "test_class-批量删除")
	@ApiOperation(value="test_class-批量删除", notes="test_class-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testClassService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_class-通过id查询")
	@ApiOperation(value="test_class-通过id查询", notes="test_class-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestClass testClass = testClassService.getById(id);
		if(testClass==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(testClass);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_student通过主表ID查询")
	@ApiOperation(value="test_student主表ID查询", notes="test_student-通主表ID查询")
	@GetMapping(value = "/queryTestStudentByMainId")
	public Result<?> queryTestStudentListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestStudent> testStudentList = testStudentService.selectByMainId(id);
		return Result.OK(testStudentList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testClass
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestClass testClass) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TestClass> queryWrapper = QueryGenerator.initQueryWrapper(testClass, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TestClass> queryList = testClassService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TestClass> testClassList = new ArrayList<TestClass>();
      if(oConvertUtils.isEmpty(selections)) {
          testClassList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          testClassList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TestClassPage> pageList = new ArrayList<TestClassPage>();
      for (TestClass main : testClassList) {
          TestClassPage vo = new TestClassPage();
          BeanUtils.copyProperties(main, vo);
          List<TestStudent> testStudentList = testStudentService.selectByMainId(main.getId().toString());
          vo.setTestStudentList(testStudentList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "test_class列表");
      mv.addObject(NormalExcelConstants.CLASS, TestClassPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("test_class数据", "导出人:"+sysUser.getRealname(), "test_class"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<TestClassPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestClassPage.class, params);
              for (TestClassPage page : list) {
                  TestClass po = new TestClass();
                  BeanUtils.copyProperties(page, po);
                  testClassService.saveMain(po, page.getTestStudentList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
