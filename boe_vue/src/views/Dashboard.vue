<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-row :gutter="20" class="mgb20">
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-1">
                <i class="el-icon-lx-mobilefill grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ num_m }}</div>
                  <div>设备数量</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-2">
                <i class="el-icon-lx-recordfill grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ num_p }}</div>
                  <div>节目数量</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-3">
                <i class="el-icon-lx-noticefill grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ num_plan }}</div>
                  <div>公告数量</div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <!-- <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="plan-card">
          <el-table :data="tableData" height="250" style="width: 100%">
            <el-table-column prop="number" label="序号" />
            <el-table-column prop="planname" label="计划名称" />
            <el-table-column prop="planconditon" label="计划状态" />
            <el-table-column prop="planconditon" label="提交时间" />
            <el-table-column prop="planconditon" label="提交人" />
            <el-table-column prop="delivertime" label="操作" />
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>事件记录</span>
            <el-divider />
          </div>
          <div>
            <el-scrollbar height="200px">
              <p v-for="item in 20" :key="item" class="scrollbar-demo-item">
                {{ item }}
              </p>
            </el-scrollbar>
          </div>
        </el-card>
      </el-col>
    </el-row> -->

    <el-row :gutter="20">
      <el-col :span="7">
        <el-card class="box-card" style="height:400px">
          <div class="card-header">
            <span style="font-size:25px">设备状态</span>
            <!-- <el-button class="button" text>Operation button</el-button> -->
          </div>
          <div class="con">
            <div class="left">
              <div class="demo-progress">
                <el-progress type="circle" :percentage="100" width="150"><span class="s1">{{ sbtotal }}台</span>
                </el-progress>
              </div>
            </div>
            <div class="right">
              <ul>
                <li class="l1">
                  <div class="d1">
                    <span>离线</span>
                    <span>{{ sb_l }}</span>
                    <span>台</span>
                  </div>
                </li>
                <li class="l2">
                  <div class="d1">
                    <span>播放</span>
                    <span>{{ sb_z }}</span>
                    <span>台</span>
                  </div>
                </li>
                <li class="l3">
                  <div class="d1">
                    <span>空闲</span>
                    <span>{{ sb_k }}</span>
                    <span>台</span>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="7">
        <el-card class="box-card" style="height:400px">
          <div class="card-header">
            <span style="font-size:25px">资源素材</span>
            <!-- <el-button class="button" text>Operation button</el-button> -->
          </div>
          <div class="con">
            <div class="demo-progress">
              <div class="a">
                <span style="font-size:20px">图片</span>
                <el-progress :text-inside="true" :stroke-width="24" :percentage="30" status="success" />
              </div>
              <div class="b">
                <span style="font-size:20px">视频</span>
                <el-progress :text-inside="true" :stroke-width="22" :percentage="20" status="warning" />
              </div>
              <div class="c">
                <span style="font-size:20px">音频</span>
                <el-progress :text-inside="true" :stroke-width="20" :percentage="50" status="exception" />
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card class="box-card">
          <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
            <el-tab-pane label="机构" name="first" :lazy="true">
              <!-- <el-card > -->
              <div>
                <schart class="schart" :canvasId="canvasId1" :options="options2">
                </schart>
              </div>
              <!-- </el-card> -->
            </el-tab-pane>
            <el-tab-pane label="分组" name="second" :lazy="true">
              <!-- <el-card > -->
              <div>
                <schart class="schart" :canvasId="canvasId2" :options="options">
                </schart>
              </div>

              <!-- </el-card> -->
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="plan-card">
          <el-table :data="tableData" height="250" style="width: 100%">
            <el-table-column prop="number" label="序号" />
            <el-table-column prop="planname" label="计划名称" />
            <el-table-column prop="planconditon" label="计划状态" />
            <el-table-column prop="planconditon" label="提交时间" />
            <el-table-column prop="planconditon" label="提交人" />
            <el-table-column prop="delivertime" label="操作" />
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>事件记录</span>
            <el-divider />
          </div>
          <div>
            <el-scrollbar height="500px">
              <p v-for="item in 20" :key="item" class="scrollbar-demo-item">
                {{ item }}
              </p>
            </el-scrollbar>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Schart from "vue-schart";
