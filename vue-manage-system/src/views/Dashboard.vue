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
                                    <div class="grid-num">{{num_m}}</div>
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
                                    <div class="grid-num">{{num_p}}</div>
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
                                    <div class="grid-num">{{num_plan}}</div>
                                    <div>计划数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                
            </el-col>
            <!-- <el-col :span="8">
                <el-card shadow="hover" style="height:403px;">
                    <template #header>
                        <div class="clearfix">
                            <span>待办事项</span>
                            <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                        </div>
                    </template>

                    <el-table :show-header="false" :data="todoList" style="width:100%;">
                        <el-table-column width="40">
                            <template #default="scope">
                                <el-checkbox v-model="scope.row.status"></el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template #default="scope">
                                <div class="todo-item" :class="{
                                        'todo-item-del': scope.row.status,
                                    }">{{ scope.row.title }}</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="60">
                            <template>
                                <i class="el-icon-edit"></i>
                                <i class="el-icon-delete"></i>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../assets/img/img.jpg" class="user-avator" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">{{ name }}</div>
                            <div>{{ role }}</div>
                        </div>
                    </div>
                    <div class="user-info-list">
                        上次登录时间：
                        <span>2019-11-01</span>
                    </div>
                    <div class="user-info-list">
                        上次登录地点：
                        <span>东莞</span>
                    </div>
                </el-card>
            </el-col> -->
        </el-row>
        <el-row :gutter="20">
            <el-col :span="16">
                    <el-card class="plan-card">
                        <el-table :data="tableData" height="250" style="width: 100%">
                            <el-table-column prop="number" label="序号"  />
                            <el-table-column prop="planname" label="计划名称" />
                            <el-table-column prop="planconditon" label="计划状态" />
                            <el-table-column prop="planconditon" label="提交时间" />
                            <el-table-column prop="planconditon" label="提交人"  />
                            <el-table-column prop="delivertime" label="操作"  />
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
                            <p v-for="item in 20" :key="item" class="scrollbar-demo-item">{{ item }}</p>

                        </el-scrollbar>
                    </div>
                    </el-card>
            </el-col>

        </el-row>

        <el-row :gutter="20">
            <el-col :span="8">
                <el-card class="box-card">
                    <div class="card-header">
                        <span>设备状态</span>
                        <!-- <el-button class="button" text>Operation button</el-button> -->
                    </div>
                    <div class="con">
                        <div class="left">
                            <div class="demo-progress">
                                <el-progress type="circle" :percentage="0" />

                                <!-- <el-progress type="circle" :percentage="100" status="success" /> -->
                            </div>
                        </div>
                        <div class="right">
                            <el-button type="primary">离线</el-button>
                            <el-button type="success">播放</el-button>
                            <el-button type="info">空闲</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card class="box-card">
                    <div class="card-header">
                        <span>资源素材</span>
                        <!-- <el-button class="button" text>Operation button</el-button> -->
                    </div>
                    <div class="con">
                        <div class="left">
                            <div class="demo-progress">
                                <el-progress type="circle" :percentage="0" />

                                <!-- <el-progress type="circle" :percentage="100" status="success" /> -->
                            </div>
                        </div>
                        <div class="right">
                            <el-button type="primary">图片</el-button>
                            <el-button type="success">视频</el-button>
                            <el-button type="info">音频</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="12">
                <el-card shadow="hover">
                    <schart ref="bar" class="schart" canvasId="bar" :options="options"></schart>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover">
                    <schart ref="line" class="schart" canvasId="line" :options="options2"></schart>
                </el-card>
            </el-col>
        </el-row>


            <el-row :gutter="20">
            <el-col :span="12">
                <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
                    <el-tab-pane label="机构" name="first">
                        <el-card shadow="hover">
                            <schart ref="line" class="schart" canvasId="line" :options="options2"></schart>
                        </el-card>
                    </el-tab-pane>
                    <el-tab-pane label="分组" name="second">
                        <el-card shadow="hover">
                            <schart ref="bar" class="schart" canvasId="bar" :options="options"></schart>
                        </el-card>
                    </el-tab-pane>
                </el-tabs>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import Schart from "vue-schart";
import { reactive } from "vue";
export default {
    name: "dashboard",
    components: { Schart },
    setup() {
        let num_p = '321';
        let num_plan = '321';
        let num_m = '321';
        const name = localStorage.getItem("ms_username");
        const role = name === "admin" ? "超级管理员" : "普通用户";
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
        const options = {
            type: "bar",
            title: {
                text: "分组设备分布",
            },
            xRorate: 10,
            labels: ["设备分组1", "设备分组2", "设备分组3", "设备分组4",],

            datasets: [
                {
                    data: [0, 1, 0, 0, 0],
                },
            ],
        };
        const options2 = {
            type: "line",
            title: {
                text: "机构设备分布",
            },
            xRorate: 10,
            labels: ["zucc1", "zucc2"],
            datasets: [
                {
                    data: [0,1,],
                },
            ],
        };
        const tableData = [
        {
            date: '2016-05-03',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-02',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-04',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-01',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-08',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-06',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-07',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        ]
        // const buttons = [
        // // { type: '', text: 'plain' },
        // { type: 'primary', text: 'primary' },
        // { type: 'success', text: 'success' },
        // { type: 'info', text: 'info' },
        // // { type: 'warning', text: 'warning' },
        // // { type: 'danger', text: 'danger' },
        // ] 

        const todoList = reactive([
            {
                title: "今天要修复100个bug",
                status: false,
            },
            {
                title: "今天要修复100个bug",
                status: false,
            },
            {
                title: "今天要写100行代码加几个bug吧",
                status: false,
            },
            {
                title: "今天要修复100个bug",
                status: false,
            },
            {
                title: "今天要修复100个bug",
                status: true,
            },
            {
                title: "今天要写100行代码加几个bug吧",
                status: true,
            },
        ]);

        return {
            num_p,
            num_plan,
            num_m,
            name,
            data,
            options,
            options2,
            todoList,
            role,
            tableData,
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
.con{
    display: flex;
    padding-top: 10px;
    /* margin-left: 10px; */
    vertical-align:middle;
}
.left{
    padding-right: 1px;
    padding-left: 10px;
}
.right{
    /* display: flex; */
    align-items: center;
    margin-left: 25px;
    width: 100px;
    /* justify-content: center; */
}
</style>
