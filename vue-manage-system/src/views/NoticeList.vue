<template>
  <div class="notice">
    <div class="noticeTop">
      <marquee class="marquee" behavior="scroll" direction="left">{{ msg }}</marquee>
      <img src="../assets/img/bg.png">
    </div>
    <el-input
      type="textarea"
      clear="text"
      id="text"
      :rows="5"
      placeholder="请输入内容"
      v-model="msg">
    </el-input>
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
      }
    }
  },
  mounted: function () {
    this.msg = this.$route.params.notice
    this.no = this.$route.params.no
  },
  methods: {
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
  padding: 0px 2%px;
  width: 96%;
  height: 4%;
  margin-left: 2%;
}
.noticeTop .marquee{
  color: white;
  width: 40%;
  position: relative;
  margin-left: 1.8%;
  margin-top: 1.25%;
  position: fixed;
  z-index: 10;
}
#text{
  width: 51.8%;
  margin-top: 1%;
  background-color: #292d3e;
  border-color: #292d3e;
  color: white;
}
.back{
  margin-left: 20%;
}
img{
    width: 70%;
}
</style>