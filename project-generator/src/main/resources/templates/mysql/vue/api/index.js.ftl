import request from '@/utils/request'

export function create(params) {
  return request({
    url: '/service/${serviceName}/api',
    method: 'post',
    data: params
  })
}

export function edit(params) {
  return request({
    url: '/service/${serviceName}/api/' + params.user.id,
    method: 'put',
    data: params
  })
}

export function list(page, scopes, sorts) {
  return request({
    url: '/service/${serviceName}/api?page=' + encodeURI(JSON.stringify(page)) + "&scopes=" + encodeURI(JSON.stringify(scopes)) + "&sorts=" + encodeURI(JSON.stringify(sorts)),
    method: 'get'
  })
}

export function detail(uid) {
  return request({
    url: '/service/${serviceName}/api/' + uid,
    method: 'get'
  })
}

export function enable(uids) {
  return request({
    url: '/service/${serviceName}/api/enable',
    method: 'patch',
    data: uids
  })
}

export function disable(uids) {
  return request({
    url: '/service/${serviceName}/api/disable',
    method: 'patch',
    data: uids
  })
}

export function del(uids) {
  return request({
    url: '/service/${serviceName}/api',
    method: 'delete',
    data: uids
  })
}

