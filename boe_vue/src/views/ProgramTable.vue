<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>节目制作与发布/发布节目
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <div class="query"> -->
                <span>节目名称：</span>
                <el-input v-model="query.name" size="small" placeholder="请输入节目名称" class="handle-input mr10"></el-input>
                <!-- </div> -->
                <!-- <el-input v-model="query.name" placeholder="请输入账户名称" class="handle-input mr10"></el-input> -->
                <span>分辨率：</span>
                <el-select v-model="query.address" placeholder="分辨率" class="handle-select mr10">
                    <el-option key="1" label="1920x1080(横)" value="1920x1080(横)"></el-option>
                    <el-option key="2" label="1080x1920(竖)" value="1080x1920(竖)"></el-option>
                </el-select>
                <span>节目状态：</span>
                <el-select v-model="query.address" placeholder="节目状态" class="handle-select mr10">
                    <el-option key="1" label="未使用" value="未使用"></el-option>
                    <el-option key="2" label="已发布" value="已发布"></el-option>
                </el-select>

                <el-button @click="handleSearch">重置</el-button>
                <el-button type="primary" @click="handleSearch">查询</el-button>
            </div>
            <div class="op2">
                <div v-if="data.showPL" class="btng1">
                    <el-button type="primary" @click="dialogVisible = true">新建节目</el-button>
                    <!-- <el-button @click="handleSearch" style="margin-left:30px" disabled>批量发布</el-button>
                <el-button @click="handleCreate" style="margin-left:30px" disabled>批量删除</el-button> -->
                </div>
                <div v-else class="btng2">
                    <el-button type="primary" @click="dialogVisible = true">新建节目</el-button>
                    <el-button type="primary" @click="handleSearch" style="margin-left:30px">批量发布</el-button>
                    <el-button type="primary" @click="handleCreate" style="margin-left:30px">批量删除</el-button>
                </div>

            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="thumb" label="缩略图">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.programMaterial"
                            :preview-src-list="[scope.row.programMaterial]">
                        </el-image>
                        <el-image-viewer v-if="scope.row.showViewer" @close="closeViewer(scope.$index, scope.row)"
                            :url-list="[scope.row.programMaterial]" />
                    </template>

                </el-table-column>
                <el-table-column prop="programName" label="节目名称"></el-table-column>
                <el-table-column prop="resolution" label="分辨率"></el-table-column>
                <el-table-column prop="duration" label="节目时长"></el-table-column>
                <el-table-column prop="programSize" label="节目大小"></el-table-column>
                <el-table-column prop="programState" label="计划状态">
                    <template #default="scope">
                        <el-tag :type="
                            scope.row.programState === '未使用'
                                ? 'success'
                                : scope.row.programState === '已发布'
                                    ? 'danger'
                                    : ''
                        ">{{ scope.row.programState }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="author" label="作者"></el-table-column>
                <el-table-column prop="updateDate" label="更新时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" @click="viewImage(scope.$index, scope.row)">预览</el-button>
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">修改
                        </el-button>
                        <!-- <el-button type="text"  class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        <el-button type="text"  @click="handleEdit(scope.$index, scope.row)">加密下载
                        </el-button> -->
                        <el-button type="text" @click="handlePub(scope.$index, scope.row)">发布
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
                <el-form-item label="节目名称">
                    <el-input v-model="form.programName"></el-input>
                </el-form-item>
                <el-form-item label="分辨率">
                    <el-select v-model="form.resolution" placeholder="分辨率" class="handle-select mr10">
                        <el-option key="1" label="1920x1080(横)" value="1920x1080(横)"></el-option>
                        <el-option key="2" label="1080x1920(竖)" value="1080x1920(竖)"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="节目时长">
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
                </el-form-item> -->
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog>
        <el-dialog v-model="dialogVisible" title="新建节目" width="40%">
            <el-form label-width="70px">
                <el-form-item label="名称：">
                    <el-input v-model="form1.programName"></el-input>
                </el-form-item>
                <el-form-item label="分辨率：">
                    <el-select v-model="form1.resolutionRatio" placeholder="分辨率" class="handle-select mr10">
                        <el-option key="1" label="1920x1080(横)" value="1920x1080(横)"></el-option>
                        <el-option key="2" label="1080x1920(竖)" value="1080x1920(竖)"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="时长：">
                    <el-input-number v-model="num" :min="1" :max="60" @change="handleChange" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addProgram()">确定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { ProgramData, imgPub } from "../api/index";
