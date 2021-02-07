<template name="home">
	<view>
		<scroll-view>
			<!-- 轮播 -->
			<swiper class="screen-swiper square-dot"  :indicator-dots="true" :circular="true"
			 :autoplay="true" interval="5000" duration="500" :style="[{animation: 'show 0.2s 1'}]">
				<swiper-item v-for="(item,index) in swiperList" :key="index">
					<image :src="item.url" mode="aspectFill" v-if="item.type=='image'"></image>
					<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false" objectFit="cover" v-if="item.type=='video'"></video>
				</swiper-item>
			</swiper>
			<!-- 中部应用宫格 -->
			<view class="bg-white"  :style="[{animation: 'show 0.4s 1'}]">
				<view class="grid margin-bottom col-2 ">
				  <navigator  v-for="(item,index) in middleApps" :key="index" :url="'/pages/home/' + item.name" class="nav-li" navigateTo
					 :style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]" hover-class="none">
						<view class="flex align-center">
							<image :src="'/static/home/'+item.icon"  mode="aspectFill" class="line2-icon"></image>
							<view class="text-df">{{item.title}} <br/> <span class="text-light">{{item.text}}</span></view>
						</view>
					</navigator>
				</view>
			</view>
			<!-- 常用服务 -->
			<view class="cu-bar bg-white solid-bottom"   :style="[{animation: 'show 0.6s 1'}]">
				<view class="action">
					<text class='cuIcon-title text-blue'></text>水泥基础维护
				</view>
			</view>
			<view class=" bg-white grid col-3 padding-sm">
				<view class="padding-sm animation-slide-bottom" :key="0" @tap="goPage('CeProductList')">
					<view class="padding radius text-center shadow-blur solid-right ">
						<svg t="1611566056734" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1544"
							 width="48" height="48"><path d="M922.406 959.133H106.169c-20.959 0-37.949-16.99-37.949-37.949V104.948c0-20.959 16.99-37.949 37.949-37.949h816.236c20.959 0 37.949 16.99 37.949 37.949v816.236c0.001 20.959-16.99 37.949-37.948 37.949z" fill="#03A7FE" p-id="1545"></path><path d="M510.559 835.163c-89.37 0-173.391-34.803-236.584-97.997-63.194-63.194-97.997-147.215-97.997-236.584h15.28c0 176.063 143.238 319.301 319.301 319.301S829.86 676.645 829.86 500.582 686.621 181.281 510.559 181.281c-72.793 0-141.348 23.855-198.252 68.986l-9.495-11.972c28.938-22.951 61.183-40.823 95.841-53.12C434.512 172.452 472.163 166 510.559 166c89.37 0 173.391 34.802 236.584 97.997 63.194 63.194 97.997 147.214 97.997 236.584s-34.803 173.39-97.997 236.584c-63.194 63.195-147.214 97.998-236.584 97.998z" fill="#FFFFFF" p-id="1546"></path><path d="M750.173 702.236H327.165v-15.281h360.514l-53.327-27.735 7.05-13.557zM720.763 757.571H285.84l117.687-47.312 5.699 14.178-44.411 17.853h355.948z" fill="#FFFFFF" p-id="1547"></path><path d="M520.811 667.64l-192.084-69.169V373.203l192.084 57.641z" fill="#FFFFFF" p-id="1548"></path><path d="M523.284 671.159l-197.031-70.95V369.878l197.031 59.125v242.156z m-192.083-74.427l187.136 67.388V432.684l-187.136-56.157v220.205z" fill="#03A7FE" p-id="1549"></path><path d="M522.521 667.64l192.083-69.169V373.203l-192.083 57.641z" fill="#FFFFFF" p-id="1550"></path><path d="M520.047 671.159V429.003l197.031-59.125v230.331l-197.031 70.95z m4.947-238.475V664.12l187.137-67.388V376.527l-187.137 56.157z" fill="#03A7FE" p-id="1551"></path><path d="M328.727 373.203l198.195-74.987 187.682 74.987-192.083 57.641z" fill="#FFFFFF" p-id="1552"></path><path d="M522.523 433.425l-201.529-59.942 205.952-77.921 195.157 77.973-199.58 59.89z m-186.064-60.503l186.058 55.34 184.588-55.392-180.208-72-190.438 72.052z" fill="#03A7FE" p-id="1553"></path></svg>
						<view class="text-lg margin-top">产品信息</view>
					</view>
				</view>
			</view>

			<!-- 常用服务 -->
			<view class="cu-bar bg-white solid-bottom"   :style="[{animation: 'show 0.6s 1'}]">
				<view class="action">
					<text class='cuIcon-title text-blue'></text>常用服务
				</view>
			</view>
			<view class=" bg-white grid col-3 padding-sm">
				<view class="padding-sm animation-slide-bottom" :style="[{animationDelay: (index + 1)*0.1 + 's'}]" v-for="(item,index) in usList" :key="index" @tap="goPage(item.page)">
					<view class="padding radius text-center shadow-blur solid-right ">
