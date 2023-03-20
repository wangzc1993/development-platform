const template = /* html */`
<el-aside>
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
</el-aside>
`;

export default {
    template,
    props: ["data"],
    data() {
        return {
            filePath: "",
            dialogVisible: false,
            fileManager: {
                path: "",
                isDir: 0,
                name: "",
                mkDir: 0,
                hoverPath: ""
            }
        }
    },
    computed: {
        treeData() {
            let $this = this;
            return [{ label: "根目录", children: $this.data, isDir: 1 }]
        }
    },
    methods: {
        treeClass(data, node) {
            return {
                'el-icon-folder': data.isDir && !node.expanded,
                'el-icon-folder-opened': data.isDir && node.expanded,
                'el-icon-document': !data.isDir
            };
        },
        handleNodeClick(data) {
            if (!data.filePath) {
                return;
            }
            this.$emit("node-click", data);
        },
        handleNodeContextMenu(data) {
        },
        handleAddFile(path, isDir) {
            this.fileManager.path = path;
            this.fileManager.isDir = isDir;
            this.dialogVisible = true;
        },
        handleFileChange() {
            this.dialogVisible = false;
            console.log(this.fileManager);
            let $this = this;
            this.$emit("node-add", {
                isDir: $this.fileManager.mkDir,
                path: $this.fileManager.path,
                name: $this.fileManager.name
            })
        },
        handleDeleteFile(path) {
            let $this = this;
            this.$confirm('确定要删除吗，该操作将删除目录及下面所有文件？', '删除' + path, {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $this.$emit("node-delete", {
                    path: path
                })
            }).catch(() => {

            });


        }
    }

}