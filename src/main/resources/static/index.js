import apiComponents from "./components/api.components.js";
import menus from "./menus.js";
import routes from "./routes.js";

for (let key in apiComponents) {
    Vue.component(key, apiComponents[key]);
}

const template = /* html */`
<el-container>
    <el-header>
        <el-menu mode="horizontal" :default-active="activeIndex" @select="handleSelect">
            <el-menu-item v-for="(item, i) in menus" :disabled="item.disabled" :index="i">
                <i v-if="item.icon" :class="item.icon"></i>
                <span>{{item.name}}</span>
            </el-menu-item>
        </el-menu>
    </el-header>
    <router-view></router-view>
</el-container>
`;


const router = new VueRouter({
    routes
});

const app = new Vue({
    template,
    router,
    data() {
        return {
            menus,
            activeIndex: 0,
        };
    },
    methods: {
        handleSelect(key, keyPath) {
            if (key < 0) {
                return;
            }
            this.$router.push(this.menus[key].uri);
        },
    },
    mounted() {
        this.$nextTick(function () {
            console.log(this.$route);
        });
        let $this = this;
        axios.interceptors.response.use(function (res) {
            if (res.data.code == 1) {
                return res.data;
            }
            return Promise.reject(res.data);
        }, function (error) {
            console.error("axios-response-error", error);
            $this.$message.error("请求失败：" + error.code);
            return Promise.reject(error);
        });

        let uri = location.hash.substring(1);
        this.$router.push(uri);
        for (let i = 1; i < this.menus.length; i++) {
            if (0 == uri.indexOf(this.menus[i].uri)) {
                this.activeIndex = i;
                break;
            }
        }
    }
}).$mount("#app");

export default { router, app };