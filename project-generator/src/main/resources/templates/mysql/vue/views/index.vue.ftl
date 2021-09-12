<template>
  <el-container>
    <el-header style="height: 30px">
      <el-row style="padding-top: 5px">
        <el-col :span="14">
          <el-input placeholder="输入关键字进行查找">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="2" style="text-align: right">
          <el-button @click="onCheckedDisableButtonClick">批量禁用</el-button>
        </el-col>
        <el-col :span="2" style="text-align: right">
          <el-button @click="onCheckedEnableButtonClick">批量启用</el-button>
        </el-col>
        <el-col :span="2" style="text-align: right">
          <el-button @click="onCheckedDeleteButtonClick">批量删除</el-button>
        </el-col>
        <el-col :span="2" style="text-align: right">
          <el-button @click="onCreateButtonClick">新增</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main>
      <el-table ref="mainTableList" :data="mainTableDataList" row-key="id" style="width: 100%" stripe
                tooltip-effect="light" highlight-current-row
                @selection-change="onCheckedChange"
                @sort-change="onSortChange">
        <el-table-column align="center" type="selection" width="50" fixed :disable="checkedAll"></el-table-column>
        <el-table-column align="center" label="序号" type="index" width="100" fixed>
          <template slot-scope="scope">
            <span>{{scope.$index + query.page.index * query.page.size + 1}}</span>
          </template>
        </el-table-column>
        <#if columns?exists>
          <#list columns as column>
        <el-table-column label="${column.javaName}" prop="${column.javaName}"  width="120" sortable='custom' fixed>
          <template slot-scope="scope">
            <span>{{ scope.row.${column.javaName} }}</span>
          </template>
        </el-table-column>
          </#list>
        </#if>
        <el-table-column fixed="right" align="center" width="260" label="操作">
          <template slot-scope="scope">
            <el-button @click="onItemDetailClick(scope.row)" icon="el-icon-edit" plain size="mini"></el-button>
            <el-popconfirm
                v-if="scope.row.able == 1"
                title="确定禁用吗？"
                iconColor="yellow"
                confirmButtonText='确定'
                confirmButtonType="warning"
                cancelButtonText='取消'
                icon="el-icon-warning"
                @onConfirm="onItemAbleClick(scope.row)"
            >
              <el-button slot="reference" icon="el-icon-lock" plain size="mini"></el-button>
            </el-popconfirm>
            <el-popconfirm
                v-if="scope.row.able == 0"
                title="确定启用吗？"
                iconColor="yellow"
                confirmButtonText='确定'
                confirmButtonType="warning"
                cancelButtonText='取消'
                icon="el-icon-warning"
                @onConfirm="onItemAbleClick(scope.row)"
            >
              <el-button slot="reference" icon="el-icon-unlock" plain size="mini"></el-button>
            </el-popconfirm>

            <el-popconfirm
                title="确定删除吗？"
                iconColor="red"
                confirmButtonText='删除'
                confirmButtonType="danger"
                cancelButtonText='取消'
                icon="el-icon-delete"
                @onConfirm="onItemDeleteClick(scope.row)"
            >
              <el-button slot="reference" icon="el-icon-delete" plain size="mini"></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleIndexChange"
          :current-page="query.page.index + 1"
          :page-sizes="[10,12,20,50,100]"
          :page-size="query.page.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total" style="margin-top: 10px">
      </el-pagination>
      <el-dialog
          :title="createDialogTitle"
          :visible.sync="createDialogVisible"
          :before-close="onCreateDialogCloseClick">
        <el-form :model="createDialogForm" :rules="createDialogFormRules" ref="createDialogForm" label-width="120px">
        <#if columns?exists>
          <#list columns as column>
          <el-form-item label="${column.javaName}" prop="${column.javaName}">
            <el-input v-model="createDialogForm.${column.javaName}"></el-input>
          </el-form-item>
          </#list>
        </#if>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="onCreateDialogResetClick('createDialogForm')">重置</el-button>
          <el-button @click="onCreateDialogCloseClick('createDialogForm')">取消</el-button>
          <el-button type="primary" @click="onCreateDialogCommitClick('createDialogForm')">保存</el-button>
      </span>
      </el-dialog>
    </el-main>
  </el-container>
