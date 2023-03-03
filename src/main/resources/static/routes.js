const Main = () => import("./views/main.js");
const ViewMain = () => import("./views/view/main.js");
const ViewEdit = () => import("./views/view/edit.js");
const ApiMain = () => import("./views/api/main.js");
const ApiEdit = () => import("./views/api/edit.js");
const MethodMappingMain = () => import("./views/method-mapping/main.js");
const MethodMappingEdit = () => import("./views/method-mapping/edit.js");
const DatabaseMain = () => import("./views/database/main.js");
const DatabaseEdit = () => import("./views/database/edit.js");
const ResourceMain = () => import("./views/resource/main.js");
const ResourceEdit = () => import("./views/resource/edit.js");
const ConfigMain = () => import("./views/config/main.js");
const ConfigEdit = () => import("./views/config/edit.js");
const TimeMain = () => import("./views/time/main.js");
const TimeEdit = () => import("./views/time/edit.js");

const routes = [{
    path: '/',
    component: Main
},
{
    path: '/view',
    component: ViewMain
},
{
    path: '/view/edit',
    component: ViewEdit
},
{
    path: '/api',
    component: ApiMain
},
{
    path: '/api/edit',
    component: ApiEdit
},
{
    path: '/method-mapping',
    component: MethodMappingMain
},
{
    path: '/method-mapping/edit',
    component: MethodMappingEdit
},
{
    path: '/database',
    component: DatabaseMain
},
{
    path: '/database/edit',
    component: DatabaseEdit
},
{
    path: '/resource',
    component: ResourceMain
},
{
    path: '/resource/edit',
    component: ResourceEdit
},
{
    path: '/config',
    component: ConfigMain
},
{
    path: '/config/edit',
    component: ConfigEdit
},
{
    path: '/time',
    component: TimeMain
},
{
    path: '/time/edit',
    component: TimeEdit
}];

const mainTabs = [
    {
        name: "主页",
        uri: "/",
        type: ""
    },
    {
        name: "页面",
        uri: "/view",
        type: "view"
    },
    {
        name: "接口",
        uri: "/api",
        type: "api"
    },
    {
        name: "方法",
        uri: "/method-mapping",
        type: "method-mapping"
    },
    {
        name: "数据库",
        uri: "/database",
        type: "database"
    },
    {
        name: "资源文件",
        uri: "/resource",
        type: "resource"
    },
    {
        name: "公共配置",
        uri: "/config",
        type: "config"
    },
    {
        name: "定时任务",
        uri: "/time",
        type: "time"
    }
];

export {routes, mainTabs}