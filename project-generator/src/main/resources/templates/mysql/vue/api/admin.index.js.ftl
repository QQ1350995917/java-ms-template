import request from '@/utils/request'

export function create(params) {
  return request({
    url: '/service/${serviceName}/api/admin',
    method: 'post',
    data: params
  })
}

export function edit(params) {
  return request({
    url: '/service/${serviceName}/api/admin/' + params.user.id,
    method: 'put',
    data: params
  })
}

export function list(page, scopes, sorts) {
  return request({
    url: '/service/${serviceName}/api/admin?page=' + encodeURI(JSON.stringify(page)) + "&scopes=" + encodeURI(JSON.stringify(scopes)) + "&sorts=" + encodeURI(JSON.stringify(sorts)),
    method: 'get'
  })
}

export function detail(uid) {
  return request({
    url: '/service/${serviceName}/api/admin/' + uid,
    method: 'get'
  })
}

export function enable(uids) {
  return request({
    url: '/service/${serviceName}/api/admin/enable',
    method: 'patch',
    data: uids
  })
}

export function disable(uids) {
  return request({
    url: '/service/${serviceName}/api/admin/disable',
    method: 'patch',
    data: uids
  })
}

export function del(uids) {
  return request({
    url: '/service/${serviceName}/api/admin',
    method: 'delete',
    data: uids
  })
}

export function reset(params) {
  return request({
    url: '/service/${serviceName}/api/admin/pwd/reset',
    method: 'patch',
    data: params
  })
}

export function accountList(uid, page, scopes, sorts) {
  return request({
    url: '/service/${serviceName}/api/admin/account/' + uid + '?page=' + encodeURI(JSON.stringify(page)) + "&scopes=" + encodeURI(JSON.stringify(scopes)) + "&sorts=" + encodeURI(JSON.stringify(sorts)),
    method: 'get'
  })
}


export function accountEnable(params) {
  return request({
    url: '/service/${serviceName}/api/admin/admin/account/enable?userId=' + params.userId + '&accountId=' + params.accountId,
    method: 'patch'
  })
}

export function accountDisable(params) {
  return request({
    url: '/service/${serviceName}/api/admin/admin/account/disable?userId=' + params.userId + '&accountId=' + params.accountId,
    method: 'patch'
  })
}

export function accountDelete(params) {
  return request({
    url: '/service/${serviceName}/api/admin/admin/account?userId=' + params.userId + '&accountId=' + params.accountId,
    method: 'delete'
  })
}
