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
export const MachineData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/device',
        method: 'get',
        params: query
        
    });
};
export const MachineEdit = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/device/update',
        method: 'put',
        data: query
        
    });
};
export const GroupData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/deviceGroup',
        method: 'get',
        params: query
        
    });
};
export const MaterialData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/material/page',
        method: 'get',
        params: query
        
    });
};
export const MaterialDelete = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/material/delete',
        method: 'put',
        data: {id:query}
        
    });
};
export const ProgramData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/programs',
        method: 'get',
        params: query
        
    });
};
export const MaterialCreate = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/programs',
        method: 'post',
        data:query
        
    });
};
export const NoticeData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/notice',
        method: 'get',
        params: query
        
    });
};
export const NoticeCreate = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/notice',
        method: 'post',
        data:query
        
    });
};
export const imgPub = query => {
        // console.log(query)
        return request({
            // url: './table.json',
            url: '/android/operation',
            method: 'post',
            data:query
            
        });
    };
