<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>系统管理/账户管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <div class="query"> -->
                  <span>账户名：</span>
                  <el-input v-model="query.name" size="small" placeholder="请输入账户名称" class="handle-input mr10"></el-input>
                <!-- </div> -->
                <!-- <el-input v-model="query.name" placeholder="请输入账户名称" class="handle-input mr10"></el-input> -->
                 <span>所属机构：</span>
                 <el-select v-model="query.address" placeholder="所属机构" class="handle-select mr10">
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select>
                <span>状态：</span>
                <el-select v-model="query.address" placeholder="状态" class="handle-select mr10" >
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select>
                <span>所属角色: </span>
                <el-input v-model="query.name" placeholder="请选择所属角色" class="handle-input mr10"></el-input>
                <el-button @click="handleSearch">重置</el-button>
                <el-button type="primary"  @click="handleSearch">搜索</el-button>
                <el-button type="primary"  @click="handleCreate">新建账户</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column prop="name" label="账户名"></el-table-column>
                <el-table-column prop="organization" label="所属机构"></el-table-column>
                <el-table-column prop="role" label="所属角色"></el-table-column>
                <el-table-column prop="state" label="状态">
                  <template #default="scope">
                        <el-tag :type="
                                scope.row.state === '已启用'
                                    ? 'success'
                                    : scope.row.state === '已停用'
                                    ? 'danger'
                                    : ''
                            ">{{ scope.row.state }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column prop="phoneNumber" label="手机号"></el-table-column>
                <el-table-column prop="mail" label="邮箱"></el-table-column>
                <el-table-column prop="updateTime" label="更行时间"></el-table-column>
                <!-- <el-table-column label="头像(查看大图)" align="center">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.thumb" :preview-src-list="[scope.row.thumb]">
                        </el-image>
                    </template>
                </el-table-column> -->
                <el-table-column prop="userType" label="用户类型"></el-table-column>
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
                      <el-button type="text" icon="el-icon-stop" @click="handleEdit(scope.$index, scope.row)">停用
                        </el-button>
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
         <el-dialog title="新建账户" v-model="createVisible" width="30%">
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
        const tableData = ref([
          {
            name:"jiejie",
            organization:"zucc",
            role:"me",
            state:"已启用",
            realName:"ajie",
            phoneNumber:1888888888,
            mail:"xfs@qq.com",
            updateTime:"4564",
            userType:"adas"
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
        let form = reactive({
            name: "",
            password:"dadasdasda",
            organization:"",
            role:"",
            state:"",
            realName:"",
            phoneNumber:"",
            mail:"",

        });
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
            handleSearch,
            handlePageChange,
            handleDelete,
            handleEdit,
            saveEdit,
            handleCreate
        };
    },
};
</script>

<style scoped>
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
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
