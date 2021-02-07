<template>
	<view>
		<van-nav-bar title="入库单" left-arrow @click-left="onClickLeft" right-text="新增" @click-right="onClickRight" />
		<van-search v-model="searchVal" placeholder="请搜索用户名" @blur="onSearch" style="margin-bottom: 5vh;" />
		<view id="listVIew">
			<van-row type="flex" justify="center" style="margin-bottom:1vh;display: block;">
				<van-col span="2" border class="tdClass">ID</van-col>
				<van-col span="5" class="tdClass">用户名</van-col>
				<van-col span="5" class="tdClass">密码</van-col>
				<van-col span="5" class="tdClass">注册日期</van-col>
				<van-col span="7" class="tdClass">操作</van-col>
			</van-row>
			<view v-for="(user,index) in users" :key="user.id">
				<van-row type="flex" gutter="12" justify="center" style="margin-bottom:1vh;display: block;">
					<checkbox-group>
						<van-col span="2"><span class="tdClass">{{user.id}}</span></van-col>
					</checkbox-group>
					<van-col span="5"><span class="tdClass">{{user.username}}</span></van-col>
					<van-col span="5"><span class="tdClass">{{user.password}}</span></van-col>
					<van-col span="5"><span class="tdClass">{{user.createTime}}</span></van-col>
					<van-col span="3"><span class="tdClass" @click="update(user)">
							<van-button round size="mini">修改</van-button>
						</span></van-col>
					<van-col span="3"><span class="tdClass">
							<van-button type="primary" round size="mini" style="margin-left: 2vw;" @click="show(user)">查看</van-button>
						</span></van-col>
				</van-row>
			</view>
		</view>
		<view style="width: 90vw;height: 10vh;margin-left: 5vw;margin-right: 5vw;position: fixed;bottom: 5vh;right: 0vw;">
			<van-button type="primary" @click="upPage">上一页</van-button>
			<text style="margin-left:7vw;font-size: 20px;">总记录数：<text style="font-size: 22px;font-weight: bold;">{{total}}</text>
			</text>
			<van-button type="primary" @click="nextPage" style="text-align: right;position: absolute;right: 0;">下一页</van-button>
		</view>
		<!-- <van-pagination v-model="currentPage" :total-items="24" :items-per-page="5" /> -->
	</view>
</template>

<script>
	import {
		mapActions
	} from "vuex"
	import CeProductForm from './modules/CeProductForm'
	import api from "@/api/api"
	export default {
		name: 'CeProductList',
		components: {

		},
		data() {
			return {
				//搜索框绑定值
				searchVal: "",
				//存放用户数据数组
				users: [],
				currentPage: 1,
				total: 0,
				//当前页码
				pageIndex: 1,
			}
		},
		components: {
			CeProductForm
		},
		created() {
			api.listUser({
				pageNo: 1,
				pageSize: 15
			}).then(res => {
				if (res.data.code == 200) {
					console.log(res.data);
					this.users = res.data.result.records;
					this.total = res.data.result.total;
				} else {

				}
			})
		},
		computed: {

		},
		methods: {
			//返回按钮--返回主页
			onClickLeft() {
				this.$router.push("/");
			},
			// 搜索框是去焦点时获取
			onSearch(val) {
				let username = val.detail.value;
				api.listUser({
					pageNo: 1,
					pageSize: 15,
					username: username
				}).then(res => {
					if (res.data.code == 200) {
						console.log(res.data);
						this.users = res.data.result.records;
						this.total = res.data.result.total;
					} else {

					}
				})
			},
			update(user) {
				console.log(user);
				this.$router.push({
					path: "/pages/cement/baseinfo/modules/CeProductForm",
					query: {
						user: encodeURIComponent(JSON.stringify(user)),
						type: 2
					}
				})
			},
			show(user) {
				this.$router.push({
					path: "/pages/cement/baseinfo/modules/CeProductForm",
					query: {
						user: encodeURIComponent(JSON.stringify(user)),
						type: 1
					}
				})
			},
			onClickRight() {

				this.$router.push({
					path: "/pages/cement/baseinfo/modules/CeProductForm",
					query: {
						type: 3
					}
				})
			},
			upPage() {
				let username = this.searchVal;

				this.pageIndex--;
				if (this.pageIndex == 0) {
					this.pageIndex = 1;
					return;
				}
				api.listUser({
					pageNo: this.pageIndex,
					pageSize: 15,
					username: username
				}).then(res => {
					if (res.data.code == 200) {
						console.log(res.data);
						this.users = res.data.result.records;
						this.total = res.data.result.total;
					} else {

					}
				})
			},
			nextPage() {
				let username = this.searchVal;
				this.pageIndex++;
				if (this.pageIndex > this.total / 15 + 1) {
					this.pageIndex--;
					return;
				}
				api.listUser({
					pageNo: this.pageIndex,
					pageSize: 15,
					username: username
				}).then(res => {
					if (res.data.code == 200) {
						console.log(res.data);
						this.users = res.data.result.records;
						this.total = res.data.result.total;
					} else {

					}
				})
			}
		}
	}
</script>
<style scoped>
	/* 列表样式 */
	#listVIew {
		background-color: #fff;
		width: auto;
	}

	/* 表内元素样式 */
	.tdClass {
		/* border: 1px solid #000000; */
		text-align: center;
		display: block;
	}
</style>
