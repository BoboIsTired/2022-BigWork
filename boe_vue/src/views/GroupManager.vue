<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>设备管理/分组管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <div class="query"> -->
                <span>分组名称：</span>
                <el-input v-model="query.groupName" size="small" placeholder="请输入设备名称" class="handle-input mr10">
                </el-input>
                <span>所属机构：</span>
                <el-select v-model="query.mechanism" placeholder="请选择所属机构" class="handle-select mr10">
                    <el-option key="1" label="城院测试" value="城院测试"></el-option>
                </el-select>
                <el-button @click="handleClean">重置</el-button>
                <el-button type="primary" @click="handleSearch">查询</el-button>
                <el-button type="primary" @click="handleSearch">新建分组</el-button>
            </div>

            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="id" label="分组id"></el-table-column>
                <el-table-column prop="groupName" label="分组名称">
                </el-table-column>
                <el-table-column prop="mechanism" label="所属机构"></el-table-column>
                <!-- <el-table-column label="头像(查看大图)" align="center">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.thumb" :preview-src-list="[scope.row.thumb]">
                        </el-image>
                    </template>
                </el-table-column> -->
                <el-table-column prop="deviceCount" label="设备数量"></el-table-column>
                <el-table-column prop="description" label="描述"></el-table-column>
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
                        <el-button type="text" @click="handleImg(scope.$index, scope.row)">详情
                        </el-button>
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" class="red" @click="handleDelete(scope.$index, scope.row)">删除
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
        <el-dialog title="编辑" v-model="editVisible" width="80%">
            <el-form label-width="70px">
                <el-form-item label="分组名称">
                    <el-input v-model="form.deviceName"></el-input>
                </el-form-item>
                <el-form-item label="所属机构">
                    <el-select v-model="form.deviceGroup" placeholder="所属机构" class="handle-select mr10">
                        <el-option key="1" label="城院测试" value="城院测试"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="form.deviceName"></el-input>
                </el-form-item>
                <el-col :span="24">设备选择：</el-col>
                <el-table :data="tableData" border class="table" ref="multipleTable"
                    header-cell-class-name="table-header">
                    <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                    <el-table-column type="selection"></el-table-column>
                    <el-table-column prop="id" label="设备名称"></el-table-column>
                    <el-table-column prop="groupName" label="MAC地址">
                    </el-table-column>
                    <el-table-column prop="mechanism" label="分辨率"></el-table-column>

                    <el-table-column prop="deviceCount" label="所属机构"></el-table-column>
                    <el-table-column prop="description" label="当前计划"></el-table-column>
                    <el-table-column prop="description" label="设备状态"></el-table-column>
                </el-table>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit(form)">确 定</el-button>
                </span>
            </template>
        </el-dialog>
        <!-- 详情弹出框 -->
        <el-dialog :data="tableData" v-model="visible" :show-close="false">
            <template #default="scope">
                <div class="my-header">
                    <el-tabs type="border-card">
                        <el-tab-pane label="分组详情">
                            <div class="detail">
                                <el-row>
                                    <el-col :span="12">分组名称：{{ form.groupName }}</el-col>
                                    <el-col :span="12">所属机构：{{ form.mechanism }}</el-col>
                                    <el-col :span="24">描述：{{ form.description }}</el-col>
                                    <el-col :span="24">设备选择：</el-col>
                                    <el-table :data="tableData" border class="table" ref="multipleTable"
                                        header-cell-class-name="table-header">
                                        <el-table-column prop="id" label="设备名称"></el-table-column>
                                        <el-table-column prop="groupName" label="MAC地址">
                                        </el-table-column>
                                        <el-table-column prop="mechanism" label="分辨率"></el-table-column>
                                        <el-table-column prop="deviceCount" label="当前计划"></el-table-column>
                                        <el-table-column prop="description" label="设备状态"></el-table-column>

                                    </el-table>
                                </el-row>
                            </div>
                        </el-tab-pane>

                    </el-tabs>
                    <div class="backBtn">
                        <el-button type="primary" @click="visible = false">返回</el-button>
                    </div>

                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { GroupData, MachineEdit } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const query = reactive({
            id: "",
            groupName: "",
            mechanism: "",
            description: "",
            deviceCount: "",

        });
        const tableData = ref([
            {
                id: "",
                groupName: "",
                mechanism: "",
                description: "",
                deviceCount: "",
            }
        ]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            GroupData().then((res) => {
                // console.log(res.data)
                tableData.value = res.data.records;
                pageTotal.value = res.data.records.length || 50;
                console.log(tableData.value)
            });
            // console.log(tableData)
        };
        getData();

        // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            ElMessage.success(`刷新成功`);
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
                .catch(() => { });
        };
        const handleClean = () => {
            query.mechanism = "";
            query.groupName = "";
        };
        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        //节目详情
        const visible = ref(false)
        let form = reactive({
            id: "",
            groupName: "",
            mechanism: "",
            description: "",
            deviceCount: "",

        });
        const handleImg = (index, row) => {
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
            // console.log(form)
            editVisible.value = true;
        };
        const handleCreate = () => {

            createVisible.value = true;
            console.log(createVisible.value)
        };
        const saveEdit = (query) => {

            console.log(query)
            if (query.deviceName === '' || query.deviceGroup === '') {
                ElMessage.error(`设备信息不能为空`);
            } else {
                MachineEdit(query).then((res) => {
                    // console.log(res.msg)
                    if (res.msg === "成功") {
                        editVisible.value = false;
                        ElMessage.success(`修改第 ${idx + 1} 行成功`);
                        getData();
                    } else {
                        ElMessage.error(`修改失败`);
                    }

                });
            }

        };

        return {
            query,
            tableData,
            pageTotal,
            editVisible,
            createVisible,
            form,
            visible,
            handleClean,
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
