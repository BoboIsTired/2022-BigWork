import request from '../utils/request';

export const fetchData = query => {
    return request({
        // url: './table.json',
        url: '/admin',
        method: 'get',
        params: query
    });
};
