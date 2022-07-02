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
                <el-input v-model="query.accountName" size="small" placeholder="请输入账户名称" class="handle-input mr10">
                </el-input>
                <!-- </div> -->
                <!-- <el-input v-model="query.name" placeholder="请输入账户名称" class="handle-input mr10"></el-input> -->
                <span>所属机构：</span>
                <el-select v-model="query.mechanism" placeholder="所属机构" class="handle-select mr10">
                    <el-option key="1" label="城院测试" value="城院测试"></el-option>
                </el-select>
                <span>状态：</span>
                <el-select v-model="query.enable" placeholder="状态" class="handle-select mr10">
                    <el-option key="1" label="启用" value="启用"></el-option>
                    <el-option key="2" label="停用" value="停用"></el-option>
                </el-select>
                <span>所属角色: </span>
                <el-select v-model="query.role" placeholder="请选择所属角色" class="handle-select mr10">
                    <el-option key="1" label="啵啵" value="啵啵"></el-option>
                </el-select>
                <el-button @click="handleClean">重置</el-button>
                <el-button type="primary" @click="handleSearch">搜索</el-button>
                <el-button type="primary" @click="handleCreate">新建账户</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column prop="accountName" label="账户名"></el-table-column>
                <el-table-column prop="mechanism" label="所属机构"></el-table-column>
                <el-table-column prop="role" label="所属角色"></el-table-column>
                <el-table-column prop="enable" label="状态">
                    <template #default="scope">
                        <el-tag @click="handleState(scope.$index, scope.row)" :type="
                            scope.row.enable === '启用'
                                ? 'success'
                                : scope.row.enable === '停用'
                                    ? 'danger'
                                    : ''
                        ">{{ scope.row.enable }}</el-tag>

                    </template>
                </el-table-column>
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>



                <!-- <el-table-column label="头像(查看大图)" align="center">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.thumb" :preview-src-list="[scope.row.thumb]">
                        </el-image>
                    </template>
                </el-table-column> -->

                <!-- <el-table-column prop="date" label="注册时间"></el-table-column> -->
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <!-- <el-button type="text" @click="handleState(scope.$index, scope.row)">{{ scope.row.id }}
                        </el-button> -->
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
                    <el-input v-model="form.accountName"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.password" type="password" placeholder="Please input password"
                        show-password />
                </el-form-item>
                <el-form-item label="所属机构">
                    <el-select v-model="form.mechanism" placeholder="所属机构" class="handle-select mr10">
                        <el-option key="1" label="城院测试" value="城院测试"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属角色">
                    <el-select v-model="form.role" placeholder="所属角色" class="handle-select mr10">
                        <el-option key="1" label="啵啵" value="啵啵"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="账号状态">
                    <el-select v-model="form.enable" placeholder="账号状态" class="handle-select mr10">
                        <el-option key="1" label="广东省" value="广东省"></el-option>
                    </el-select>
                </el-form-item> -->
                <el-form-item label="真实姓名">
                    <el-input v-model="form.realName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit(form)">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 新建弹出框 -->
        <el-dialog title="新建账户" v-model="createVisible" width="30%">
            <el-form label-width="70px">
                <el-form-item label="账户名">
                    <el-input v-model="New.accountName"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="New.password" type="password" placeholder="Please input password"
                        show-password />
                </el-form-item>
                <el-form-item label="所属机构">
                    <el-select v-model="New.mechanism" placeholder="所属机构" class="handle-select mr10">
                        <el-option key="1" label="城院测试" value="城院测试"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属角色">
                    <el-select v-model="New.role" placeholder="所属角色" class="handle-select mr10">
                        <el-option key="1" label="啵啵" value="啵啵"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="账号状态">
                    <el-select v-model="New.enable" placeholder="账号状态" class="handle-select mr10">
                        <el-option key="1" label="启用" value="启用"></el-option>
                        <el-option key="2" label="停用" value="停用"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="New.realName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="New.email"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="New.phone"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="createVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveCreate(New)">确 定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { fetchData, userAdd, userEdit } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const query = reactive({
            accountName: "",
            mechanism: "",
            role: "",
            enable: "",
            pageIndex: 1,
            pageSize: 10,
            search: "",
        });

        const tableData = ref([
            {
                id: "",
                accountName: "jiejie",
                mechanism: "zucc",
                role: "me",
                enable: "1",
                enableText: "已启用",
                realName: "ajie",
                phone: 1888888888,
                email: "xfs@qq.com",
            }

        ]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            fetchData().then((res) => {
                var list = res.data
                // console.log(list.records)
                tableData.value = list.records;
                pageTotal.value = list.records.length || 50;
            });
        };
        getData();

        // 查询操作
        const handleClean = () => {
            query.pageIndex = 1;
            query.accountName = "";
            query.mechanism = "";
            query.role = "";
            query.enable = "";
        };
        const handleSearch = () => {
            query.search = query.accountName
            fetchData(query).then((res) => {
                var list = res.data
                // console.log(query)
                tableData.value = list.records;
                pageTotal.value = list.records.length || 50;
            });
            handleClean()
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

        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        let New = reactive({
            accountName: "",
            password: "",
            mechanism: "城院测试",
            role: "啵啵",
            enable: "停用",
            realName: "",
            phone: "",
            email: "",

        });
        let form = reactive({
            id: "",
            accountName: "",
            password: "",
            mechanism: "",
            role: "",
            enable: "",
            realName: "",
            phone: "",
            email: "",

        });

        let idx = -1;
        const handleEdit = (index, row) => {
            idx = index;
            Object.keys(form).forEach((item) => {

                form[item] = row[item];
            });
            // console.log(form)
            editVisible.value = true;
        };
        const handleState = (index, row) => {
            console.log(row)
            if (row.enable == "启用") {
                row.enable = "停用"
                userEdit(row).then((res) => {
                    ElMessage.success(`状态修改成功`);
                    getData();
                });

            }
            else {
                row.enable = "启用"
                userEdit(row).then((res) => {
                    ElMessage.success(`状态修改成功`);
                    getData();
                });
            }
        }
        const handleCreate = () => {
            createVisible.value = true;

        };
        const saveCreate = (query) => {
            // console.log(query)
            if (query.accountName === '' || query.password === '' || query.realName === '' || query.phone === '' || query.email === '') {
                ElMessage.error(`新用户详情不能为空`);
            } else {
                userAdd(query).then((res) => {
                    ElMessage.success(`新用户创建成功`);
                    createVisible.value = false;
                    getData();
                });
            }

        }
        const saveEdit = (query) => {
            console.log(query)
            if (query.accountName === '' || query.password === '' || query.realName === '' || query.phone === '' || query.email === '') {
                ElMessage.error(`用户详情不能修改为空`);
            } else {
                userEdit(query).then((res) => {
                    if (res.msg === "成功") {
                        editVisible.value = false;
                        ElMessage.success(`修改第 ${idx + 1} 行成功`);
                        getData();
                    } else {
                        ElMessage.error(`修改失败`);
                    }

                });
            }
            // Object.keys(form).forEach((item) => {
            //     tableData.value[idx][item] = form[item];
            // });
        };

        return {
            // data,
            query,
            tableData,
            pageTotal,
            editVisible,
            createVisible,
            form,
            New,
            handleSearch,
            handlePageChange,
            handleDelete,
            handleEdit,
            saveEdit,
            handleCreate,
            handleState,
            handleClean,
            saveCreate
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
