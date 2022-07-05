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
            <el-button type="file">上传素材</el-button>
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
            <el-upload v-model:file-list="fileList" action="/api/material/upload" list-type="picture-card"
              :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
              <el-icon>
                <Plus />
              </el-icon>
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
import { MaterialData, MaterialDelete, userEdit } from "../api/index";
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
export default {
  data() {
    return {
      fileList: [

      ],
      query:[]
    }
  },
  methods: {
    handleRemove(file, fileList) {
      MaterialDelete(file.id).then((res) => {
      console.log(res.msg)
    });
    },
    handlePreview(file) {
        dialogImageUrl.value = file.url
        dialogVisible.value = true
        console.log(file)
      },
  },
  mounted() {
    MaterialData().then((res) => {
      var list = res.data
      // console.log(res.data)
      this.fileList = list.records;
      // this.fileList[0].url='/api/imgs/'+this.fileList[0].url
      // console.log(this.fileList[0].url)
      for(var i=0;i<this.fileList.length;i++){
        this.fileList[i].url='/api/file/'+this.fileList[i].url
      }
      console.log(this.fileList)

    });

  }
}
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
.show {
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
.t {
  display: flex;
  align-items: center;

}

.left {
  margin-right: 300px;
}
</style>
