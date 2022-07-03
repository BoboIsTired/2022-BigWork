<template>
  <div class="notice">
    <div class="noticeTop">
      <marquee id="mar" class="marquee" behavior="scroll" direction="left">{{ msg }}</marquee>
      <!-- <img src="../assets/img/bg.png"> -->
    </div>
    <div class="edit">
          <label style="color:black;padding-right: 10px;width:160px;">文字位置:</label>
            <el-select v-model="content.a" placeholder="文字位置"  @change="poscg()" >
              <el-option key="1" label="上" value="上"></el-option>
              <el-option key="2" label="中" value="中"></el-option>
              <el-option key="2" label="下" value="下"></el-option>
            </el-select>
            
    </div>
  </div>
  <div class="sub" >
     <div class="input">
       <el-input
        type="textarea"
        clear="text"
        id="text"
        :rows="5"
        placeholder="请输入内容"
        v-model="msg">
      </el-input>
    </div>   
    <el-button @click="back" class="back">取消</el-button>
    <el-button @click="submit" class="submit" type="primary">提交</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return{
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
      content:{
        a:'文字位置',
        uppos:'30px',
        midpos:"260px",
        downpos:"500px"
      }
    }
  },
  mounted: function () {
    this.msg = this.$route.params.notice
    this.no = this.$route.params.no
  },
  methods: {
    poscg(){
      // console.log('sdsadasdasdad')
      // console.log(document.getElementById("mar").style.marginTop)
      var cnt = document.getElementById('mar')
      if(this.content.a == '上'){
        cnt.style.marginTop = this.content.uppos
      }else if(this.content.a == '中'){
        cnt.style.marginTop = this.content.midpos
      }else {
        cnt.style.marginTop = this.content.downpos
      }
      
      // console.log(cnt.style.marginTop)
      // document.getElementById("mar").style.marginTop = this.content.pos
    }, 
    back() {
      // this.$router.push({path: '/ ',})
    },
    submit() {
      // var date = new Date()
      // let year = date.getFullYear();
      // var month = date.getMonth() + 1;
      // var dat = date.getDate();
      // var hour = date.getHours();
      // var minutes = date.getMinutes();
      // var second = date.getSeconds();
      // var timer = year + '-' + month + '-' + dat + ' ' + hour + ':' + minutes + ':' + second;
      // this.formLabelAlign.notice=this.msg
      // this.formLabelAlign.createTime=timer
      // this.formLabelAlign.state='发布中'
      // this.formLabelAlign.author=this.$cookies.get("cname")
      // this.formLabelAlign.startTime='-',
      // this.formNotice.no=this.no,
      // this.formNotice.notice=this.msg,
      // this.formNotice.state='发布中',
      // this.formNotice.author=this.$cookies.get("cname"),
      // this.formNotice.startTime='-'
      // if(this.no != null){
      //   this.$axios({
      //     url: `/api/notice`,
      //     method: 'put',
      //     data: {
      //       ...this.formNotice
      //     }
      //   }).then(res => {
      //     if(res.data.code == 200) {
      //       this.$message({
      //         message: '数据添加成功',
      //         type: 'success'
      //       })
      //       this.$router.push({path: '/noticeList'})
      //     }
      //   })
      // }else{
      //   this.$axios({
      //     url: `/api/notice`,
      //     method: 'post',
      //     data: {
      //       ...this.formLabelAlign
      //     }
      //   }).then(res => {
      //     if(res.data.code == 200) {
      //       this.$message({
      //         message: '数据添加成功',
      //         type: 'success'
      //       })
      //       this.$router.push({path: '/noticeList'})
      //     }
      //   })
      // }
    }
  }
}
</script>

<style>
.notice{
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
  background-image:url('../assets/img/bg.png');
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
  color:white;
  width: 100%;
  /* position: relative; */
  /* margin-left: 1.8%;
  margin-top: 1.25%; */
  /* position: fixed;
  z-index: 10; */
  margin-top: 30px;
 
  /* margin-top: v-bind('content.pos'); */
}
.sub {
  height: 20%;
  /* margin-top: 80px; */
  /* position:absolute;
  bottom:0px; */
}
#text{
  width: 71.8%;
  /* margin-top: 1%; */
  background-color: #292d3e;
  border-color: #292d3e;
  color: white;
  margin-left: 2.5%;
  margin-top: 1.25%;
}
.back{
  margin-left: 20%;
}
img{
    width: 70%;
}
</style>