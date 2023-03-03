import {codeOptions, deepClone} from "../../common.js"

const template = `
<el-dialog
    title="插入代码"
    :visible.sync="visible"
    width="60%"
    :before-close="handleClose" @open="handleOpen">
    <div class="block">
        <el-cascader
        v-model="tagPath"
        :options="options"
        :props="{ expandTrigger: 'hover' }"></el-cascader>
        <component v-if="tagPath.length" :is="'ex-def-' + tagPath[tagPath.length-1]" @change="handleDefChange"></component>

    </div>
    <span slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" :disabled="!tagPath.length" @click="handleChange">确 定</el-button>
    </span>
</el-dialog>
`;

export default {
    props: [],
    data() {
        return {
            visible: false,
            tagPath: [],
            codeData: {},
            options: codeOptions
        }
    },
    template,
    methods: {
        open() {
            this.visible = true;
        },
        close() {
            this.handleClose();
        },
        handleClose() {
            this.visible = false;
            this.tagPath = [];
            this.codeData = {};
        },
        handleChange() {
            let $this = this;
            axios.get("api/gen-id").then(function(res) {
                let data = deepClone($this.codeData);
                data.attributes.id = res.data;
                console.log("add-dialog", data);
                $this.$emit("change", data);
                $this.close();
            }).catch(function(err) {
                console.error(err);
                $this.$message.error(err.msg);
            });

        },
        handleDefChange(val) {
            this.codeData = deepClone(val);
        },
        handleOpen() {
            this.tagPath = [];
            this.codeData = {};
        }
    }
}