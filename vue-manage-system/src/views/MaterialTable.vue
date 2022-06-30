<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 基础表格
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <div>
          <el-input v-model="input1" placeholder="Please input">
            <template #prepend> 搜索 </template>
            <template #append>
              <el-button>查询</el-button>
            </template>
          </el-input>
        </div>
      </div>
      <div class="title">
        <i class="el-icon-folder-opened"></i>
        <div class="path">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">全部</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">图片</a></el-breadcrumb-item>
            <el-breadcrumb-item>视频</el-breadcrumb-item>
            <el-breadcrumb-item>音频</el-breadcrumb-item>
            <el-breadcrumb-item>艺术</el-breadcrumb-item>
            <el-breadcrumb-item>党建</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </div>
      <div class="t">
        <div class="left">
          <div class="option">
            <el-radio-group v-model="radio">
              <el-radio :label="1">全部</el-radio>
              <el-radio :label="2">图片</el-radio>
              <el-radio :label="3">视频</el-radio>
              <el-radio :label="4">音频</el-radio>
              <el-radio :label="5">艺术</el-radio>
              <el-radio :label="6">党建</el-radio>
            </el-radio-group>
          </div>
        </div>
        <div class="right">
            <el-row class="mb-4">
                <el-button>取消全选</el-button>
                <el-button type="primary">全选</el-button>
                <el-button type="success">批量删除</el-button>
                <el-button type="info">上传素材</el-button>
            </el-row>
        </div>
      </div>

      <div class="show">
        <el-row>
          <!-- <el-col
            v-for="(o, index) in 2"
            :key="o"
            :span="4"
            :offset="index > 0 ? 2 : 0"
          >
            <el-card :body-style="{ padding: '0px' }">
              <img
                src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                class="image"
              />
              <div style="padding: 14px">
                <span>Yummy hamburger</span>
                <div class="bottom">
                  <time class="time">{{ currentDate }}</time>
                  <el-button text class="button">Operating</el-button>
                </div>
              </div>
            </el-card>
          </el-col> -->
          <el-col>
            <el-upload
                v-model:file-list="fileList"
                action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
            >
                <el-icon><Plus /></el-icon>
            </el-upload>

            <el-dialog v-model="dialogVisible">
                <img w-full :src="dialogImageUrl" alt="Preview Image" />
            </el-dialog>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { fetchData } from "../api/index";
// import { Search } from '../element-plus/icons-vue'
// import { Plus } from '@element-plus/icons-vue'

// import type { UploadProps, UploadUserFile } from 'element-plus'

const input1 = ref("");
const currentDate = ref(new Date());
export default {
  name: "basetable",
  setup() {
    const query = reactive({
      address: "",
      name: "",
      pageIndex: 1,
      pageSize: 10,
    });
    const tableData = ref([]);
    const pageTotal = ref(0);
    const radio = ref(3);
    // 获取表格数据
    const getData = () => {
      fetchData(query).then((res) => {
        tableData.value = res.list;
        pageTotal.value = res.pageTotal || 50;
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
    let form = reactive({
      name: "",
      address: "",
    });
    let idx = -1;
    const handleEdit = (index, row) => {
      idx = index;
      Object.keys(form).forEach((item) => {
        form[item] = row[item];
      });
      editVisible.value = true;
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
      form,
      handleSearch,
      handlePageChange,
      handleDelete,
      handleEdit,
      saveEdit,
      radio,
    };
  },
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
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
.title {
  margin-top: 30px;
  display: flex;
  padding-left: 20px;
}
.el-icon-folder-opened {
  font-size: 30px;
  color: #999;
  padding-right: 10px;
}
.path {
  padding-top: 10px;
}
.option {
  padding-top: 30px;
}
.time {
  font-size: 12px;
  color: #999;
}
/* 图片 */
.show{
    margin-top: 70px;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
/* 按钮栏 */
.t{
    display: flex;
    align-items: center;

}
.left{
        margin-right: 300px;
}


</style>
