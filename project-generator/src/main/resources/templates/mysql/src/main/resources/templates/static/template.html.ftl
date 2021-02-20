<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${className}</title>

  <link rel="stylesheet" href="https://unpkg.zhimg.com/element-ui/lib/theme-chalk/index.css">
  <link rel="stylesheet" href="css/template.css">

  <script src="https://unpkg.zhimg.com/vue/dist/vue.js"></script>
  <script src="https://unpkg.zhimg.com/element-ui/lib/index.js"></script>
  <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
  <script src="js/template.js"></script>

</head>
<body>
<el-container id="container">
  <el-header style="padding: 0px;height: 42px">
    <el-row>
      <el-col :span="12" :offset="6">
        <el-button @click="uploadDialogVisible = true">upload</el-button>
      </el-col>
    </el-row>
  </el-header>
  <el-main style="padding: 0px;height: 650px">
    <el-row>
      <el-col :span="12" :offset="6">
        <el-table
            :data="files"
            style="width: 100%;"
            max-height="600">
          <el-table-column
              prop="id"
              label="ID"
              width="180">
          </el-table-column>
          <el-table-column
              prop="fileName"
              label="FileName"
              width="180">
          </el-table-column>
          <el-table-column
              prop="fileSuffix"
              label="Suffix"
              width="180">
          </el-table-column>
          <el-table-column
              prop="url"
              label="Url"
              width="340">
          </el-table-column>
          <el-table-column
              prop="createTime"
              label="CreateTime"
              width="120">
          </el-table-column>
          <el-table-column
              fixed="right"
              label="Operations"
              width="140">
            <template slot-scope="scope">
              <el-button
                  @click.native.prevent="onBrowse(scope.row.url)"
                  type="text"
                  size="small">
                Browse
              </el-button>
              <el-button
                  @click.native.prevent="onDownload(scope.row.id)"
                  type="text"
                  size="small">
                Download
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              layout="prev, pager, next"
              @current-change="onRequestForFiles"
              :page-size="pageSize"
              :total="filesTotal">
          </el-pagination>
        </div>
      </el-col>
      <el-dialog
          title="提示"
          :visible.sync="uploadDialogVisible"
          width="30%"
          :before-close="onUploadClose">
        <el-upload
            class="upload-demo"
            drag
            action="/api/admin/file/test/test"
            multiple
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
        <span slot="footer" class="dialog-footer">
          <el-button @click="uploadDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="uploadDialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </el-row>
  </el-main>
  <el-footer>
    <el-row>
      <el-col :span="12" :offset="6">
        <p>Powered by www.dingpengwei@foxmail.com</p>
        <p>copyright©2020</p>
      </el-col>
    </el-row>
  </el-footer>
</el-container>

<script>
  new Vue({
    el: '#container',
    data: function () {
      return {
        files: [],
        pageIndex: 1,
        pageSize: 12,
        filesTotal: 0,
        uploadDialogVisible: false
      };
    },
    created() {
      this.onRequestForFiles(this.pageIndex);
    },
    methods: {
      onRequestForFiles(pageIndex) {
        this.$http.get('/api/admin/file/' + (pageIndex - 1) + '/' + this.pageSize).then(
            function (res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var data = body.data;
              var elements = data.elements;
              if (elements == undefined || elements.length == 0) {
                this.$message({
                  message: '没有找到数据表',
                  type: 'warning'
                });
              } else {
                this.files = data.elements;
                this.filesTotal = data.total;
              }
            }, function (e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              if (code == 400) {
                this.$message.error('参数错误');
              } else if (code == 500) {
                this.$message.error('服务异常');
              } else {
                this.$message.error('未知异常');
              }
            });
      },
      onBrowse(url) {
        window.open(url, '_blank');
      },
      onDownload(id) {
        window.open('/api/admin/file/' + id, '_blank');
      },
      onUploadClose(done) {
        // this.$confirm('确认关闭？')
        // .then(_ => {
        //   done();
        // })
        // .catch(_ => {});
        done();
      }
    }
  });
</script>
</body>
</html>
