import {
  create,
  edit,
  list,
  detail,
  enable,
  disable,
  del
} from '@/api/${serviceName}'

const actions = {
  create(commit, state){
    return new Promise((resolve, reject) => {
      const {
<#if columns?exists>
  <#list columns as column>
        ${column.javaName},
  </#list>
</#if>
      } = state
      create({
<#if columns?exists>
  <#list columns as column>
        ${column.javaName}: ${column.javaName},
  </#list>
</#if>
      }).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  edit(commit, state){
    return new Promise((resolve, reject) => {
      const {
<#if columns?exists>
  <#list columns as column>
    ${column.javaName}: ${column.javaName},
  </#list>
</#if>
      } = state
      edit({
<#if columns?exists>
  <#list columns as column>
    ${column.javaName}: ${column.javaName},
  </#list>
</#if>
      }).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  list(commit, query) {
    return new Promise((resolve, reject) => {
      list(query.page, query.scopes, query.sorts).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  detail(commit, uid){
    return new Promise((resolve, reject) => {
      detail(uid).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  enable(commit, uids){
    return new Promise((resolve, reject) => {
      enable(uids).then(response => {
      if (response.meta.code == 200) {
      resolve(response.data)
    }
  }).catch(error => {
      reject(error)
    })
  })
  },
  disable(commit, uids){
    return new Promise((resolve, reject) => {
      disable(uids).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  del(commit, uids){
    return new Promise((resolve, reject) => {
      del(uids).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  }
}

export default {
  namespaced: true,
  actions
}
