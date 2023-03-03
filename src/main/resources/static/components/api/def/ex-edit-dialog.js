import { deepClone } from "../../common.js";

const template = `
<el-dialog
    title="编辑代码"
    :visible.sync="visible"
    width="60%"
    :before-close="handleClose" @open="handleOpen">
    <div class="block">
        <component v-if="codeData.tag" :is="'ex-def-' + codeData.tag" :data="codeData" @change="handleDefChange"></component>
    </div>
    <span slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="handleChange">确 定</el-button>
    </span>
</el-dialog>
`;

export default {
    props: ["data"],
    data() {
        return {
            codeData: {},
            visible: false
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
            this.codeData = {};
            this.visible = false;
        },
        handleChange() {
            this.$emit("change", deepClone(this.codeData));
            this.close();

        },
        handleDefChange(val) {
            this.codeData = deepClone(val);
        },
        handleOpen() {
            this.tagPath = [];
            this.codeData = deepClone(this.data);
        }
    }
}