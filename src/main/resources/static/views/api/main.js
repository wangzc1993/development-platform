
const template = /* html */`
<el-container>
  <aside-menu :data="treeData" @node-click="handleNodeClick" @node-add="handleNodeAdd" @node-delete="handleNodeDelete"></aside-menu>
  <el-main>
    <el-col span="18" v-if="context.length">
        <el-row style="margin-bottom: 5px">
        <el-button type="success" plain size="small" @click="apiSave">保存</el-button>
        <el-button type="warning" plain size="small" @click="apiCheck">代码检查</el-button>
        <el-button type="primary" plain size="small" @click="apiExecute">运行</el-button>
        <el-button type="warning" plain size="small" disabled>调试</el-button>
        </el-row>
        <template v-for="(item, i) in context">
        <component :key="i" :is="getComponentName(item)" :data="item"></component>
        </template>
    </el-col>
    <el-col span="6" v-if="context.length">
        <el-collapse v-model="activeNames">
        <el-collapse-item title="接口请求参数" name="1">
            <el-form size="mini">
            <el-form-item v-for="(item, i) in apiParameters.childs" :label="item.attributes.name">
                <el-input v-model="item.textContext">
                    <el-button slot="prepend" @click.stop="handleEditParameter(item, i)" icon="el-icon-edit" size="mini"></el-button>
                    <el-button slot="append" @click.stop="handleDeleteParameter(i)" type="danger" icon="el-icon-delete" size="mini"></el-button>
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click.stop="handleAddParameter">添加参数</el-button>
            </el-form-item>
            </el-form>
            <el-dialog :visible.sync="parametersDialogVisible" width="30%">
            <el-form label-width="100px">
                <el-form-item label="参数名称">
                <el-input v-model="parameter.attributes.name"></el-input>
                </el-form-item>
                <el-form-item label="默认参数值">
                <el-input v-model="parameter.textContext"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="parametersDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleAddParameterChange">确 定</el-button>
            </span>
            </el-dialog>
        </el-collapse-item>
        <el-collapse-item title="运行结果" name="2" v-if="res">
            <el-input
            type="textarea"
            placeholder="运行结果"
            v-model="res"
            :autosize="{ minRows: 2, maxRows: 20}"
            disabled>
            </el-input>
        </el-collapse-item>
        <el-collapse-item title="运行日志" name="3" v-if="logs">
            <div v-for="item in logs" :key="item">{{item}}</div>
        </el-collapse-item>
        <el-collapse-item title="检测日志" name="4" v-if="checkLogs.length">
            <div v-for="item in checkLogs" :key="item">{{item.id}} - {{item.message}}</div>
        </el-collapse-item>
        </el-collapse>
    </el-col>
  </el-main>
</el-container>
`;

export default {
    template,
    provide() {
        let $this = this;
        return {
            getByTag(tag, childs) {
                for (let item of childs) {
                    if (item.tag == tag) {
                        return item;
                    }
                }
            },
            getListByTag(tag, childs) {
                let ls = [];
                for (let item of childs) {
                    if (item.tag == tag) {
                        ls.push(item);
                    }
                }
                return ls;
            },
            check: $this.check
        }
    },
    data: function () {
        return {
            activeNames: ["1"],
            context: [],
            filePath: "",
            res: "",
            logs: "",
            checkLogs: [],
            check: {
                checkLogsMap: {}
            },
            parametersDialogVisible: false,
            addParameter: true,
            parameterEditIndex: -1,
            parameter: {
                tag: "parameter",
                textContext: "",
                attributes: { type: "def", name: "" },
                childs: []
            },
            treeData: []
        };
    },
    computed: {
        apiParameters() {
            console.log("context", this.context);
            for (let xml of this.context) {
                if (xml.tag == 'xml') {
                    for (let ps of xml.childs) {
                        if (ps.tag == 'api-parameters') {
                            return ps;
                        }
                    }
                }
            }
            return { childs: [] }
        }
    },
    watch: {
        "$route.query": function (val) {
            this.filePath = val.filePath;
            this.loadContext();
        }
    },
    methods: {
        loadContext() {
            let $this = this;
            axios.get(`file/tree?type=api`).then(function (res) {
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
                $this.context = [res.data];
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        handleNodeAdd(data) {
            if (!data.path) {
                data.path = "/api"
            }
            let $this = this;
            axios.post(`file/mk-file`, data).then(function (res) {
                $this.$message.success(res.msg);
                $this.loadContext();
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        handleNodeDelete(data) {
            let $this = this;
            axios.post(`file/delete`, data).then(function (res) {
                $this.$message.success(res.msg);
                $this.loadContext();
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        getComponentName(data) {
            return "ex-" + data.tag;
        },
        getParameters() {
            let params = {};
            for (let r of this.apiParameters.childs) {
                let val = r.textContext ? r.textContext.trim() : "";
                if (val.indexOf("{") == 0 || val.indexOf("[") == 0) {
                    val = JSON.parse(val);
                }
                params[r.attributes.name] = val;
            }
            return params;
        },
        apiExecute() {
            let $this = this;
            let params = this.getParameters();
            console.log("parameters", params);
            axios.post(`api/execute`, {
                path: $this.filePath,
                parameters: params
            }).then(function (res) {
                $this.$message.success("运行成功");
                $this.res = res.data;
                $this.logs = res.logs;
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        apiSave() {
            let $this = this;
            axios.post(`file/save`, {
                path: this.filePath,
                json: JSON.stringify($this.context[0])
            }).then(function (res) {
                $this.$message.success("保存成功");
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        apiCheck() {
            let $this = this;
            axios.post(`api/check`, {
                path: this.filePath,
                json: JSON.stringify($this.context[0])
            }).then(function (res) {
                $this.checkLogs = res.data;
                let map = {};
                for (let r of $this.checkLogs) {
                    map[r.id] = r.message;
                }
                $this.check.checkLogsMap = map;
                if (!$this.checkLogs.length) {
                    $this.$message.success("检查完成，代码无问题");
                }
                console.log("init checkLogsMap", $this.check.checkLogsMap);
            }).catch(function (err) {
                $this.$message.error(err.msg);
            });
        },
        handleAddParameter() {
            this.addParameter = true;
            this.parametersDialogVisible = true;
            this.parameter = {
                tag: "parameter",
                attributes: {
                    type: "def",
                    name: ""
                },
                textContext: "",
                childs: []
            }
        },
        handleEditParameter(data, i) {
            this.parameterEditIndex = i;
            this.addParameter = false;
            this.parametersDialogVisible = true;
            this.parameter = deepClone(data);
        },
        handleDeleteParameter(i) {
            this.apiParameters.childs.splice(i, 1);
        },
        handleAddParameterChange() {
            this.parametersDialogVisible = false;
            if (this.addParameter) {
                this.apiParameters.childs.push(deepClone(this.parameter));
            } else {
                this.apiParameters.childs.splice(this.parameterEditIndex, 1, deepClone(this.parameter));
            }
        }
    },
    mounted: function () {
        console.log("params", this.$route.query);
        this.filePath = this.$route.query.filePath;
        this.loadContext();
    },
};