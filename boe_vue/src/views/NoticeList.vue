<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>节目制作与发布/发布公告
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <div class="query"> -->
                <!-- <span>节目名称：</span>
                <el-input v-model="query.name" size="small" placeholder="请输入节目名称" class="handle-input mr10"></el-input> -->
                <!-- </div> -->
                <!-- <el-input v-model="query.name" placeholder="请输入账户名称" class="handle-input mr10"></el-input> -->
                <!-- <span>分辨率：</span>
                <el-select v-model="query.address" placeholder="公告状态" class="handle-select mr10">
                    <el-option key="1" label="1920x1080(横)" value="1920x1080(横)"></el-option>
                    <el-option key="2" label="1080x1920(竖)" value="1080x1920(竖)"></el-option>
                </el-select> -->
                <span>公告状态：</span>
                <el-select v-model="query.address" placeholder="公告状态" class="handle-select mr10">
                    <el-option key="1" label="未使用" value="未使用"></el-option>
                    <el-option key="2" label="已发布" value="已发布"></el-option>
                </el-select>

                <el-button @click="handleSearch">重置</el-button>
                <el-button type="primary" @click="handleSearch">查询</el-button>
            </div>
            <div class="op2">
                <div v-if="data.showPL" class="btng1">
                    <el-button type="primary" @click="dialogVisible = true" >新建公告</el-button>
                    <!-- <el-button @click="handleSearch" style="margin-left:30px" disabled>批量发布</el-button>
                <el-button @click="handleCreate" style="margin-left:30px" disabled>批量删除</el-button> -->
                </div>
                <div v-else class="btng2">
                    <el-button type="primary" @click="dialogVisible = true" >新建公告</el-button>
                    <!-- <el-button type="primary" @click="handleSearch" style="margin-left:30px">批量发布</el-button>
                    <el-button type="primary" @click="handleCreate" style="margin-left:30px">批量删除</el-button> -->
                </div>

            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="noticeTitle" label="公告标题">
                </el-table-column>
                <el-table-column prop="body" label="公告内容"></el-table-column>
                <!-- <el-table-column prop="resolution" label="分辨率"></el-table-column>
                <el-table-column prop="duration" label="节目时长"></el-table-column>
                <el-table-column prop="programSize" label="节目大小"></el-table-column> -->
                <!-- <el-table-column prop="programState" label="公告状态">
                    <template #default="scope">
                        <el-tag :type="
                            scope.row.programState === '未使用'
                                ? 'success'
                                : scope.row.programState === '已发布'
                                    ? 'danger'
                                    : ''
                        ">{{ scope.row.programState }}</el-tag>
                    </template>
                </el-table-column> -->
                <el-table-column prop="fontSize" label="字体大小"></el-table-column>
                <el-table-column prop="fontColor" label="字体颜色"></el-table-column>
                <el-table-column prop="fontPosition" label="字体位置"></el-table-column>
                <el-table-column prop="author" label="作者"></el-table-column>
                
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" @click="handleImg(scope.$index, scope.row)">详情</el-button>
                        <!-- <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button> -->
                        <!-- <el-button type="text"  class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        <el-button type="text"  @click="handleEdit(scope.$index, scope.row)">加密下载
                        </el-button> -->
                        <el-button type="text"  @click="handlePub(scope.$index, scope.row)">发布
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
        
        <el-dialog :data="tableData" v-model="visible" :show-close="false">
            <template #default="scope">
                <div class="my-header">
                    <el-tabs type="border-card">
                        <el-tab-pane label="公告详情">
                            <div class="detail">
                                <el-row>
                                    <el-col :span="24">标题：{{ form.noticeTitle }}</el-col>
                                    <el-col :span="24">公告内容：{{ form.body }}</el-col>
                                    <el-col :span="24">创建时间：{{ form.startTime }}</el-col>
                                    <!-- <el-col :span="24">字体大小：{{ form.fontSize }}</el-col>
                                    <el-col :span="24">字体颜色：{{ form.fontColor }}</el-col>
                                    <el-col :span="24">字体位置：{{ form.fontPosition }}</el-col> -->
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
        <el-dialog v-model="dialogVisible" title="新建公告" width="40%">
            <el-form label-width="70px">
                <el-form-item label="标题：">
                    <el-input v-model="form1.noticeTitle"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addNotice()">确定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { NoticeData,imgPub } from "../api/index";
import { useRouter } from "vue-router";
export default {
    name: "basetable",
    setup() {
        const query = reactive({
            id:"",
            codeId:"2"

        });
        const data = reactive({
            showPL: true
        })
        const route = new useRouter()
        const tableData = ref([
            {
                noticeTitle: "123",
                body:"123",
                fontSize: "12",
                fontColor: "red",
                fontPosition: "1",
                author: "jx",
                startTime: "",
                id:""
            }
        ]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            NoticeData(query).then((res) => {

                tableData.value = res.data.records;
                // console.log(tableData.value[0])
                pageTotal.value = res.data.records.length || 50;
                // for (var i = 0; i < res.data.records.length; i++) {
                //     tableData.value[i].programMaterial = '/api/imgs/' + tableData.value[i].programMaterial
                // }
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
            noticeTitle: "123",
                body:"123",
                fontSize: "12",
                fontColor: "red",
                fontPosition: "1",
                author: "jx",
                startTime: ""

        });
        let form1 = reactive({
            noticeTitle: "",
                body:"123",
                fontSize: "12",
                fontColor: "red",
                fontPosition: "1",
                author: "jx",
                startTime: ""

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

        const addNotice = () => {
            console.log(form1)
            if (form1.noticeTitle === '') {
                ElMessage.error('公告标题不能为空')
            } else {
                // console.log(form1.noticeTitle)
                route.push({
                    path: '/noticeadd',
                    query: { noticeTitle: form1.noticeTitle}
                })
                dialogVisible.value = false
            }
                

            

        }
        const handlePub = (index, row) => {
            query.id=row.id
            // query.body=row.body
            // query.fontColor=row.fontColor
            // query.fontPosition=row.fontPosition
            // query.fontSize=row.fontSize
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
        const handleImg = (index, row) => {
            Object.keys(form).forEach((item) => {
                form[item] = row[item];
            });
            visible.value = true
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
            addNotice,
            handlePub,
            handleImg
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
