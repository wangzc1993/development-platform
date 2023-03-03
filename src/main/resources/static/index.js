import { routes, mainTabs } from "./routes.js";
import apiComponents from "./components/api.components.js";

for (let key in apiComponents) {
    Vue.component(key, apiComponents[key]);
}

const template = `
<el-container>
    <el-aside>
        <el-menu>
            <el-menu-item>
                <i class="el-icon-menu"></i>
                <span slot="title">中文在线开发平台</span>
            </el-menu-item>
        </el-menu>
        <el-row>
            <el-tree :data="treeData" @node-click="handleNodeClick" @node-contextmenu="handleNodeContextMenu">
                <span class="custom-tree-node" slot-scope="{ node, data }" @mouseover="fileManager.hoverPath=data.filePath">
                    <i :class="treeClass(data, node)"></i>
                    <span>{{ node.label }}</span>
                    <template v-if="fileManager.hoverPath==data.filePath">
                        <el-link type="primary" v-if="data.isDir" @click.stop="handleAddFile(data.filePath, data.isDir)">新建</el-link>
                        <el-link type="danger" v-if="data.filePath" @click.stop="handleDeleteFile(data.filePath)">删除</el-link>
                    </template>
                </span>
            </el-tree>
        </el-row>
    </el-aside>

    <el-container>
        <el-header>
            <el-menu mode="horizontal" :default-active="activeIndex" @select="handleSelect">
                <el-menu-item v-for="(item, i) in mainTabs" :index="i">
                    <span>{{item.name}}</span>
                </el-menu-item>
            </el-menu>
        </el-header>
        <el-container>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
    <el-dialog :title="fileManager.mkDir?'新建目录':'新建文件'" :visible.sync="dialogVisible" width="30%">
        <el-form label-width="80px">
            <el-form-item :label="fileManager.mkDir?'目录名称':'文件名称'">
                <el-input v-model="fileManager.name" :placeholder="fileManager.mkDir?'请输入目录名称':'请输入文件名称'"></el-input>
            </el-form-item>
            <el-form-item label="类型">
                <el-radio v-model="fileManager.mkDir" :label="0">新建文件</el-radio>
                <el-radio v-model="fileManager.mkDir" :label="1">新建目录</el-radio>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleFileChange">确 定</el-button>
        </span>
    </el-dialog>
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
            mainTabs,
            activeIndex: 0,
            treeData: [],
            dialogVisible: false,
            fileManager: {
                path: "",
                isDir: 0,
                name: "",
                mkDir: 0,
                hoverPath: ""
            }
        };
    },
    methods: {
        treeClass(data, node) {
            return {
                'el-icon-folder': data.isDir && !node.expanded,
                'el-icon-folder-opened': data.isDir && node.expanded,
                'el-icon-document': !data.isDir
            };
        },
        handleSelect(key, keyPath) {
            let type = this.mainTabs[key].type;
            this.activeIndex = key;
            let $this = this;
            axios.get(`file/tree?type=${type}`).then(function (res) {
                $this.treeData = [{ label: "根目录", children: res.data, isDir: 1 }];
                $this.$router.push($this.mainTabs[key].uri);
            }).catch(function (err) {
                console.log(err);
            });
        },
        handleNodeClick(data) {
            if (data.isDir || this.activeIndex == 0) {
                return;
            }
            console.log(data);
            let $this = this;
            console.log("router-uri", $this.activeIndex);
            this.$router.push({
                path: $this.mainTabs[$this.activeIndex].uri + "/edit",
                query: {
                    filePath: data.filePath
                }
            });

        },
        handleNodeContextMenu(data) {
            console.log(arguments);
        },
        handleAddFile(path, isDir) {
            if (!path) {
                path = this.mainTabs[this.activeIndex].uri;
            }
            this.fileManager.path = path;
            this.fileManager.isDir = isDir;
            this.dialogVisible = true;
        },
        handleFileChange() {
            this.dialogVisible = false;
            console.log(this.fileManager);
            let $this = this;
            if (this.fileManager.mkDir) {
                axios.get(`file/mk-dir?path=${encodeURI(this.fileManager.path)}&dirName=${this.fileManager.name}`).then(function (res) {
                    $this.$message.success(res.msg);
                }).catch(function (err) {
                    $this.$message.error(err.msg);
                });
            } else {
                axios.get(`file/mk-file?path=${encodeURI(this.fileManager.path)}&fileName=${this.fileManager.name}`).then(function (res) {
                    $this.$message.success(res.msg);
                }).catch(function (err) {
                    $this.$message.error(err.msg);
                });
            }
        },
        handleDeleteFile(path) {
            let $this = this;
            this.$confirm('确定要删除吗，该操作将删除目录及下面所有文件？', '删除' + path, {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                axios.get(`file/delete?path=${encodeURI(path)}`).then(function (res) {
                    $this.$message.success(res.msg);
                }).catch(function (err) {
                    $this.$message.error(err.msg);
                });
              }).catch(() => {
                        
              });

            
        }
    },
    mounted() {
        let $this = this;
        axios.interceptors.response.use(function (res) {
            console.log("axios-response", res);
            if (res.data.code == 1) {
                return res.data;
            }
            return Promise.reject(res.data);
        }, function (error) {
            console.error("axios-response-error", error);
            $this.$message.error("请求失败：" + error.code);
            return Promise.reject(error);
        });

        let uri = location.hash;
        uri = uri.substring(1, uri.indexOf("?") > 1 ? uri.indexOf("?") : uri.length);
        console.log(uri);
        for (let i = 1; i < this.mainTabs.length; i++) {
            if (uri.indexOf(this.mainTabs[i].uri) == 0) {
                this.handleSelect(i, []);
                break;
            }
        }
    }
}).$mount("#app");

export default { router, app };