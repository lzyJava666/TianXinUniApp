<template>
	<view>
		<van-nav-bar :title="title" left-arrow @click-left="fromList" @click-right="toUpdate">
			<template #right>
			    <text v-show="type==1">修改</text>
			  </template>
		</van-nav-bar>
		<van-cell-group>
			<van-field :value="user.username" @input="setUsername" label="用户名" placeholder="请输入用户名" :disabled="type==1" />
			<van-field :value="user.password" @input="setPassword" label="密码" placeholder="请输入密码" :disabled="type==1" />
			<van-field :value="user.createTime" @input="setCreateTime" label="出生日期" placeholder="请输入出生日期" :disabled="type==1" />
			<br>
			<van-button round block type="info" native-type="submit" @click="submit()" v-show="type!=1">提交</van-button>
		</van-cell-group>
	</view>
</template>

<script>
	import api from '@/api/api'
	export default {
		name: 'CeProductForm',
		components: {

		},
		props: {

		},
		data() {
			return {
				value: '',
				user: {},
				title: "",
				// 1:查看 2:修改 3:新增
				type: this.$route.query.type,
				that: this
			}
		},
		computed: {

		},
		created() {
			switch (this.type) {
				case 1:
					this.title = "查看";
					this.user = JSON.parse(decodeURIComponent(this.$route.query.user));
					break;
				case 2:
					this.title = "修改";
					this.user = JSON.parse(decodeURIComponent(this.$route.query.user));
					break;
				default:
					this.title = "新增";
			}
		},
		methods: {
			fromList() {
				this.$router.push({path:"/pages/cement/baseinfo/CeProductList"});
			},
			toUpdate(){
				this.type=2;
				this.title = "修改";
			},
			setUsername(e){
				this.user.username=e.detail;
			},
			setPassword(e){
				this.user.password=e.detail;
			},
			setCreateTime(e){
				this.user.createTime=e.detail;
			},
			submit(){
				if(this.type==2){
					//修改

					api.updateUser(this.user)
						.then(res=>{
							if (res.data.code == 200) {
								uni.showToast({
								title: '提交成功',
								duration: 2000
								});
							} else {
								
							}
						})
				}else{
					//新增
					console.log(this.user);
					api.saveUser(this.user)
						.then(res=>{
							if (res.data.code == 200) {
								uni.showToast({
								title: '提交成功',
								duration: 2000
								});
							} else {
								
							}
						})
				}
			}
		}
	}
</script>
