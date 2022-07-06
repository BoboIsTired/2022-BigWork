<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>计划管理/计划列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <div class="query"> -->
                  <span>计划名称：</span>
                  <el-input v-model="query.name" size="small" placeholder="请输入账户名称" class="handle-input mr10"></el-input>
                <!-- </div> -->
                <!-- <el-input v-model="query.name" placeholder="请输入账户名称" class="handle-input mr10"></el-input> -->
                <span>计划状态：</span>
                <el-select v-model="query.address" placeholder="状态" class="handle-select mr10" >
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select>
               
                <el-button @click="handleSearch">重置</el-button>
                <el-button type="primary"  @click="handleSearch">搜索</el-button>
                <el-button type="primary"  @click="$router.push('/planadd')">新建计划</el-button>
                <el-button @click="handleSearch">批量删除</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="thumb" label="缩略图">
                  <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.thumb" @click="handleImg(scope.$index, scope.row)">
                        </el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="planName" label="计划名称"></el-table-column>
                <el-table-column prop="planState" label="计划状态">
                  <template #default="scope">
                        <el-tag :type="
                                scope.row.planState === '待发布'
                                    ? 'success'
                                    : scope.row.planState === '发布中'
                                    ? 'danger'
                                    : ''
                            ">{{ scope.row.planState }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="playMode" label="播放模式"></el-table-column>
                <el-table-column prop="playDate" label="播放日期"></el-table-column>
                <el-table-column prop="author" label="作者"></el-table-column>
                <el-table-column prop="checker" label="审核人"></el-table-column>
                <!-- <el-table-column label="头像(查看大图)" align="center">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.thumb" :preview-src-list="[scope.row.thumb]">
                        </el-image>
                    </template>
                </el-table-column> -->
                <el-table-column prop="updateTime" label="更新时间"></el-table-column>
                <!-- <el-table-column label="状态" align="center">
                    <template #default="scope">
                        <el-tag :type="
                                scope.row.state === '成功'
                                    ? 'success'
                                    : scope.row.state === '失败'
                                    ? 'danger'
                                    : ''
                            ">{{ scope.row.state }}</el-tag>
                    </template>
                </el-table-column> -->

                <!-- <el-table-column prop="date" label="注册时间"></el-table-column> -->
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                      <el-button type="text"  @click="handleImg(scope.$index, scope.row)">详情
                        </el-button>
                      <el-button type="text"  @click="handleEdit(scope.$index, scope.row)">修改
                        </el-button>
                        <el-button type="text"  @click="handleEdit(scope.$index, scope.row)">复制
                        </el-button>
                        <el-button type="text"  class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        <el-button type="text"  @click="handleEdit(scope.$index, scope.row)">加密下载
                        </el-button>
                        <el-button type="text"  @click="handleEdit(scope.$index, scope.row)">发布
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form label-width="70px">
                <el-form-item label="账户名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input
                      v-model="form.password"
                      type="password"
                      placeholder="Please input password"
                      show-password
                    />
                </el-form-item>
                <el-form-item label="所属机构">
                    <el-select v-model="form.organization" placeholder="所属机构" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属角色">
                    <el-select v-model="form.role" placeholder="所属角色" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="账号状态">
                    <el-select v-model="form.state" placeholder="账号状态" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="form.realName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.mail"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.phoneNumber"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 新建弹出框 -->
         <!-- <el-dialog title="新建账户" v-model="createVisible" width="30%">
            <el-form label-width="70px">
                <el-form-item label="账户名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input
                      v-model="form.password"
                      type="password"
                      placeholder="Please input password"
                      show-password
                    />
                </el-form-item>
                <el-form-item label="所属机构">
                    <el-select v-model="form.organization" placeholder="所属机构" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属角色">
                    <el-select v-model="form.role" placeholder="所属角色" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="账号状态">
                    <el-select v-model="form.state" placeholder="账号状态" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="form.realName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.mail"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.phoneNumber"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="createVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog> -->
        <!-- 节目详情 -->
        <el-dialog :data="tableData" v-model="visible" :show-close="false">
          <template #default="scope">
            <div class="my-header">
              <el-tabs type="border-card">
                <el-tab-pane label="计划详情" >
                  <div class="detail">
                    <!-- <span>计划名称：{{form.planName}}</span>  -->
                    <el-row>
                      <el-col :span="12">计划名称：{{form.planName}}</el-col>
                      <el-col :span="12">播放日期：{{form.playDate}}</el-col>
                      <el-col :span="12">播放模式：{{form.playMode}}</el-col>
                      <el-col :span="12">播放策略：{{form.playTactics}}</el-col>
                      <el-col :span="12">多屏同步：{{form.synchronous}}</el-col>
                      <el-col :span="12">发布状态：{{form.planState}}</el-col>
                      <el-col :span="24">创建时间：{{form.updateTime}}</el-col>
                      <el-col :span="12">循环类型：{{form.loopType}}</el-col>
                      <el-col :span="12">循环时间段：{{form.loopTime}}</el-col>
                    </el-row>
                  </div>
                  <div class="progrom">
                    <span>已选节目：</span>
                    <div class="pgInfo">
                    
                        <el-image class="table-td-thumb" :src="form.thumb"  :preview-src-list="form.thumb">
                        </el-image>
                   
                    </div>
                  </div>
                  <div class="result">
                    <span>原因：</span>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="设备详情">设备详情</el-tab-pane>
              </el-tabs>
              <div class="backBtn">
                <el-button  type="primary" @click="visible=false">返回</el-button>
              </div>
              
            </div>
          </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { fetchData } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const query = reactive({
            // address: "",
            // name: "",
            // pageIndex: 1,
            // pageSize: 10,

            
        });
        const tableData = reactive([
          {
            thumb:["https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"],
            planName:"1324564",
            planState:"待发布",
            playMode:"按时段播放",
            playDate:"2022-06-29",
            playTactics:"替换",
            author:"jx",
            checker:"jiejie",
            updateTime:"boom"
          }
        ]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            fetchData(query).then((res) => {
                console.log(res.data)
                // tableData.value = res.list;
                // pageTotal.value = res.pageTotal || 50;
            });
        };
        getData();

        // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            getData();
        };
        // 分页导航
        const handlePageChange = (val) => {
            query.pageIndex = val;
            getData();
        };

        // 删除操作
        const handleDelete = (index) => {
            // 二次确认删除
            ElMessageBox.confirm("确定要删除吗？", "提示", {
                type: "warning",
            })
                .then(() => {
                    ElMessage.success("删除成功");
                    tableData.value.splice(index, 1);
                })
                .catch(() => {});
        };

        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        //节目详情
        const visible = ref(false)
        let form = reactive({
             thumb:[""],
            planName:"",
            planState:"",
            playMode:"",
            playDate:"",
            playTactics:"sfsf",
            synchronous:"",
            loopType:"",
            loopTime:"",
            author:"",
            checker:"",
            updateTime:""

        });
        const handleImg = (index,row) =>{
          Object.keys(form).forEach((item) => {
                form[item] = row[item];
            });
          visible.value = true
        }
        let idx = -1;
        const handleEdit = (index, row) => {
            idx = index;
            Object.keys(form).forEach((item) => {
                form[item] = row[item];
            });
            editVisible.value = true;
        };
        const handleCreate = () => {
           
            createVisible.value = true;
            console.log(createVisible.value)
        };
        const saveEdit = () => {
            editVisible.value = false;
            ElMessage.success(`修改第 ${idx + 1} 行成功`);
            Object.keys(form).forEach((item) => {
                tableData.value[idx][item] = form[item];
            });
        };

        return {
            query,
            tableData,
            pageTotal,
            editVisible,
            createVisible,
            form,
            visible,
            handleSearch,
            handlePageChange,
            handleDelete,
            handleEdit,
            handleImg,
            saveEdit,
            handleCreate
        };
    },
};
</script>

<style scoped>

.pgInfo {
margin-left: 70px; 
margin-top: -13px;
height: 150px;
border: 1px solid black;
}
.backBtn {
  margin-top: 10px;
  margin-left: 700px;
}
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 130px;
}

.handle-input {
    width: 130px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    
    /* margin: auto; */
     margin: 30px 0 0 30px;
    padding: 5px;
    width: 60px;
    height: 60px;
}
</style>
