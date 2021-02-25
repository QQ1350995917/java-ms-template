<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${projectName}</title>

  <link rel="stylesheet" href="https://unpkg.zhimg.com/element-ui/lib/theme-chalk/index.css">
  <link rel="stylesheet" href="./static/css/template.css">

  <script src="https://unpkg.zhimg.com/vue/dist/vue.js"></script>
  <script src="https://unpkg.zhimg.com/element-ui/lib/index.js"></script>
  <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
  <script src="./static/js/template.js"></script>

</head>
<body>
<el-container id="container">
  <el-header style="padding: 0px;height: 42px">
    <el-row>
      <el-col :span="12" :offset="6">
        <template>
          <el-tabs v-model="activeName" type="card" @tab-click="onTabClick">
            <el-tab-pane label="${projectName}" name="0"></el-tab-pane>
            <el-tab-pane label="swagger" name="swagger">swagger</el-tab-pane>
          </el-tabs>
        </template>
      </el-col>
    </el-row>
  </el-header>
  <el-main
      style="padding: 0px 10px 0px 0px; margin-top: 10px; background-color: transparent; height: 666px">
    <el-row class="row-bg" style="background-color: transparent">
      <el-col :span="12" :offset="6" style="background-color: transparent">
      <#if classNames?exists>
        <#list classNames as className>
        <el-button type="primary" @click="onButtonClick('${className}')">${className}</el-button>
        </#list>
      </#if>
      </el-col>
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
        activeName: '0'
      };
    },
    methods: {
      onTabClick(tab, event) {
        if ("swagger" === tab.name) {
          window.open("/swagger", '_self');
        }
      },
      onButtonClick(className) {
        // window.location.href="./static/" + className + ".html"
        window.open('/static/' + className + '.html', '_blank');
      }
    }
  });
</script>
</body>
</html>
