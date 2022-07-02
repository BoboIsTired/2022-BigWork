<template>
  <div class="planadd">
    <div class="title">
      <h4>设置日程</h4>
    </div>
    <div class="planInfo">
      <div class="planname">
        <span>计划名称：</span>
        <el-input v-model="input" class="handle-input mr10" @input="cg"></el-input>
      </div>
      <div class="playTactics">
        <span>播放策略：</span> 
        <el-radio-group v-model="radio1" class="ml-4">
          <el-radio label="替换" size="large" @change="cg">替换</el-radio>
          <el-radio label="插播" size="large" @change="cg">插播</el-radio>
        </el-radio-group> 
      </div> 
      <div class="playMode">
        <span>播放模式：</span>
          <el-radio-group v-model="radio2" class="ml-4">
            <el-radio label="时段播放" size="large" @change="cg">时段播放</el-radio>
            <el-radio label="持续播放" size="large" @change="cg">持续播放</el-radio>
          </el-radio-group> 
      </div>
      <div class="synchronous">
        <span>多屏同步：</span>
        <el-radio-group v-model="radio3" class="ml-4">
          <el-radio label="打开" size="large" @change="cg">打开</el-radio>
          <el-radio label="关闭" size="large" @change="cg">关闭</el-radio>
        </el-radio-group> 
        <span class="tip">(打开此功能会使播放同一计划的设备画面保持统一)</span> 
      </div>
      <div class="playDate">
        <span>播放日期：</span>
        <el-date-picker
        v-model="value1"
        type="daterange"
        range-separator="To"
        value-format="YYYY-MM-DD"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        size="default"
        @panel-change="cg"
      />
      </div>
      <div v-if="form.playMode=='时段播放'" class="addplan1">
        <div class="left">
            <div class="tq" v-for="(item,index) in timeqlist" :key="index">
                <div class="looptime">
                  <strong>循环时间段：</strong>
                  <span style="margin-left:80px">
                    <el-button type="text" icon="el-icon-edit" @click="tq"></el-button>
                    <el-button type="text" icon="el-icon-delete" @click="tq" style="margin-left:20px"></el-button>
                  </span>
                  <div class="text" style="margin-top:-5px">{{item.looptime}}</div>
                </div>
                <div class="looptype" style="margin-top:-10px">
                  <strong>循环周期：</strong>
                  <div class="ttext" style="margin-top:5px">455454</div>
                </div>
            </div>
            <div class="timeq">
              <span class="s1">
                <span>➕</span>
                <span @click="dialogVisible = true">添加时间段</span>
              </span>
              
            </div>
        </div>
        <div class="right">
            dffasdas
        </div>
      </div>
      <div v-else class="addplan2">
        dasdasddasdas
      </div>
    </div>
     <el-dialog
        v-model="dialogVisible"
        title="设置播放时间段"
        width="50%"
      >
          <span>循环时间段：</span>
         <el-time-picker
            v-model="value2"
            is-range
            range-separator="To"
            start-placeholder="Start time"
            end-placeholder="End time"
            value-format="HH:mm:ss"
            @change="addtq"
          />
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="dialogVisible = false"
              >Confirm</el-button
            >
          </span>
        </template>
      </el-dialog>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'

export default {
  setup() {
    const input = ref('');
    const radio1 = ref('替换')
    const radio2 = ref('时段播放')
    const radio3 = ref('打开')
    const value1 = ref('')
    const value2 = ref('')
    const timeqlist = reactive([
    //   {
    //   looptime:"312",
    //   loopperiod:"2313"
    // },
   ])
    const form = reactive({
      planname:"",
      playTactics:"替换",
      playMode:"时段播放",
      synchronous:"打开",
      playDate:[]
    })
    const addtq = () => {
      let string = value2.value[0]+ "~" + value2.value[1]
      console.log(string)
      let tq = {  
        looptime:"",
        loopperiod:"2331"}
        tq.looptime = string
      timeqlist.push(tq)
    }
    const cg = () => {
      
      form.planname = input.value
      form.synchronous = radio3.value
      form.playMode = radio2.value
      form.playTactics = radio1.value
      form.playDate = value1.value
      console.log(form)
    } 
    const dialogVisible = ref(false)

    return {
    input,
    radio1,
    radio2,
    radio3,
    value1,
    value2,
    form,
    timeqlist,
    cg,
    addtq,
    dialogVisible
  }
  }
  
  
}
</script>

<style scoped>
.title{
  margin-top: 15px;
  margin-left: 10px;
}
.planInfo{
  margin-top: 15px;
  margin-left: 45px;
}
.playMode {
  margin-top: 30px;
}
.playTactics {
  margin-top: 30px;
}
.synchronous {
  margin-top: 30px;
}
.tip {
  margin-left: 28px;
  font-size: 5px;
}
.playDate {
  margin-top: 30px;
}
.addplan1{
  display: flex;
}
.left {
  margin-top: 30px;
  display: inline-block;
  border: 1px solid black;
  height: 700px;
  width: 300px;
}
.looptime{
  margin: 10px;
}
.looptype{
  margin: 10px;
}
.tq {
  margin: 15px;
  border: 2px solid black;
  height: 120px;
}
.timeq{
  text-align: center;
  margin: 15px;
  cursor: pointer;
  height: 95px;
  border: 2px dashed black;
  border-radius: 5px;
}
.s1{
  margin:auto auto;
  display:block;
  position:relative;
  top:35px;
}
.right {
  margin-top: 30px;
  display: inline-block;
  flex: 1;
  margin-left: 20px;
  border: 1px solid black;
  height: 700px;
}
.handle-input {
    width: 150px;
    display: inline-block;
    
}
.mr10 {
    margin-right: 10px;
}
.example-basic .el-date-editor {
  margin: 8px;
}
</style>