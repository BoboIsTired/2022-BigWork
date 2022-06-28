module.exports = {
  
    // 配置跨域请求
    devServer: {
      port: 3000,
      host: 'localhost',
      open: true,
      https: false,
      proxy: {
        '/api': {
          target: 'http://localhost:8888/',
          ws: true,
          changeOrigin: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
  
    }
  
  }