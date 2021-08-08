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
      <el-col :span="2" :offset="3">
        <el-button @click="onCreateDialogOpen({})">Create</el-button>
      </el-col>
      <el-col :span="16">
        <div>
          <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select">
            <el-select v-model="select" slot="prepend" placeholder="请选择" style="width: 320px;">
            <#if columns?exists>
              <#list columns as column>
              <el-option label="${column.javaName}" value="${column.jdbcName}"></el-option>
              </#list>
            </#if>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="onSearchButtonClick"></el-button>
          </el-input>
        </div>
      </el-col>
    </el-row>
  </el-header>
  <el-main style="padding: 0px;height: 650px">
    <el-row>
      <el-col :span="18" :offset="3">
        <el-table
            :data="list"
            style="width: 100%;"
            max-height="600">
        <#if columns?exists>
          <#list columns as column>
          <el-table-column prop="${column.jdbcName}" label="${column.javaName}">
            <#if column.javaName == 'createTime'>
              <template slot-scope="scope">
                <span>{{ dateFormat(scope.row.${column.javaName})}}</span>
              </template>
            <#elseif column.javaName == 'updateTime'>
              <template slot-scope="scope">
                <span>{{ dateFormat(scope.row.${column.javaName})}}</span>
              </template>
            <#else>
              <template slot-scope="scope">
                <span>{{ scope.row.${column.javaName}}}</span>
              </template>
            </#if>
          </el-table-column>
          </#list>
        </#if>
          <el-table-column
              fixed="right"
              label="Operations"
              width="180">
            <template slot-scope="scope">
              <el-button @click.native.prevent="onUpdateDialogOpen(scope.row)"
                         icon="el-icon-edit" size="small"></el-button>
              <el-button v-if="0 == scope.row.able"
                         @click.native.prevent="onEnableButtonClick(scope.row.id)"
                         icon="el-icon-view" size="small"></el-button>
              <el-button v-if="1 == scope.row.able"
                         @click.native.prevent="onDisableButtonClick(scope.row.id)"
                         icon="el-icon-view" size="small"></el-button>
              <el-button @click.native.prevent="onDelButtonClick(scope.row.id)"
                         icon="el-icon-delete" size="small"></el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              layout="prev, pager, next"
              @current-change="onRequestForList"
              :page-size="pageSize"
              :total="total">
          </el-pagination>
        </div>
      </el-col>
      <el-dialog
          :title="dialogName"
          :visible.sync="dialogVisible"
          width="45%"
          :before-close="onDetailDialogClose">
        <#if columns?exists>
          <#list columns as column>
        <div>
          <el-input placeholder="请输入内容" v-model="item.${column.jdbcName}">
            <template slot="prepend">${column.javaName}</template>
          </el-input>
        </div>
          </#list>
        </#if>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button v-if="'Create' == dialogName" type="primary"
                     @click="onRequestForCreate">Create</el-button>
          <el-button v-if="'Update' == dialogName" type="primary"
                     @click="onRequestForUpdate">Update</el-button>
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
    data: function() {
      return {
        list: [],
        item: {},
        pageIndex: 1,
        pageSize: 12,
        total: 0,
        select: '',
        keyword: '',
        dialogVisible: false,
        dialogName: '',
        scopesTemplate: [{
          hit: '指定查询方式[EM:精确,ENM:精确非,AL:前后模糊,LL:左模糊,RL:右模糊,SM:范围',
          fieldName: '指定查询字段',
          fieldValue: '指定查询目标',
          start: '范围查询起始值，区间包含',
          end: '范围查询结束值，区间包含'
        }],
        sortsTemplate: [{fieldName: '指定排序字段', sort: '[desc|asc]'}],
        pageTemplate: {index: 0, size: 12},
        scopes: [],
        sorts: [],
        page: {}
      };
    },
    created() {
      this.onRequestForList();
    },
    methods: {
      onRequestForList() {
        this.$http.get('/api/${apiPath}?scopes=' + encodeURIComponent(JSON.stringify(this.scopes))
            + '&sorts=' + encodeURIComponent(JSON.stringify(this.sorts))
            + '&page=' + encodeURIComponent(JSON.stringify(this.page)))
        .then(
            function(res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var data = body.data;
              if (code == 200) {
                var elements = data.elements;
                if (elements == undefined || elements.length == 0) {
                  this.$message({
                    message: 'No Date',
                    type: 'warning'
                  });
                } else {
                  this.$message.success('List Success');
                  this.list = data.elements;
                  this.total = Number(data.total);
                }
              } else {
                this.$message({
                  message: meta.message,
                  type: 'warning'
                });
              }
            }, function(e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              if (code == 400) {
                this.$message.error('Request params error');
              } else if (code == 500) {
                this.$message.error('Server error');
              } else {
                this.$message.error('Unknown error');
              }
            });
      },
      onSearchButtonClick() {
        if (this.select === '' || this.select === undefined) {
          this.$message({
            message: 'Must select filed',
            type: 'warning'
          });
          return;
        }
        if (this.keyword === '' || this.keyword === undefined) {
          this.$message({
            message: 'Must input keyword',
            type: 'warning'
          });
          return;
        }
        this.scopes = [];
        this.scopes.push({
          hit: 'AL',
          fieldName: this.select,
          fieldValue: this.keyword
        });
        this.list = [];
        this.item = {};
        this.pageIndex = 1;
        this.pageSize = 12;
        this.total = 0;
        this.onRequestForList();
      },
      onCreateDialogOpen(item) {
        this.item = item;
        this.dialogName = 'Create';
        this.dialogVisible = true;
      },
      onUpdateDialogOpen(item) {
        this.item = item;
        this.dialogName = 'Update';
        this.dialogVisible = true;
      },
      onRequestForCreate() {
        this.$http.post('/api/${apiPath}', this.item).then(
            function(res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (200 == code) {
                this.$message.success('Create Success');
                this.dialogVisible = false;
                this.onRequestForList();
              } else {
                this.$message.error(code + ':' + message);
              }
            }, function(e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (code == 400) {
                this.$message.error('Request params error:' + message);
              } else if (code == 500) {
                this.$message.error('Server error:' + message);
              } else {
                this.$message.error('Unknown error:' + message);
              }
            });
      },
      onRequestForUpdate() {
        this.$http.patch('/api/${apiPath}/' + this.item.id, this.item).then(
            function(res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (200 == code) {
                this.$message.success('Update Success');
                this.dialogVisible = false;
                this.onRequestForList();
              } else {
                this.$message.error(code + ':' + message);
              }
            }, function(e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (code == 400) {
                this.$message.error('Request params error:' + message);
              } else if (code == 500) {
                this.$message.error('Server error:' + message);
              } else {
                this.$message.error('Unknown error:' + message);
              }
            });
      },
      onDetailDialogClose(done) {
        //   this.$confirm('Confirm？')
        //   .then(_ = > {
        //     done();
        // }).
        //   catch(_ = > {};
        // )
        //   ;
        done();
      },
      onEnableButtonClick(id) {
        this.$http.put('/api/${apiPath}/enable/' + id).then(
            function(res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (200 == code) {
                this.$message.success('Enable Success');
                this.dialogVisible = false;
                this.onRequestForList();
              } else {
                this.$message.error(code + ':' + message);
              }
            }, function(e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (code == 400) {
                this.$message.error('Request params error:' + message);
              } else if (code == 500) {
                this.$message.error('Server error:' + message);
              } else {
                this.$message.error('Unknown error:' + message);
              }
            });
      },
      onDisableButtonClick(id) {
        this.$http.put('/api/${apiPath}/disable/' + id).then(
            function(res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (200 == code) {
                this.$message.success('Disable Success');
                this.dialogVisible = false;
                this.onRequestForList();
              } else {
                this.$message.error(code + ':' + message);
              }
            }, function(e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (code == 400) {
                this.$message.error('Request params error:' + message);
              } else if (code == 500) {
                this.$message.error('Server error:' + message);
              } else {
                this.$message.error('Unknown error:' + message);
              }
            });
      },
      onDelButtonClick(id) {
        this.$http.delete('/api/${apiPath}/' + id).then(
            function(res) {
              var body = res.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (200 == code) {
                this.$message.success('Delete Success');
                this.dialogVisible = false;
                this.onRequestForList();
              } else {
                this.$message.error(code + ':' + message);
              }
            }, function(e) {
              var body = e.body;
              var meta = body.meta;
              var code = meta.code;
              var message = meta.message;
              if (code == 400) {
                this.$message.error('Request params error:' + message);
              } else if (code == 500) {
                this.$message.error('Server error:' + message);
              } else {
                this.$message.error('Unknown error:' + message);
              }
            });
      },
      dateFormat:function(time) {
        var date=new Date(time);
        var year=date.getFullYear();
        var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
        var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
        var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
        var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
        var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
        // 拼接
        return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
      }
    }
  });
</script>
</body>
</html>
