import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
const path = require('path')
import { resolve } from 'path'

export default {
    base: './',
    open: true,
    // 是否开启 https
    https: false,
    // 服务端渲染
    outDir: 'dist',
    resolve: {
        alias: {
            '@': resolve(__dirname, '.', 'src'),
        },
    },
    ssr: false,
    plugins: [vue()],
    optimizeDeps: {
        include: ['schart.js']
    },
    server: {
        port: 8080,
        host: "0.0.0.0",
        // 是否自动在浏览器打开
        open: false,
        // 是否开启 https
        https: false,
        proxy: {
          '/api': {
            target: 'http://localhost:8888',
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/api/, ''),
          },
        },
        
    }

}
