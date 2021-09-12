import request from '@/utils/request'

export function create(params) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}',
    method: 'post',
    data: params
  })
}

export function edit(params) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}/' + params.id,
    method: 'patch',
    data: params
  })
}

export function list(page, scopes, sorts) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}?page=' + encodeURI(JSON.stringify(page)) + "&scopes=" + encodeURI(JSON.stringify(scopes)) + "&sorts=" + encodeURI(JSON.stringify(sorts)),
    method: 'get'
  })
}

export function detail(id) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}/' + id,
    method: 'get'
  })
}

export function enable(ids) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}/enable',
    method: 'patch',
    data: ids
  })
}

export function disable(ids) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}/disable',
    method: 'patch',
    data: ids
  })
}

export function del(ids) {
  return request({
    url: '/service/${serviceName}/api/admin/${tableName}',
    method: 'delete',
    data: ids
  })
}

