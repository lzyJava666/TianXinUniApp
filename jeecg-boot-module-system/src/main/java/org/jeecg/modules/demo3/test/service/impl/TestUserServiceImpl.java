package org.jeecg.modules.demo3.test.service.impl;

import org.jeecg.modules.demo3.test.entity.TestUser;
import org.jeecg.modules.demo3.test.mapper.TestUserMapper;
import org.jeecg.modules.demo3.test.service.ITestUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: test_user
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements ITestUserService {

}
