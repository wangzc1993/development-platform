const template = /* html */`
<el-container>
    <aside-menu :data="treeData" @node-click="handleNodeClick" @node-add="handleNodeAdd" @node-delete="handleNodeDelete"></aside-menu>
    <el-main>
        <el-form label-width="120px">
            <template v-for="item in context.childs">
                <template v-if="item.tag=='class'">
                    <el-alert title="Java类配置" type="info"></el-alert>
                    <el-col span="8">
                        <el-form-item label="类名">
                            <el-input v-model="item.textContext"></el-input>
                        </el-form-item>
                    </el-col>
                </template>
                <el-col span="8">
                    <el-form-item label="说明" v-if="item.tag=='description'">
                        <el-input v-model="item.textContext"></el-input>
                    </el-form-item>
                </el-col>
                <template v-if="item.tag=='methods'">
                    <el-alert title="Java方法配置" type="info"></el-alert>
                    <template v-for="method in item.childs">
                        <template v-for="info in method.childs">
                            <el-col span="8">
                                <el-form-item label="方法名称" v-if="info.tag=='methodName'">
                                    <el-input v-model="info.textContext"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col span="8">
                                <el-form-item label="方法说明" v-if="info.tag=='description'">
                                    <el-input v-model="info.textContext"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col span="8">
                                <el-form-item label="方法范围" v-if="info.tag=='scope'">
                                    <el-input v-model="info.textContext"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col span="8">
                                <el-form-item label="映射方法名称" v-if="info.tag=='name'">
                                    <el-input v-model="info.textContext"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col span="8">
                                <el-form-item label="返回值类型" v-if="info.tag=='return'">
                                    <el-input v-model="info.attributes.type"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col span="8">
                                <el-form-item label="映射返回值类型" v-if="info.tag=='return'">
                                    <el-input v-model="info.textContext"></el-input>
                                </el-form-item>
                            </el-col>
                            <template v-if="info.tag=='parameters'">
                                <el-alert title="Java方法参数配置" type="info"></el-alert>
                                <el-row v-for="p in info.childs">
                                    <el-col span="10">
                                        <el-form-item label="参数类型">
                                            <el-input v-model="p.attributes.type"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col span="10">
                                        <el-form-item label="映射参数类型">
                                            <el-input v-model="p.textContext"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col span="4">
                                        <el-button>删除</el-button>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col span="8" offset="6">
                                        <el-button>添加参数</el-button>
                                        <el-button type="success">保存</el-button>
                                    </el-col>
                                </el-row>
                            </template>
                        </template>
                    </template>
                </template>

            </template>
        </el-form>
    </el-main>
</el-container>
`;

export default {
    template,
    data() {
        return {
            filePath: "",
            context: {},
            treeData: []
        }
    },
    methods: {
        init() {
            let $this = this;
            axios.get(`file/tree?type=method-mapping`).then(function (res) {
                $this.treeData = res.data;
            }).catch(function (err) {
                console.log(err);
            });
        },
        handleNodeClick(data) {
            if (data.isDir) {
                return;
            }
            let $this = this;
            axios.get(`file/read?path=${encodeURI(data.filePath)}`).then(function (res) {
                $this.context = res.data;
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        handleNodeAdd(data) {
            if (!data.path) {
                data.path = "/method-mapping"
            }
            let $this = this;
            axios.post(`file/mk-file`, data).then(function (res) {
                $this.$message.success(res.msg);
                $this.init();
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        handleNodeDelete(data) {
            let $this = this;
            axios.post(`file/delete`, data).then(function (res) {
                $this.$message.success(res.msg);
                $this.init();
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
    },
    mounted() {
        this.filePath = this.$route.query.filePath;
        this.init();
    }
}