</template>
<script>
  import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
  import ElPopover from "../../../node_modules/element-ui/packages/popover/src/main";
  import ElPopconfirm from "../../../node_modules/element-ui/packages/popconfirm/src/main";
  import ElHeader from "../../../node_modules/element-ui/packages/header/src/main";
  import ElMain from "../../../node_modules/element-ui/packages/main/src/main";
  import ElRadio from "../../../node_modules/element-ui/packages/radio/src/radio";
  export default {
    components: {
      ElRadio,
      ElMain,
      ElHeader,
      ElPopconfirm,
      ElPopover,
      ElButton
    },
    name: 'List',
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'info',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        loading: true,
        query: {
          page: {
            index: 0,
            size: 12
          },
          scopes: [{"fieldName": "del", "fieldValue": "0", "hit": "EM"}],
          sorts: [{"fieldName": "create_time", "sort": "asc"}]
        },
        mainTableDataList: [],
        total: 0,
        checkedAll: false,
        checkedList: [],
        createDialogVisible: false,
        createDialogTitleCreate: '新增',
        createDialogTitleEdit: '编辑',
        createDialogTitle: '',
        createDialogFormInit: {
        <#if columns?exists>
          <#list columns as column>
            ${column.javaName}: '',
          </#list>
        </#if>
        },
        createDialogForm: {
        <#if columns?exists>
          <#list columns as column>
            ${column.javaName}: '',
          </#list>
        </#if>
        },
        createDialogFormRules: {
        <#if columns?exists>
          <#list columns as column>
            ${column.javaName}:
          [
          <#if (column.nullable!"YES") == "NO">
            {required: true, message: '${column.jdbcName}', trigger: 'blur'},
          </#if>
//            {required: true, message: '请输入名称', trigger: 'blur'},
//            {min: 4, max: 24, message: '长度在 M 到 N 个字符', trigger: 'blur'},
//            {
//              validator: function(rule, value, callback) {
//                if (/^((13[0-9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$/i.test(value) == false) {
//                  callback(new Error("手机号格式错误"));
//                } else {
//                  callback();
//                }
//              },
//              trigger: "blur"
//            }
         ],
          </#list>
        </#if>
        }
      }
    },
    created() {
      this.requestForMainTableList()
    },
    methods: {
      checkAll(data) {
        if (data) {
          data.forEach(row => {
            this.$refs.mainTable.toggleRowSelection(row);
        });
        } else {
          this.$refs.mainTable.clearSelection();
        }
      },
      onCheckedChange(val){
        this.checkedList = val;
      },
      requestForMainTableList() {
        this.$store.dispatch('${serviceName}Admin${className}/list', this.query)
        .then((response) => {
          if (response.meta.code == 200) {
          this.query.page.index = parseInt(response.data.index);
          this.query.page.size = parseInt(response.data.size);
          this.total = parseInt(response.data.total);
          this.mainTableDataList = response.data.elements;
        }
      }).catch(() => {})
      },
      onSortChange(props){
        if (props.order == 'ascending') {
          this.query.sorts[0].fieldName = props.prop
          this.query.sorts[0].sort = "ASC"
        } else if (props.order == 'descending') {
          this.query.sorts[0].fieldName = props.prop
          this.query.sorts[0].sort = "DESC"
        } else {
          this.query.sorts[0].fieldName = 'create_time'
          this.query.sorts[0].sort = "ASC"
        }
        console.log(JSON.stringify(this.query.sorts))
        this.requestForMainTableList();
      },
      handleSizeChange(size) {
        this.query.page.size = size;
        this.requestForMainTableList()
      },
      handleIndexChange(index) {
        index = index - 1;
        this.query.page.index = index;
        this.requestForMainTableList()
      },
      onItemDetailClick(row){
        <#if columns?exists>
          <#list columns as column>
        this.createDialogForm.${column.javaName} = row.${column.javaName};
          </#list>
        </#if>
        this.createDialogTitle = this.createDialogTitleEdit;
        this.createDialogVisible = true;
      },
      onCheckedDisableButtonClick(){
        var _this = this;
        var disabling = [];
        this.checkedList.forEach(item => {
          disabling.push(item.id);
        });
        this.requestForUserDisable(disabling,function () {
          _this.$message({
            message: '禁用成功',
            type: 'success'
          });
          _this.checkedList.forEach(item => {
            item.able = 0;
          });
        });
      },
      onCheckedEnableButtonClick(){
        var _this = this;
        var enabling = [];
        this.checkedList.forEach(item => {
          enabling.push(item.id);
        });
        this.requestForUserEnable(enabling,function () {
          _this.$message({
            message: '启用成功',
            type: 'success'
          });
          _this.checkedList.forEach(item => {
            item.able = 1;
          });
        });
      },
      onItemAbleClick(row){
        var _this = this;
        if (row.able == 0) {
          this.requestForUserEnable([row.id], function () {
            row.able = 1;
            _this.$message({
              message: '启用成功',
              type: 'success'
            });
          });
        } else if (row.able == 1) {
          this.requestForUserDisable([row.id], function () {
            row.able = 0;
            _this.$message({
              message: '禁用成功',
              type: 'success'
            });
          });
        } else {
          this.$message.error('参数错误');
        }
      },
      requestForUserEnable(ids, successCallback){
        this.$store.dispatch('${serviceName}Admin${className}/enable', ids)
        .then(successCallback()).catch((error) => {
          this.$message.error(error);
      })
      },
      requestForUserDisable(ids, successCallback){
        this.$store.dispatch('${serviceName}Admin${className}/disable', ids)
        .then(successCallback()).catch((error) => {
          this.$message.error(error);
      })
      },
      onCheckedDeleteButtonClick() {
        var deleting = [];
        this.checkedList.forEach(item => {
          deleting.push(item.id);
      });
        this.requestForDelete(deleting);
      },
      onItemDeleteClick(row) {
        this.requestForDelete([row.id]);
      },
      requestForDelete(ids){
        this.$store.dispatch('${serviceName}Admin${className}/del', ids)
        .then(() => {
          this.$message({
          message: '删除成功',
          type: 'success'
        });
        if (this.mainTableDataList.length == 1 && this.query.page.index > 0) {
          this.query.page.index = this.query.page.index - 1;
        }
        this.requestForMainTableList();
      }).catch((error) => {
          this.$message.error(error);
      })
      },
      onCreateButtonClick() {
        this.createDialogTitle = this.createDialogTitleCreate;
        this.createDialogForm = this.createDialogFormInit;
        this.createDialogVisible = true;
      },
      onCreateDialogResetClick(formName) {
        this.createDialogForm = this.createDialogFormInit;
        this.$refs[formName].resetFields();
      },
      onCreateDialogCloseClick() {
        this.onCreateDialogResetClick('createDialogForm');
        this.createDialogVisible = false;
      },
      onCreateDialogCommitClick(formName) {
        if (this.createDialogTitle == this.createDialogTitleCreate) {
          this.requestForCreateMember(formName);
        } else if (this.createDialogTitle == this.createDialogTitleEdit) {
          this.requestForEditMember(formName);
        }
      },
      requestForCreateMember(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$store.dispatch('${serviceName}Admin${className}/create', this.createDialogForm)
            .then((response) => {
              if (response.meta.code == 200) {
                this.$message({
                  message: '创建成功',
                  type: 'success'
                });
                this.onCreateDialogCloseClick();
                this.requestForMainTableList();
              }
            }).catch((e) => {})
          } else {
            return false;
          }
        });
      },
      requestForEditMember(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$store.dispatch('${serviceName}Admin${className}/edit', this.createDialogForm)
            .then((response) => {
              if (response.meta.code == 200) {
                this.$message({
                  message: '修改成功',
                  type: 'success'
                });
                this.onCreateDialogCloseClick();
                this.requestForMainTableList();
              }
            }).catch((e) => {})
          } else {
            return false;
          }
        });
      },
    },
  }
</script>

<style scoped>
  .el-container {
    padding-left: 10px;
    padding-top: 20px;
  }
</style>