import { useRouter } from "vue-router";
export default {
    name: "basetable",
    setup() {
        const query = reactive({
            id:"",
            codeId: '1'

        });
        const data = reactive({
            showPL: true
        })
        const route = new useRouter()
        const tableData = ref([
            {
                showViewer: false,
                programMaterial: ["https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"],
                programName: "1324564",
                resolution: "1",
                programState: "未使用",
                programSize: "待发布",
                duration: "1",
                author: "jx",
                updateDate: "boom"
            },
            {
                showViewer: false,
                programMaterial: ["https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"],
                programName: "1324564",
                resolution: "1",
                programState: "已发布",
                programSize: "待发布",
                duration: "1",
                author: "jx",
                updateDate: "boom"
            }
        ]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            ProgramData(query).then((res) => {

                tableData.value = res.data.records;
                // console.log(tableData.value[0])
                pageTotal.value = res.data.records.length || 50;
                for (var i = 0; i < res.data.records.length; i++) {
                    tableData.value[i].programMaterial = '/api/file/' + tableData.value[i].programMaterial
                }
                // tableData[0].programMaterial = '/api/imgs/' + tableData[0].programMaterial

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
                .catch(() => { });
        };

        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        //节目详情
        const visible = ref(false)

        const dialogVisible = ref(false)
        let form = reactive({
            programMaterial: ["https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"],
            programName: "1324564",
            resolution: "1",
            programState: "未使用",
            programSize: "待发布",
            duration: "1",
            author: "jx",
            updateDate: "boom"

        });
        let form1 = reactive({
            showViewer: false,
            thumb: [],
            programName: "",
            resolutionRatio: "",
            programDuration: "1",
            programState: "",
            playMode: "",
            playDate: "",
            playTactics: "sfsf",
            synchronous: "",
            loopType: "",
            loopTime: "",
            author: "",
            checker: "",
            updateTime: ""

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
            // Object.keys(form).forEach((item) => {
            //     tableData.value[idx][item] = form[item];
            // });
        };
        const viewImage = (index, row) => {
            row.showViewer = true
            // data.showViewer = true;


        }
        const closeViewer = (index, row) => {
            //  data.showViewer = false
            row.showViewer = false
        }

        const addProgram = () => {
            // console.log(form1)
            if (form1.programName === '' || form1.resolutionRatio === '') {
                ElMessage.error('节目内容填写不完整')
            } else {
                route.push({
                    path: '/programadd',
                    query: { name: form1.programName, resolution: form1.resolutionRatio, duration: form1.programDuration }
                })
                dialogVisible.value = false
            }

        }
        const handlePub = (index, row) => {
            // query.msg = row.programMaterial.slice(4)
            // query.msg = 'http://192.168.123.21:3000' + query.msg
            query.id=row.id
            console.log(query)
            imgPub(query).then((res) => {
                console.log(res)
                if(res!='failed'){
                    ElMessage.success('发布成功！')
                }else{
                    ElMessage.error('发布失败！')
                }
                
                
            })

        }
        const num = ref(1)
        const handleChange = () => {
            form1.programDuration = num.value
            console.log(num.value)
        }

        return {
            query,
            tableData,
            pageTotal,
            editVisible,
            createVisible,
            dialogVisible,
            form,
            form1,
            visible,
            data,
            num,
            handleChange,
            handleSearch,
            handlePageChange,
            handleDelete,
            handleEdit,
            viewImage,
            closeViewer,
            saveEdit,
            handleCreate,
            addProgram,
            handlePub
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

.op2 {}

.handle-select {
    width: 130px;
}

.handle-input {
    width: 130px;
    display: inline-block;
}

.table {
    margin-top: 10px;
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
