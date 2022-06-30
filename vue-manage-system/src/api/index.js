import request from '../utils/request';

export const fetchData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/admin',
        method: 'get',
        params: query
        
    });
};
export const userAdd = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/admin',
        method: 'post',
        data: query
        
    });
};
export const userEdit = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/admin/update',
        method: 'put',
        data: query
        
    });
};


