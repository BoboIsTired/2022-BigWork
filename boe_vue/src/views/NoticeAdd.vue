<template>
  <div class="notice">
    <div class="noticeTop">
      <marquee id="mar" class="marquee" behavior="scroll" direction="left">{{ msg }}</marquee>
      <!-- <img src="../assets/img/bg.png"> -->
    </div>
    <div class="edit">
      <label style="color:black;padding-right: 10px;width:160px;">文字位置:</label>
      <el-select v-model="content.a" placeholder="文字位置" @change="poscg()">
        <el-option key="1" label="上" value="上"></el-option>
        <el-option key="2" label="中" value="中"></el-option>
        <el-option key="2" label="下" value="下"></el-option>
      </el-select>
      <br>
      <br>
      <label style="color:black;padding-right: 10px;width:160px;">文字大小:</label>
      <el-slider v-model="content.size" @input="sizecg()"></el-slider>
      <br>
      <br>
      <label style="color:black;padding-right: 10px;width:160px;">字体颜色:(r,g,b)</label>
      <el-slider v-model="content.r" max="255" @input="colorcg()"></el-slider>
      <el-slider v-model="content.g" max="255" @input="colorcg()"></el-slider>
      <el-slider v-model="content.b" max="255" @input="colorcg()"></el-slider>
    </div>
  </div>
  <div class="sub">
    <div class="input">
      <el-input type="textarea" clear="text" id="text" :rows="5" placeholder="请输入内容" v-model="msg">
      </el-input>
    </div>
    <el-button @click="back" class="back">取消</el-button>
    <el-button @click="submit" class="submit" type="primary">提交</el-button>
  </div>
  <div></div>
</template>

<script>
import { useRouter } from "vue-router";
import router from "../router";
import { NoticeCreate, MaterialCreate, userEdit } from "../api/index";
import { ElMessage } from 'element-plus';
export default {

  data() {
    const router = useRouter();
    return {
      msg: '',
      no: '',
      formLabelAlign: {
        notice: '',
        startTime: '',
        state: '',
        author: '',
        createTime: ''
      },
      formNotice: {
        no: '',
        notice: '',
        startTime: '',
        state: '',
        author: '',
      },
      content: {
        a: '文字位置',
        size: '',
        r: 255,
        g: 255,
        b: 255,
        uppos: '40px',
        midpos: "260px",
        downpos: "500px"
      },
      noticeAdd: {
        noticeTitle: router.currentRoute.value.query.noticeTitle,
        fontColor: "",
        fontSize: "0",
        fontPosition: "",
        body: "",
        author:localStorage.getItem("ms_realName")
      }
    }
  },
  mounted: function () {
    this.msg = this.$route.params.notice
    this.no = this.$route.params.no
  },
  methods: {
    
    poscg() {
      // console.log('sdsadasdasdad')
      // console.log(document.getElementById("mar").style.marginTop)
      var cnt = document.getElementById('mar')
      if (this.content.a == '上') {
        cnt.style.marginTop = this.content.uppos
      } else if (this.content.a == '中') {
        cnt.style.marginTop = this.content.midpos
      } else {
        cnt.style.marginTop = this.content.downpos
      }

      // console.log(cnt.style.marginTop)
      // document.getElementById("mar").style.marginTop = this.content.pos
    },
    sizecg() {
      var cnt = document.getElementById('mar')
      // console.log(this.content.size)
      cnt.style.fontSize = this.content.size + 'px'
    },
    colorcg() {
      var cnt = document.getElementById('mar')
      // console.log(this.content.r)
      // console.log(this.content.g)
      // console.log(this.content.b)
      var string = 'rgb(' + this.content.r + ',' + this.content.g + ',' + this.content.b + ')'
      console.log(string)
      cnt.style.color = string
    },
    back() {
      this.msg = ""
      // console.log(this.content.r.toString(16))
    },
    submit() {

      this.noticeAdd.fontPosition = this.content.a
      this.noticeAdd.fontSize = this.content.size
      this.noticeAdd.body = this.msg
      this.noticeAdd.fontColor = '#' + this.content.r.toString(16) +  this.content.g.toString(16) +  this.content.b.toString(16) 
      console.log(this.msg)
      if (this.noticeAdd.fontPosition === '文字位置' || this.noticeAdd.fontSize === '' || this.noticeAdd.body === '' || this.noticeAdd.body === undefined) {
        ElMessage.error('公告信息不完整')
      } else {
        NoticeCreate(this.noticeAdd).then((res) => {
          console.log(res.msg)
          if (res.msg === '成功') {
            ElMessage.success('公告创建成功')
          }

        })

      }
    }
  }
}
</script>

<style>
.notice {
  /* padding: 0px 2%px; */
  display: flex;
  justify-content: space-around;
  width: 100%;
  height: 80%;
  margin: 0;
  /* position:relative; */
  /* margin-left: 2%; */
}

.noticeTop {
  /* width: 1439px;
  height: 850px; */
  width: 80%;
  /* position: relative; */
  /* margin-left: 1.8%;
  margin-top: 1.25%; */
  background-image: url('../assets/img/bg.png');
  /* background-size: contain; */
  background-size: 100% 100%;
  background-repeat: no-repeat;
  /* padding-bottom: 47.27%; */
  /* position: fixed;
  z-index: 10; */
}

.edit {
  margin-left: 20px;
}

.marquee {
  color: white;
  width: 100%;
  /* position: relative; */
  /* margin-left: 1.8%;
  margin-top: 1.25%; */
  /* position: fixed;
  z-index: 10; */
  margin-top: 40px;
  font-size: 1px;

  /* margin-top: v-bind('content.pos'); */
}

.sub {
  height: 20%;
  /* margin-top: 80px; */
  /* position:absolute;
  bottom:0px; */
}

#text {
  width: 71.8%;
  /* margin-top: 1%; */
  background-color: #292d3e;
  border-color: #292d3e;
  color: white;
  margin-left: 2.5%;
  margin-top: 1.25%;
  /* font-size: 100px; */
}

.back {
  margin-left: 20%;
}

img {
  width: 70%;
}
</style>