import { onMounted, reactive, ref } from "vue";
import { MachineData, ProgramData, NoticeData } from "../api/index";
export default {
  name: "dashboard",
  components: { Schart },
  setup() {
    onMounted(() => {
      ProgramData().then((res) => {
        var list = res.data
        num_p.value = list.records.length || 2;
      })
      NoticeData().then((res) => {
        var list = res.data
        num_plan.value = list.records.length || 2;
      })
    })
    let num_p = ref("321");
    let num_plan = ref("321");
    let num_m = ref("321");
    let sb_l = 0;
    let sb_z = 1;
    let sb_k = 0;
    let sbtotal = sb_l + sb_z + sb_k;
    let picture = "20";
    let ship = "10";
    let music = "20";
    const activeName = ref('second')
    const getData = () => {
      MachineData().then((res) => {
        var list = res.data
        // console.log(list.records)
        // tableData.value = list.records;
        num_m.value = list.records.length || 2;
      });

    };
    getData();
    const name = localStorage.getItem("ms_username");
    // const role = name === "admin" ? "超级管理员" : "普通用户";
    const data = reactive([
      {
        name: "2018/09/04",
        value: 1083,
      },
      {
        name: "2018/09/05",
        value: 941,
      },
      {
        name: "2018/09/06",
        value: 1139,
      },
      {
        name: "2018/09/07",
        value: 816,
      },
      {
        name: "2018/09/08",
        value: 327,
      },
      {
        name: "2018/09/09",
        value: 228,
      },
      {
        name: "2018/09/10",
        value: 1065,
      },
    ]);
    const canvasId1 = 'myCanvas1'
    const canvasId2 = 'myCanvas2'
    const handleClick = () => {
      console.log(activeName.value)
    }
    const options = {
      type: "bar",
      title: {
        text: "分组设备分布",
      },
      xRorate: 10,
      labels: ["测试分组1", "测试分组2", "测试分组3", "测试分组4"],

      datasets: [
        {
          data: [0, 1, 0, 0],
        },
      ],
    };
    const options2 = {
      type: "bar",
      title: {
        text: "机构设备分布",
      },
      xRorate: 10,
      labels: ["城院测试", "城院测试2"],
      datasets: [
        {
          data: [0, 1],
        },
      ],
    };

    return {
      num_p,
      num_plan,
      num_m,
      name,
      data,
      options,
      options2,

      sb_l,
      sb_k,
      sb_z,
      sbtotal,
      picture,
      ship,
      music,
      canvasId1,
      canvasId2,
      activeName,

      handleClick
      // buttons
    };
  },
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}

.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
  background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
  color: rgb(242, 94, 67);
}

.user-info {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 2px solid #ccc;
  margin-bottom: 20px;
}

.user-avator {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

.user-info-cont {
  padding-left: 50px;
  flex: 1;
  font-size: 14px;
  color: #999;
}

.user-info-cont div:first-child {
  font-size: 30px;
  color: #222;
}

.user-info-list {
  font-size: 14px;
  color: #999;
  line-height: 25px;
}

.user-info-list span {
  margin-left: 70px;
}

.mgb20 {
  margin-bottom: 20px;
}

.todo-item {
  font-size: 14px;
}

.todo-item-del {
  text-decoration: line-through;
  color: #999;
}

.schart {
  width: 100%;
  height: 300px;
}

/* 滚动条 */
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 35px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  background-color: #bebebe;
  color: var(--el-color-primary);
}

/* 下划线 */
.demo-border .text {
  width: 15%;
}

.demo-border .line {
  width: 70%;
}

.demo-border .line div {
  width: 100%;
  height: 0;
  border-top: 1px solid var(--el-border-color);
}

/* 设备状态 */
.con {
  display: flex;
  padding-top: 10px;
  /* margin-left: 10px; */
  vertical-align: middle;
  margin: 100px 0px;
}

.left {
  padding-right: 1px;
  padding-left: 10px;
}

.right {
  display: flex;
  align-items: center;
  margin-left: 75px;
  width: 100px;
  padding-right: 20px;
  /* justify-content: center; */
}

/*资源 */
.a {
  display: flex;
}

.b {
  display: flex;
}

.c {
  display: flex;
}

.demo-progress .el-progress--line {
  left: 40px;
  margin-bottom: 15px;
  width: 280px;
}

.right {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.l1::marker {
  color: rgb(120, 128, 141);
  font-size: 40px;

}

.l2::marker {
  color: rgb(78, 203, 115);
  font-size: 40px;
}

.l3::marker {
  color: rgb(251, 212, 55);
  font-size: 40px;
}

.d1 {
  margin-top: 0px;
  font-size: 20px;
}

.s1 {
  font-size: 30px;
}
</style>
