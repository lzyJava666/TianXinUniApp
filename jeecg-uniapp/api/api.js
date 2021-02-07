import { http } from '@/common/service/service.js'
import configService from '@/common/service/config.service.js';
const apiService = {

	 /**
	  * 登录
	  */
	login(params) {
		return http.post('/sys/mLogin',params)
	},
	/**
	  * 手机号码登录
	  */
	phoneNoLogin(params) {
		return http.post('/sys/phoneLogin',params);
	},
	/**
	  * 退出
	  */
	logout(params) {
		return http.post('/sys/logout',params);
	},
	/**
	 * 获取数据列表
	 */
	listUser(params){
		if(params.username==null){
			params.username="";
		}
		return http.get(`/test/testUser/list?pageNo=${params.pageNo}&pageSize=${params.pageSize}&username=${params.username}`);
	}
	,
	updateUser(data){
		console.log(data);
		return http.post('/test/testUser/edit',data);
	},
	saveUser(data){
		return http.post('/test/testUser/add',data);
	},
	/**
	 * 获取文件访问路径
	 * @param avatar
	 * @param subStr
	 * @returns {*}
	 */
	getFileAccessHttpUrl(avatar,subStr){
	    if(!subStr) subStr = 'http'
	    if(avatar && avatar.startsWith(subStr)){
	        return avatar;
	    }else{
	        return configService.staticDomainURL + "/" + avatar;
	    }
	}
};

export default apiService;
