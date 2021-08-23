import {
  create,
  edit,
  list,
  detail,
  enable,
  disable,
  del,
  reset,
  accountList,
  accountEnable,
  accountDisable,
  accountDel
} from '@/api/${serviceName}/admin'

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
      const {uid, aid, cellphoneId, emailId, loginName, name, cellphone, email, pin, empNo, level, gender, summary} = state
      edit({
        user: {
          id: uid,
          name: name,
          pin: pin,
          empNo: empNo,
          level: level,
          gender: gender,
          summary: summary
        },
        account: {
          id: aid,
          loginName: loginName,
          // loginPwd: accountPwd
        },
        contacts: [
          {
            id: cellphoneId,
            type: 1,
            value: cellphone
          },
          {
            id: emailId,
            type: 2,
            value: email
          }
        ]
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
  },
  reset(commit, params){
    return new Promise((resolve, reject) => {
      reset(params).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  accountList(commit, query){
    return new Promise((resolve, reject) => {
      accountList(query.uid, query.page, query.scopes, query.sorts).then(response => {
      const data = response
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
  },
  accountEnable(commit, params){
    return new Promise((resolve, reject) => {
      accountEnable(params).then(response => {
      if (response.meta.code == 200) {
      resolve(response.data)
    } else {
      reject(response.meta)
    }
  }).catch(error => {
      reject(error)
    })
  })
  },
  accountDisable(commit, params){
    return new Promise((resolve, reject) => {
      accountDisable(params).then(response => {
      if (response.meta.code == 200) {
      resolve(response.data)
    } else {
      reject(response.meta)
    }
  }).catch(error => {
      reject(error)
    })
  })
  },
  accountDel(commit, params){
    return new Promise((resolve, reject) => {
      accountDel(params).then(response => {
      if (response.meta.code == 200) {
      resolve(response.data)
    } else {
      reject(response.meta)
    }
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
