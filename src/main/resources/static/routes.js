const Main = () => import("./views/main.js");
const ViewMain = () => import("./views/view/main.js");
const ApiMain = () => import("./views/api/main.js");
const MethodMappingMain = () => import("./views/method-mapping/main.js");
const DatabaseMain = () => import("./views/database/main.js");
const ResourceMain = () => import("./views/resource/main.js");
const ConfigMain = () => import("./views/config/main.js");
const TimeMain = () => import("./views/time/main.js");

export default [{
    path: '/',
    component: Main
},
{
    path: '/view',
    component: ViewMain
},
{
    path: '/api',
    component: ApiMain
},
{
    path: '/method-mapping',
    component: MethodMappingMain
},
{
    path: '/database',
    component: DatabaseMain
},
{
    path: '/resource',
    component: ResourceMain
},
{
    path: '/config',
    component: ConfigMain
},
{
    path: '/time',
    component: TimeMain
}];