<!--						 <image :src="item.icon"  mode="aspectFill" class="line2-icon"></image>-->
						<view class="cu-avatar lg "
						 :style="{background: 'url(' + item.icon + ') no-repeat',backgroundSize:'100upx 100upx'}">
						   <view class="cu-tag badge" v-if="getTtemDotInfo(item)">{{getTtemDotInfo(item)}}</view>
						</view>
						<view class="text-lg margin-top">{{item.title}}</view>
					</view>
				</view>
			</view>

			<!-- 其他服务 -->
			<view class="cu-bar bg-white solid-bottom margin-top"  :style="[{animation: 'show 0.6s 1'}]">
				<view class="action">
					<text class='cuIcon-title text-yellow'></text>其他服务
				</view>
			</view>
			<view class=" bg-white grid col-3 padding-sm">
				<view class="padding-sm animation-slide-bottom" :style="[{animationDelay: (index + 1)*0.1 + 's'}]" v-for="(item,index) in osList" :key="index">
					<view class="padding radius text-center shadow-blur solid-right ">
						<view class="cu-avatar lg "  :style="{background: 'url(' + item.icon + ') no-repeat',backgroundSize:'100upx 100upx'}"><!-- <view class="cu-tag badge">99</view> --></view>
						<view class="text-lg margin-top">{{item.title}}</view>
					</view>
				</view>
			</view>

			<view class="cu-tabbar-height">
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import { us,os } from '@/common/util/work.js'
	export default {
		name: 'home',
		props:{
			cur:String,
		},
		watch: {
			cur: {
				immediate: true,
				handler() {
					console.log('watch',this.cur)
				    this.initMenu()
				},
			},
		},
		data() {
			return {
			 swiperList: [
				  {id:1,type: 'image',url: '/static/banner/banner1.png', link: ''},
				  {id:2,type: 'image',url: '/static/banner/banner2.jpg', link: ''},
				  {id:3,type: 'image',url: '/static/banner/banner3.jpg', link: ''},
				  {id:4,type: 'image',url: '/static/banner/banner4.jpg', link: ''},
				],
				middleApps: [
				  {icon: 'line2_icon1.png', title: '审批', 'text': '个人审批'},
				  {icon: 'line2_icon2.png', title: '审批稿', 'text': '审批草稿箱'},
				],
				usList:us.data,
				osList:os.data,
				websock:'',
				heartCheck:null,
				lockReconnect:false,
				msgCount:0,
				dot:{
				  mailHome:false
				}
			}
		},
		methods: {
			initMenu(){
				console.log("-----------home------------")
			    this.initWebSocket();
			    this.loadCount(0);
			},
			goPage(page){
				if(!page){
					return false;
				}
				if(page==='annotationList'){
				  this.msgCount = 0
				}
				this.dot[page]=false
				this.$router.push({name: page})
			},
			initWebSocket: function () {
				// WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
				var userId = this.$store.getters.userid;
				var url = this.$config.apiUrl.replace("https://","wss://").replace("http://","ws://")+"/websocket/"+userId;
				console.log('websocket url>>'+url);
				this.websock = new WebSocket(url);
				this.websock.onopen = this.websocketOnopen;
				this.websock.onerror = this.websocketOnerror;
				this.websock.onmessage = this.websocketOnmessage;
				this.websock.onclose = this.websocketOnclose;
			},
			websocketOnopen: function () {
				console.log("WebSocket连接成功");
				//心跳检测重置
				//this.heartCheck.reset().start();
			},
			websocketOnerror: function () {
				console.log("WebSocket连接发生错误");
				this.reconnect();
			},
			websocketOnmessage: function (e) {
				console.log("-----接收消息-------",e.data);
				var data = eval("(" + e.data + ")"); //解析对象
				if(data.cmd == "topic"){
				  //系统通知
				  this.loadCount('1')
				}else if(data.cmd == "user"){
				  //用户消息
				  this.loadCount('2')
				} else if(data.cmd == 'email'){
				  this.loadEmailCount()
				}

				//心跳检测重置
				//this.heartCheck.reset().start();
			},
			websocketOnclose: function (e) {
				console.log("connection closed (" + e.code + ")");
				this.reconnect();
			},
			websocketSend(text) { // 数据发送
				try {
				  this.websock.send(text);
				} catch (err) {
				  console.log("send failed (" + err.code + ")");
				}
			},
			reconnect() {
				var that = this;
				if(that.lockReconnect) return;
				that.lockReconnect = true;
				//没连接上会一直重连，设置延迟避免请求过多
				setTimeout(function () {
				  console.info("尝试重连...");
				  that.initWebSocket();
				  that.lockReconnect = false;
				}, 5000);
			},
			loadCount(flag){
				console.log("loadCount::flag",flag)
				let url = '/sys/annountCement/listByUser';
				this.$http.get(url).then(res=>{
					console.log("res::",res)
				  if(res.data.success){
					let msg1Count = res.data.result.anntMsgTotal;
					let msg2Count = res.data.result.sysMsgTotal;
					this.msgCount = msg1Count + msg2Count
					console.log("this.msgCount",this.msgCount)
				  }
				})
			},
			loadEmailCount(){
				this.dot.mailHome = true
			},
			getTtemDotInfo(item){
				if(item.page==='annotationList' && this.msgCount>0){
				  return this.msgCount
				}
				return '';
			}
		}
	}
</script>

<style>
  .line2-icon {
	width: 60px;
	height: 60px;
  }
</style>
