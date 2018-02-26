import Vue from 'vue'
import Router from 'vue-router'
import dashBoard from '@/components/page/dashBoard'
import appForward from '@/components/page/appForward'
import hostList from '@/components/page/hostList'
import hostMonitor from '@/components/page/hostMonitor'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: '/',
            component: dashBoard
        },
        {
            path: '/appForward',
            name:'appForward',
            component: appForward
        },
        {
            path: '/hostList',
            name:'hostList',
            component: hostList
        },
        {
            path: '/hostMonitor',
            name:'hostMonitor',
            component: hostMonitor
        }
    ]
})
