import request from '../utils/request';

export const fetchData = query => {
    return request({
        // url: './table.json',
        url: '/admin',
        method: 'get',
        params: query
    });
};

export const login = query => {
    return request({

        url: '/admin/login',
        method: 'post',
        params: params
    });
};
