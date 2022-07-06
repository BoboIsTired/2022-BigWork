<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>设备管理/设备列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <div class="query"> -->
                <span>设备名称：</span>
                <el-input v-model="query.deviceName" size="small" placeholder="请输入设备名称" class="handle-input mr10">
                </el-input>
                <span>所属机构：</span>
                <el-select v-model="query.mechanism" placeholder="请选择所属机构" class="handle-select mr10">
                    <el-option key="1" label="城院测试" value="城院测试"></el-option>
                </el-select>
                <span>所属分组：</span>
                <el-select v-model="query.deviceGroup" placeholder="请选择所属分组" class="handle-select mr10">
                    <el-option key="1" label="group_test" value="group_test"></el-option>
                    <el-option key="2" label="222" value="222"></el-option>
                </el-select>
            </div>
            <div class="handle-box">
                <span>MAC地址：</span>
                <el-input v-model="query.mac" size="small" placeholder="请输入MAC地址" class="handle-input mr10"></el-input>
                <span>分辨率：</span>
                <el-input v-model="query.resolution" size="small" placeholder="请输入分辨率" class="handle-input mr10">
                </el-input>
                <span>设备状态：</span>
                <el-select v-model="query.state" placeholder="请选择设备状态" class="handle-select mr10">
                    <el-option key="1" label="离线" value="离线"></el-option>
                    <el-option key="2" label="播放" value="播放"></el-option>
                    <el-option key="3" label="空闲" value="空闲"></el-option>
                </el-select>
            </div>
            <div class="handle-box">
                <span>系统版本：</span>
                <el-select v-model="query.ip" placeholder="请选择系统版本" class="handle-select mr10">
                    <el-option key="1" label="最新" value="最新"></el-option>
                </el-select>
                <span>当前计划：</span>
                <el-select v-model="query.plan" placeholder="请选择当前计划" class="handle-select mr10">
                    <el-option key="1" label="111" value="111"></el-option>
                </el-select>

                <span>SN：</span>
                <el-input v-model="query.snCode" size="small" placeholder="请输入SN" class="handle-input mr10"></el-input>
                <el-button @click="handleClean">重置</el-button>
                <el-button type="primary" @click="handleSearch">查询</el-button>
            </div>

            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="deviceName" label="设备名称">
                </el-table-column>
                <el-table-column prop="mechanism" label="所属机构"></el-table-column>
                <el-table-column prop="deviceGroup" label="所属分组">
                </el-table-column>
                <el-table-column prop="mac" label="MAC地址"></el-table-column>
                <el-table-column prop="resolution" label="分辨率"></el-table-column>
                <el-table-column prop="state" label="设备状态"></el-table-column>
                <!-- <el-table-column label="头像(查看大图)" align="center">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.thumb" :preview-src-list="[scope.row.thumb]">
                        </el-image>
                    </template>
                </el-table-column> -->
                <el-table-column prop="plan" label="当前计划"></el-table-column>
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
                        <el-button type="text" disabled @click="handleEdit(scope.$index, scope.row)">控制
                        </el-button>
                        <el-button type="text" @click="handleSearch()">刷新
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
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form label-width="70px">
                <el-form-item label="设备名称">
                    <el-input v-model="form.deviceName"></el-input>
                </el-form-item>
                <el-form-item label="所属分组">
                    <el-select v-model="form.deviceGroup" placeholder="所属分组" class="handle-select mr10">
                        <el-option key="1" label="group_test" value="group_test"></el-option>
                        <el-option key="2" label="222" value="222"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit(form)">确 定</el-button>
                </span>
            </template>
        </el-dialog>
        <!-- 节目详情 -->
        <el-dialog :data="tableData" v-model="visible" :show-close="false">
            <template #default="scope">
                <div class="my-header">
                    <el-tabs type="border-card">
                        <el-tab-pane label="设备信息">
                            <div class="detail">
                                <el-row>
                                    <el-col :span="12">设备型号：HiDPTAndroid Hi3751V553</el-col>
                                    <el-col :span="12">系统版本：BOE_iGallery32_V13103_V5.2.0</el-col>
                                    <el-col :span="12">设备ip：{{ form.ip }}</el-col>
                                    <el-col :span="12">信发版本：1.3.1</el-col>
                                    <el-col :span="12">有线MAC地址：{{ form.mac }}</el-col>
                                    <el-col :span="12">无线MAC地址：{{ form.mac }}</el-col>
                                    <el-col :span="12">运行内存：0.96 GB</el-col>
                                    <el-col :span="12">SN:{{ form.snCode }}</el-col>
                                    <el-col :span="12">储存空间：4.65 GB可用（共 5.39 GB）</el-col>
                                    <el-col :span="12">激活时间：2022-06-23 10:40:12</el-col>
                                </el-row>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="安装信息">
                            <div class="detail">
                                <el-row>
                                    <el-col :span="12">设备名称：测试误删</el-col>
                                    <el-col :span="12">注册时间：{{ form.startDate }}</el-col>
                                    <el-col :span="12">所属分组：{{ form.deviceGroup }}</el-col>
                                    <el-col :span="12">信发版本：1.3.1</el-col>
                                    <el-col :span="12">分辨率：{{ form.resolution }}</el-col>
                                    <el-col :span="12">所属机构：{{ form.mechanism }}</el-col>
                                    <el-col :span="12">屏显方式：横屏</el-col>
                                    <el-col :span="24">安装位置:中国浙江省杭州市拱墅区上塘街道东教路</el-col>
                                </el-row>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="状态信息">
                            <div class="detail">
                                <el-row>
                                    <el-col :span="12">设备状态：{{ form.state }}</el-col>
                                    <el-col :span="12">当前计划：{{ form.plan }}</el-col>
                                    <el-col :span="12">设备运行时长：</el-col>
                                    <el-col :span="12">最后心跳时间：{{ form.deadDate }}</el-col>
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
import { MachineData, MachineEdit } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const query = reactive({
            id: "",
            deviceName: "",
            mechanism: "",
            deviceGroup: "",
            mac: "",
            snCode: "",
            ip: "",
            plan: "",
            state: "",
            resolution: "",
            deadDate: "",
            startDate: "",

        });
        const tableData = ref([
            {
                id: "",
                deviceName: "",
                mechanism: "",
                deviceGroup: "",
                mac: "",
                snCode: "",
                ip: "",
                plan: "",
                state: "",
                resolution: "",
                deadDate: "",
                startDate: "",
            }
        ]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            MachineData().then((res) => {
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
            query.deviceGroup = "";
            query.deviceName = "";
            query.ip = "";
            query.mac = "";
            query.mechanism = "";
            query.plan = "";
            query.resolution = "";
            query.snCode = "";
            query.state = "";
        };
        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        //节目详情
        const visible = ref(false)
        let form = reactive({
            id: "",
            deviceName: "",
            mechanism: "",
            deviceGroup: "",
            mac: "",
            snCode: "",
            ip: "",
            plan: "",
            state: "",
            resolution: "",
            deadDate: "",
            startDate: "",

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
                    }else{
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
